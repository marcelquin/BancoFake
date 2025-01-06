package APP.Domain.Bussness;

import APP.Domain.Response.CepResultDTO;
import APP.Domain.Response.Cliente;
import APP.Infra.Exceptions.EntityNotFoundException;
import APP.Infra.Exceptions.NullargumentsException;
import APP.Infra.Gateway.ClienteGateway;
import APP.Infra.Persistence.Entity.ClienteEntity;
import APP.Infra.Persistence.Entity.ContatoEntity;
import APP.Infra.Persistence.Entity.EnderecoEntity;
import APP.Infra.Persistence.Repository.ClienteRepository;
import APP.Infra.Persistence.Repository.ContatoRepository;
import APP.Infra.Persistence.Repository.EnderecoRepository;
import APP.Infra.Exceptions.IllegalActionException;
import APP.Util.ClienteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ClienteService implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ContatoRepository contatoRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.contatoRepository = contatoRepository;
        this.clienteMapper = clienteMapper;
    }

    Locale localBrasil = new Locale("pt", "BR");

    @Override
    public ResponseEntity<List<Cliente>> ListarClientes()
    {
        try
        {
            List<Cliente> response = new ArrayList<>();
            List<ClienteEntity> entities = clienteRepository.findAll();
            for(ClienteEntity entity : entities)
            {
                Cliente dto = clienteMapper.EntityToDto(entity);
                response.add(dto);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Cliente> BuscarClientesPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                ClienteEntity entity = clienteRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                Cliente response = clienteMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Cliente> BuscarClientesPorDocumento(Long documento)
    {
        try
        {
            if(documento != null)
            {
                ClienteEntity entity = clienteRepository.findBydocumento(documento).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                Cliente response = clienteMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Cliente> NovoCliente(String nome,
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
                                                          Double score)
    {
        try
        {
            if(score < 0){throw new IllegalActionException("valor do score Invalido");}
            if(nome != null &&
               sobrenome != null &&
                    documento != null &&
               dataNascimento != null &&
               numero != null &&
               cep != null &&
               prefixo != null &&
               telefone != null &&
               email != null)
            {
                ClienteEntity entity = new ClienteEntity();
                ContatoEntity contato = new ContatoEntity();
                EnderecoEntity endereco = new EnderecoEntity();
                CepResultDTO DTO = BuscaEndereco(cep);
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setNumero(numero);
                if(referencia != null){endereco.setReferencia(referencia);}
                endereco.setCep(cep);
                endereco.setCidade(DTO.localidade());
                endereco.setEstado(DTO.uf());
                endereco.setTimeStamp(LocalDateTime.now());
                contato.setPrefixo(prefixo);
                contato.setTelefone(telefone);
                contato.setEmail(email);
                contato.setTimeStamp(LocalDateTime.now());
                entity.setNome(nome);
                entity.setDocumento(documento);
                entity.setSobrenome(sobrenome);
                entity.setDataNascimento(dataNascimento);
                entity.setTimeStamp(LocalDateTime.now());
                entity.setEndereco(endereco);
                entity.setContato(contato);
                contatoRepository.save(contato);
                enderecoRepository.save(endereco);
                clienteRepository.save(entity);
                Cliente response = clienteMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public CepResultDTO BuscaEndereco(String cep)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            CepResultDTO DTO = restTemplate
                    .getForEntity(
                            String.format("https://viacep.com.br/ws/%s/json", cep),
                            CepResultDTO.class).getBody();
            return DTO;
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Cliente> EditarCliente(Long id,
                                                            String nome,
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
                                                            Double score)
    {
        try
        {
            if(score < 0){throw new IllegalActionException("valor do score Invalido");}
            if(id != null &&
            nome != null &&
            sobrenome != null && documento != null &&
            dataNascimento != null &&
            numero != null &&
            cep != null &&
            prefixo != null &&
            telefone != null &&
            email != null)
            {
                ClienteEntity entity = clienteRepository.findById(id).orElseThrow(
                    ()-> new EntityNotFoundException()
                );
                EnderecoEntity endereco = enderecoRepository.findById(entity.getEndereco().getId()).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                ContatoEntity contato = contatoRepository.findById(entity.getContato().getId()).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                entity.setNome(nome);
                entity.setSobrenome(sobrenome);
                entity.setDocumento(documento);
                entity.setDataNascimento(dataNascimento);
                CepResultDTO DTO = BuscaEndereco(cep);
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                if(referencia != null){endereco.setReferencia(referencia);}
                endereco.setNumero(numero);
                endereco.setCep(cep);
                endereco.setCidade(DTO.localidade());
                endereco.setEstado(DTO.uf());
                contato.setPrefixo(prefixo);
                contato.setTelefone(telefone);
                contato.setEmail(email);
                entity.setTimeStamp(LocalDateTime.now());
                endereco.setTimeStamp(LocalDateTime.now());
                contato.setTimeStamp(LocalDateTime.now());
                contatoRepository.save(contato);
                enderecoRepository.save(endereco);
                clienteRepository.save(entity);
               Cliente response = clienteMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);

            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public void DeletarClientesPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                ClienteEntity entity = clienteRepository.findById(id).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                enderecoRepository.deleteById(entity.getEndereco().getId());
                contatoRepository.deleteById(entity.getContato().getId());
                clienteRepository.deleteById(id);
            }
            else
            { throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

}
