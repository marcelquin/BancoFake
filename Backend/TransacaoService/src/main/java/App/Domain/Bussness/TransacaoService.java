package App.Domain.Bussness;

import App.Domain.Response.AcountResponse;
import App.Domain.Response.Transacao;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.TransacaoGateway;
import App.Infra.Persistence.Entity.TransacaoEntity;
import App.Infra.Persistence.Enum.STATUSTRANSACAO;
import App.Infra.Persistence.Repository.TransacaoRepository;
import App.Util.AcountService;
import App.Util.TransacaoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;
    private final AcountService acountService;
    private final TransacaoMapper transacaoMapper;


    public TransacaoService(TransacaoRepository transacaoRepository, AcountService acountService, TransacaoMapper transacaoMapper) {
        this.transacaoRepository = transacaoRepository;
        this.acountService = acountService;
        this.transacaoMapper = transacaoMapper;
    }

    @Override
    public ResponseEntity<Transacao> novoSaque(String acount, Double valor)
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
                   entity.setAcuntePagador(acountResponse.getAcount());
                   entity.setAcunteBeneficiario(acountResponse.getAcount());
                   entity.setDataTransacao(LocalDateTime.now());
                   entity.setTimeStamp(LocalDateTime.now());
                   entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                   entity.setValor(valor);
                   transacaoRepository.save(entity);
                   acountResponse.saque(valor);
                   //autorization service

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
    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acountPagador != null && acountBeneficiario != null && valor != null) {

                AcountResponse acountResponsePagador = acountService.BuscarAcountPorAcountNumber(acountPagador).getBody();

                Boolean checkPagador = acountResponsePagador.checkAcount();
                if (checkPagador == true)
                {
                   AcountResponse acountResponseBeneficiario = acountService.BuscarAcountPorAcountNumber(acountBeneficiario).getBody();
                   Boolean checkBeneficiario = acountResponseBeneficiario.checkAcount();
                   if(checkBeneficiario == true)
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
                        acountService.SalvarAlteracao(acountResponsePagador);
                        acountService.SalvarAlteracao(acountResponseBeneficiario);
                       Transacao response = transacaoMapper.EntitytoDto(entity);
                       return new ResponseEntity<>(response, HttpStatus.OK);
                   }



                } else {
                    throw new NullargumentsException();
                }
            }}
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
