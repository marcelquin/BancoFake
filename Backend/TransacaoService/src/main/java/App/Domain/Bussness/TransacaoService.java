package App.Domain.Bussness;

import App.Domain.Facede.TransacaoFacede;
import App.Domain.Feigin.JWTClient;
import App.Domain.Request.AuthRequest;
import App.Domain.Request.AuthenticationDTO;
import App.Domain.Response.AcountResponse;
import App.Domain.Response.LoginResponseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransacaoService implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;
    private final AcountService acountService;
    private final TransacaoMapper transacaoMapper;
    private final TransacaoFacede transacaoFacede;
    private final JWTClient jwtClient;


    public TransacaoService(TransacaoRepository transacaoRepository, AcountService acountService, TransacaoMapper transacaoMapper, TransacaoFacede transacaoFacede, JWTClient jwtClient) {
        this.transacaoRepository = transacaoRepository;
        this.acountService = acountService;
        this.transacaoMapper = transacaoMapper;
        this.transacaoFacede = transacaoFacede;
        this.jwtClient = jwtClient;
    }

    @Override
    public ResponseEntity<Transacao> novoSaque(String acount, String senhaAutorizacao, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acount != null && valor != null)
            {
             AuthenticationDTO authenticationDTO = new AuthenticationDTO(acount,senhaAutorizacao);
             LoginResponseDTO loginResponseDTO = (LoginResponseDTO) jwtClient.login(authenticationDTO).getBody();
             if(loginResponseDTO.token() != null)
             {
                 AcountResponse acountResponse = acountService.BuscarAcountPorAcountNumber(acount).getBody();
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
                     entity.setTipotransacao(TIPOTRANSACAO.SAQUE);
                     transacaoRepository.save(entity);
                     //autorization service
                     AuthRequest authRequest = new AuthRequest(entity.getId(), TIPOTRANSACAO.SAQUE, acountResponse.getBloqueio(), acountResponse.getAtiva(),Boolean.FALSE,null);
                     transacaoFacede.SolicitarProcessamento(authRequest);
                     Transacao response = transacaoMapper.EntitytoDto(entity);
                     return new ResponseEntity<>(response, HttpStatus.OK);
                 }
                 else {throw new IllegalActionException();}
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
            if(acountPagador != null && acountBeneficiario != null && valor != null)
            {
                AuthenticationDTO authenticationDTO = new AuthenticationDTO(acountPagador,senhaAutenticacao);
                LoginResponseDTO loginResponseDTO = (LoginResponseDTO) jwtClient.login(authenticationDTO).getBody();
                if(loginResponseDTO.token() != null)
                {
                    AcountResponse acountResponsePagador = acountService.BuscarAcountPorAcountNumber(acountPagador).getBody();
                    Boolean checkPagador = acountResponsePagador.checkAcount();
                    if (checkPagador == true)
                    {
                        AcountResponse acountResponseBeneficiario = acountService.BuscarAcountPorAcountNumber(acountBeneficiario).getBody();
                        Boolean checkBeneficiario = acountResponseBeneficiario.checkAcount();
                        if (checkBeneficiario == true)
                        {
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
                            entity.setTipotransacao(TIPOTRANSACAO.DEPOSITO);
                            transacaoRepository.save(entity);
                            //autorization service
                            AuthRequest authRequest = new AuthRequest(entity.getId(), TIPOTRANSACAO.DEPOSITO, acountResponsePagador.getBloqueio(), acountResponsePagador.getAtiva(), Boolean.FALSE, null);
                            transacaoFacede.SolicitarProcessamento(authRequest);
                            Transacao response = transacaoMapper.EntitytoDto(entity);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    }
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

    @Override
    public ResponseEntity<Transacao> AdicionarSaldo(String acountPagador, String senhaAutenticacao, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(acountPagador != null && valor != null)
            {
                AuthenticationDTO authenticationDTO = new AuthenticationDTO(acountPagador,senhaAutenticacao);
                LoginResponseDTO loginResponseDTO = (LoginResponseDTO) jwtClient.login(authenticationDTO).getBody();
                if(loginResponseDTO.token() != null)
                {
                    AcountResponse acountResponsePagador = acountService.BuscarAcountPorAcountNumber(acountPagador).getBody();
                    Boolean checkPagador = acountResponsePagador.checkAcount();
                    if (checkPagador == true)
                    {
                        TransacaoEntity entity = new TransacaoEntity();
                        int codigoTransacao1 = (int) (1111 + Math.random() * 9999);
                        int codigoTransacao2 = (int) (111111 + Math.random() * 999999);
                        int codigoTransacao3 = (int) (111111 + Math.random() * 999999);
                        int codigoTransacao4 = (int) (11 + Math.random() * 99);
                        entity.setCodigo(LocalDate.now().getYear() + "." + codigoTransacao1 + "." + codigoTransacao2 + "." + codigoTransacao3 + "-" + codigoTransacao4);
                        entity.setAcuntePagador(acountResponsePagador.getAcount());
                        entity.setAcunteBeneficiario(acountResponsePagador.getAcount());
                        entity.setDataTransacao(LocalDateTime.now());
                        entity.setTimeStamp(LocalDateTime.now());
                        entity.setStatustransacao(STATUSTRANSACAO.AGUARDANDO);
                        entity.setValor(valor);
                        entity.setTipotransacao(TIPOTRANSACAO.ADD_SALDO);
                        transacaoRepository.save(entity);
                        //autorization service
                        AuthRequest authRequest = new AuthRequest(entity.getId(), TIPOTRANSACAO.ADD_SALDO, acountResponsePagador.getBloqueio(), acountResponsePagador.getAtiva(), Boolean.FALSE, null);
                        transacaoFacede.SolicitarProcessamento(authRequest);
                        Transacao response = transacaoMapper.EntitytoDto(entity);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
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

    @Override
    public void FinalizarTransacaoSaque(AuthRequest authRequest)
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
                        System.out.println("service: "+acountPagador.getSaldo());
                        acountService.SalvarAlteracao(acountPagador);
                        entity.setStatustransacao(STATUSTRANSACAO.APROVADA);
                        entity.setDataAutorizacao(LocalDateTime.now());
                        transacaoRepository.save(entity);
                    }
                }
                else {throw new IllegalActionException();}
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }


    @Override
    public void FinalizarTransacaoDeposisito(AuthRequest authRequest)
    {
        try
        {
            if(authRequest != null)
            {
                TransacaoEntity entity = transacaoRepository.findById(authRequest.idTransacao()).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                if(entity.getTipotransacao() == TIPOTRANSACAO.DEPOSITO)
                {
                    AcountResponse acountPagador = acountService.BuscarAcountPorAcountNumber(entity.getAcuntePagador()).getBody();
                    Boolean checkPagador = acountPagador.checkAcount();
                    if(checkPagador == true) {
                        AcountResponse acountBeneficiario = acountService.BuscarAcountPorAcountNumber(entity.getAcunteBeneficiario()).getBody();
                        Boolean checkBeneficiario = acountBeneficiario.checkAcount();
                        if (checkBeneficiario == true) {
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
                else {throw new IllegalActionException();}
            }
            else
            {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void FinalizarTransacaoAdicionarSaldo(AuthRequest authRequest)
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
                    if(entity.getTipotransacao() == TIPOTRANSACAO.ADD_SALDO)
                    {
                        AcountResponse acountPagador = acountService.BuscarAcountPorAcountNumber(entity.getAcuntePagador()).getBody();
                        Boolean checkAcount = acountPagador.checkAcount();
                        if(checkAcount == true)
                        {
                            acountPagador.depositto(entity.getValor());
                            System.out.println("service: "+acountPagador.getSaldo());
                            acountService.SalvarAlteracao(acountPagador);
                            entity.setStatustransacao(STATUSTRANSACAO.APROVADA);
                            entity.setDataAutorizacao(LocalDateTime.now());
                            transacaoRepository.save(entity);
                        }
                    }
                    else {throw new IllegalActionException();}
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
