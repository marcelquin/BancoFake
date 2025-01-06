package App.Domain.Bussness;

import App.Domain.Facede.TransacaoFacede;
import App.Domain.Request.AuthRequest;
import App.Domain.Response.AcountResponse;
import App.Domain.Response.Transacao;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.TransacaoGateway;
import App.Infra.Persistence.Entity.TransacaoEntity;
import App.Infra.Persistence.Enum.STATUSTRANSACAO;
import App.Infra.Persistence.Repository.TransacaoRepository;
import App.Util.AcountService;
import App.Util.TransacaoMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;
    private final AcountService acountService;
    private final TransacaoMapper transacaoMapper;
    private final TransacaoFacede transacaoFacede;


    public TransacaoService(TransacaoRepository transacaoRepository, AcountService acountService, TransacaoMapper transacaoMapper, TransacaoFacede transacaoFacede) {
        this.transacaoRepository = transacaoRepository;
        this.acountService = acountService;
        this.transacaoMapper = transacaoMapper;
        this.transacaoFacede = transacaoFacede;
    }

    @Override
    public ResponseEntity<Transacao> novoSaque(String acount, String senhaAutorizacao, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acount != null && valor != null)
            {
             AcountResponse acountResponse = acountService.BuscarAcountPorAcountNumber(acount).getBody();
               Boolean check = acountResponse.checkAcount();
               if(check == true)
               {
                   TransacaoEntity entity = new TransacaoEntity();
                   int codigoTransacao = (int) (10000001 + Math.random() * 89999999);
                   entity.setAcuntePagador(acountResponse.getAcount());
                   entity.setAcunteBeneficiario(acountResponse.getAcount());
                   entity.setDataTransacao(LocalDateTime.now());
                   entity.setTimeStamp(LocalDateTime.now());
                   entity.setCodigo("tr_"+codigoTransacao);
                   entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                   entity.setValor(valor);
                   transacaoRepository.save(entity);
                   acountResponse.saque(valor);
                   //autorization service
                   //AuthRequest authRequest = new AuthRequest(entity.getId(),acountResponse.getBloqueio(),acountResponse.getAtiva(),Boolean.FALSE,null);
                   AuthRequest authRequest = new AuthRequest(entity.getId(),Boolean.FALSE,Boolean.TRUE,Boolean.FALSE,null);
                    transacaoFacede.SolicitarProcessamento(authRequest);
                   Transacao response = transacaoMapper.EntitytoDto(entity);
                   return new ResponseEntity<>(response, HttpStatus.OK);
               }
                else
               {
                   throw new IllegalActionException();
               }
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String senhaAutorizacao, String acountBeneficiario, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acountPagador != null &&
               acountBeneficiario != null &&
               valor != null &&
               senhaAutorizacao != null)
            {
                AcountResponse acountResponsePagador = acountService.BuscarAcountPorAcountNumber(acountPagador).getBody();
                //validação senha
                if (senhaAutorizacao == acountResponsePagador.getSenhaAutorizacao())
                {
                    Boolean checkPagador = acountResponsePagador.checkAcount();
                    if (checkPagador == true)
                    {
                        AcountResponse acountResponseBeneficiario = acountService.BuscarAcountPorAcountNumber(acountBeneficiario).getBody();
                        Boolean checkBeneficiario = acountResponseBeneficiario.checkAcount();
                        if (checkBeneficiario == true)
                        {
                            TransacaoEntity entity = new TransacaoEntity();
                            entity.setAcuntePagador(acountResponsePagador.getAcount());
                            entity.setAcunteBeneficiario(acountResponseBeneficiario.getAcount());
                            entity.setDataTransacao(LocalDateTime.now());
                            entity.setTimeStamp(LocalDateTime.now());
                            entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                            entity.setValor(valor);
                            transacaoRepository.save(entity);
                            acountResponsePagador.saque(valor);
                            acountResponseBeneficiario.depositto(valor);
                            //autorization service
                            AuthRequest authRequest = new AuthRequest(entity.getId(), acountResponsePagador.getBloqueio(), acountResponsePagador.getAtiva(), Boolean.FALSE, null);
                            transacaoFacede.SolicitarProcessamento(authRequest);
                            Transacao response = transacaoMapper.EntitytoDto(entity);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                        else {throw new IllegalActionException();}
                    }
                    else { throw new IllegalActionException(); }
                }
                else {throw new IllegalActionException();}
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RabbitListener(queues = {"transacao-auth-response"})
    public void SucessTransacao(@Payload AuthRequest authRequest)
    {
        if(authRequest.auth() == true)
        {
            //busca por codigo
            TransacaoEntity entity = transacaoRepository.findById(authRequest.idTransacao()).orElseThrow(
                    ()-> new EntityNotFoundException()
            );
            //identifica conta pagador
            AcountResponse acountPagador = acountService.BuscarAcountPorAcountNumber(entity.getAcuntePagador()).getBody();
            //identifica conta beneficiario
            AcountResponse acountBeneficiario = acountService.BuscarAcountPorAcountNumber(entity.getAcunteBeneficiario()).getBody();
            //executa saque e deposito
            Boolean checkPagador = acountPagador.checkAcount();
            if(checkPagador == true)
            {
                Boolean checkBeneficiario = acountBeneficiario.checkAcount();
                if(checkBeneficiario == true)
                {
                    acountPagador.saque(entity.getValor());
                    acountBeneficiario.depositto(entity.getValor());
                    //salva alterações
                    acountService.SalvarAlteracao(acountPagador);
                    acountService.SalvarAlteracao(acountBeneficiario);
                    entity.setStatustransacao(STATUSTRANSACAO.APROVADA);
                    entity.setDataAutorizacao(LocalDateTime.now());
                    transacaoRepository.save(entity);
                }
            }

        }
        else
        { throw new IllegalActionException();}
    }

}
