package App.Domain.Bussness;

import App.Domain.Bussness.ClienteClient;
import App.Domain.Response.AcountResponse;
import App.Domain.Response.Cliente;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.AcountGateway;
import App.Infra.Persistence.Entity.AcountEntity;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import App.Infra.Persistence.Repository.AcountRepository;

import App.Util.AcountMapper;
import feign.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcountService implements AcountGateway {

    private final AcountRepository acountRepository;

    private final AcountMapper acountMapper;
    private final ClienteClient clienteService;

    public AcountService(AcountRepository acountRepository, AcountMapper acountMapper, ClienteClient clienteService) {
        this.acountRepository = acountRepository;
        this.acountMapper = acountMapper;
        this.clienteService = clienteService;
    }


    @Override
    public ResponseEntity<List<AcountResponse>> ListarAcounts()
    {
        try
        {
               List<AcountEntity> acountEntities = acountRepository.findAll();
               List<AcountResponse> responses = new ArrayList<>();
               for(AcountEntity entity: acountEntities)
               {
                   AcountResponse response = acountMapper.EntityToDto(entity);
                   responses.add(response);
               }
               return new ResponseEntity<>(responses,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AcountResponse> BuscarAcountPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                AcountEntity entity = acountRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                AcountResponse response = acountMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AcountResponse>BuscarAcountPorAcountNumber(String acountNumber)
    {
        try
        {
            if(acountNumber != null)
            {
                AcountEntity entity = acountRepository.findByacount(acountNumber).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                AcountResponse response = acountMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AcountResponse>NovaAcount(String nome,
                                                    String sobrenome,
                                                    Long documento,
                                                    LocalDate dataNascimento,
                                                    String logradouro,
                                                    String numero,
                                                    String bairro,
                                                    String referencia,
                                                    String cep,
                                                    Long prefixo,
                                                    Long telefone,
                                                    String email,
                                                    Double score,
                                                    TIPOACOUNT tipoacount)
    {
        try
        {
            if(score < 0){throw new IllegalActionException();}
            if(nome != null && sobrenome != null && documento != null &&
            telefone != null && email != null && tipoacount != null &&
            logradouro != null && numero != null && bairro != null &&
            referencia != null && cep != null && prefixo != null && score != null)
            {
                Cliente clienteResponse = clienteService.NovoCliente(nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email, score).getBody();
                if(clienteResponse != null)
                {
                    int acountnumber = (int) (111111 + Math.random() * 999999);
                    int senhaAuthorizacao = (int) (1111 + Math.random() * 9999);
                    int senhaAuthentication = (int) (11111111 + Math.random() * 99999999);
                    String acounttype = "";
                    int digito = 0;
                    if(tipoacount == TIPOACOUNT.SALARIO)
                    {
                        acounttype = "CS_";
                        digito = 02;
                    }
                    if(tipoacount == TIPOACOUNT.CORRENTE)
                    {
                        acounttype = "CC_";
                        digito = 01;
                    }
                    if(tipoacount == TIPOACOUNT.POUCANCA)
                    {
                        acounttype = "CP_";
                        digito = 00;
                    }
                    AcountEntity acount = new AcountEntity();
                    acount.setAtiva(true);
                    acount.setBloqueio(false);
                    acount.setSaldo(0.0);
                    acount.setAcount(acounttype+acountnumber);
                    acount.setSenhaAutenticacao("at_"+senhaAuthentication);
                    acount.setSenhaAutorizacao("atz_"+senhaAuthorizacao);
                    acount.setTimeStamp(LocalDateTime.now());
                    acount.setCliente(nome +" "+sobrenome);
                    acount.setDocumento(documento);
                    acount.setLimite(score);
                    acount.setLimiteDisponivel(score);
                    List<String> noticicacoes = new ArrayList<>();
                    acount.setNoticicacao(noticicacoes);
                    acountRepository.save(acount);
                    AcountResponse response = acountMapper.EntityToDto(acount);
                    return new ResponseEntity<>(response,HttpStatus.CREATED);
                }
                else {throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AcountResponse> SalvarAlteracao(AcountResponse acountResponse)
    {
        try
        {
            if(acountResponse != null)
            {
                AcountEntity entity = acountMapper.DtoToEntity(acountResponse);
                acountRepository.save(entity);
                AcountResponse response = acountMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<AcountResponse> AlterarLimite(Long id, Double novoLimite)
    {
        try
        {
            if(novoLimite < 0){throw new IllegalActionException();}
            if(id != null && novoLimite != null)
            {
                AcountEntity entity = acountRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                if(novoLimite < entity.getLimite()){throw new IllegalActionException();}
                entity.setLimite(novoLimite);
                entity.setTimeStamp(LocalDateTime.now());
                acountRepository.save(entity);
                AcountResponse response = acountMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AcountResponse> BloquearAcount(Long id, String justificativa)
    {
        try
        {
            if(id != null && justificativa != null)
            {
                AcountEntity entity = acountRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                entity.getNoticicacao().add(justificativa);
                AcountResponse response = acountMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
