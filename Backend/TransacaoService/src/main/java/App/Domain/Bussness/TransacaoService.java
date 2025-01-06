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
import App.Infra.Persistence.Enum.TIPOTRANSACAO;
import App.Infra.Persistence.Repository.TransacaoRepository;
import App.Util.AcountService;
import App.Util.TransacaoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
             System.out.println("salva: "+acountResponse.getSenhaAutorizacao());
             System.out.println("request: "+senhaAutorizacao);
             Boolean acountPasswordChack = acountResponse.verificaSenha(senhaAutorizacao);
             System.out.println(acountResponse.equals(senhaAutorizacao));
                 Boolean check = acountResponse.checkAcount();
                 if(check == true)
                 {
                     TransacaoEntity entity = new TransacaoEntity();
                     int codigoTransacao1 = (int) (1111 + Math.random() * 9999);
                     int codigoTransacao2 = (int) (111111 + Math.random() * 999999);
                     int codigoTransacao3 = (int) (111111 + Math.random() * 999999);
                     int codigoTransacao4 = (int) (11 + Math.random() * 99);
                     entity.setAcuntePagador(acountResponse.getAcount());
                     entity.setAcunteBeneficiario(acountResponse.getAcount());
                     entity.setDataTransacao(LocalDateTime.now());
                     entity.setTimeStamp(LocalDateTime.now());
                     entity.setCodigo(LocalDate.now().getYear()+"."+codigoTransacao1+"."+codigoTransacao2+"."+codigoTransacao3+"-"+codigoTransacao4);
                     entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                     entity.setValor(valor);
                     transacaoRepository.save(entity);
                     //autorization service
                     AuthRequest authRequest = new AuthRequest(entity.getId(), TIPOTRANSACAO.SAQUE, acountResponse.getBloqueio(), acountResponse.getAtiva(),Boolean.FALSE,null);
                     transacaoFacede.SolicitarProcessamento(authRequest);
                     Transacao response = transacaoMapper.EntitytoDto(entity);
                     return new ResponseEntity<>(response, HttpStatus.OK);
             }
             else {throw new IllegalActionException();}
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String senhaAutenticacao, String acountBeneficiario, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acountPagador != null && acountBeneficiario != null && valor != null) {

                AcountResponse acountResponsePagador = acountService.BuscarAcountPorAcountNumber(acountPagador).getBody();
                    Boolean checkPagador = acountResponsePagador.checkAcount();
                    if (checkPagador == true) {
                        AcountResponse acountResponseBeneficiario = acountService.BuscarAcountPorAcountNumber(acountBeneficiario).getBody();
                        Boolean checkBeneficiario = acountResponseBeneficiario.checkAcount();
                        if (checkBeneficiario == true) {
                            TransacaoEntity entity = new TransacaoEntity();
                            int codigoTransacao1 = (int) (1111 + Math.random() * 9999);
                            int codigoTransacao2 = (int) (111111 + Math.random() * 999999);
                            int codigoTransacao3 = (int) (111111 + Math.random() * 999999);
                            int codigoTransacao4 = (int) (11 + Math.random() * 99);
                            entity.setCodigo(LocalDate.now().getYear() + "." + codigoTransacao1 + "." + codigoTransacao2 + "." + codigoTransacao3 + "-" + codigoTransacao4);
                            entity.setAcuntePagador(acountResponsePagador.getAcount());
                            entity.setAcunteBeneficiario(acountResponseBeneficiario.getAcount());
                            entity.setDataTransacao(LocalDateTime.now());
                            entity.setTimeStamp(LocalDateTime.now());
                            entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                            entity.setValor(valor);
                            transacaoRepository.save(entity);
                            //autorization service
                            AuthRequest authRequest = new AuthRequest(entity.getId(), TIPOTRANSACAO.DEPOSITO, acountResponsePagador.getBloqueio(), acountResponsePagador.getAtiva(), Boolean.FALSE, null);
                            transacaoFacede.SolicitarProcessamento(authRequest);
                            Transacao response = transacaoMapper.EntitytoDto(entity);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    }
                } else {
                    throw new NullargumentsException();
                }
            }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void FinalizarTransacao(AuthRequest authRequest)
    {
        try
        {
            if(authRequest != null)
            {
                TransacaoEntity entity = transacaoRepository.findById(authRequest.idTransacao()).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                if(authRequest.tipotransacao() == TIPOTRANSACAO.SAQUE)
                {
                    AcountResponse acountPagador = acountService.BuscarAcountPorAcountNumber(entity.getAcuntePagador()).getBody();
                    Boolean checkAcount = acountPagador.checkAcount();
                    if(checkAcount == true)
                    {
                        acountPagador.saque(entity.getValor());
                        acountService.SalvarAlteracao(acountPagador);
                        entity.setStatustransacao(STATUSTRANSACAO.APROVADA);
                        entity.setDataAutorizacao(LocalDateTime.now());
                        transacaoRepository.save(entity);
                    }
                }
                if(authRequest.tipotransacao() == TIPOTRANSACAO.DEPOSITO);
                {
                    AcountResponse acountPagador = acountService.BuscarAcountPorAcountNumber(entity.getAcuntePagador()).getBody();
                    Boolean checkPagador = acountPagador.checkAcount();
                    if(checkPagador == true)
                    {
                        AcountResponse acountBeneficiario = acountService.BuscarAcountPorAcountNumber(entity.getAcunteBeneficiario()).getBody();
                        Boolean checkBeneficiario = acountBeneficiario.checkAcount();
                        if(checkBeneficiario == true)
                        {
                            acountPagador.saque(entity.getValor());
                            acountBeneficiario.depositto(entity.getValor());
                            acountService.SalvarAlteracao(acountPagador);
                            acountService.SalvarAlteracao(acountBeneficiario);
                            entity.setStatustransacao(STATUSTRANSACAO.APROVADA);
                            entity.setDataAutorizacao(LocalDateTime.now());
                            transacaoRepository.save(entity);
                        }
                    }
                }
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }


}
