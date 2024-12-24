package App.Domain.Bussness;

import App.Domain.Response.AcountResponse;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.AcountGateway;
import App.Infra.Persistence.Entity.AcountEntity;
import App.Infra.Persistence.Entity.ClientAcountEntity;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import App.Infra.Persistence.Repository.AcountRepository;
import App.Infra.Persistence.Repository.ClienteAcountRepository;
import App.Util.AcountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcountService implements AcountGateway {

    private final AcountRepository acountRepository;
    private final ClienteAcountRepository clienteAcountRepository;

    private final AcountMapper acountMapper;

    public AcountService(AcountRepository acountRepository, ClienteAcountRepository clienteAcountRepository, AcountMapper acountMapper) {
        this.acountRepository = acountRepository;
        this.clienteAcountRepository = clienteAcountRepository;
        this.acountMapper = acountMapper;
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
    public ResponseEntity<AcountResponse>NovaAcount(String clienteNome,
                                                    String clienteSobrenome,
                                                    Long documento,
                                                    String telefone,
                                                    String email,
                                                    TIPOACOUNT tipoacount)
    {
        try
        {
            if(clienteNome != null &&
            clienteSobrenome != null &&
            documento != null &&
            telefone != null &&
            email != null &&
            tipoacount != null)
            {
                ClientAcountEntity cliente = new ClientAcountEntity();
                cliente.setNome(clienteNome);
                cliente.setSobrenome(clienteSobrenome);
                cliente.setDocumento(documento);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setTimeStamp(LocalDateTime.now());
                clienteAcountRepository.save(cliente);
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
                acount.setClientAcount(cliente);
                List<String> noticicacoes = new ArrayList<>();
                acount.setNoticicacao(noticicacoes);
                acountRepository.save(acount);

                AcountResponse response = acountMapper.EntityToDto(acount);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
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