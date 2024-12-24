package App.Domain.Bussness;

import App.Domain.Response.Transacao;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.TransacaoGateway;
import App.Infra.Persistence.Repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public ResponseEntity<Transacao> novoSaque(Long documento, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(documento != null && valor != null)
            {
               //busca cliente

               //verifica dados

               //cria entidade

               //salva entidade

               //response
               return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<Transacao> novoDeposito(Long documentoPagador,Long documentoBeneficiario, Double valor)
    {
        try
        {
            if(valor < 0){throw new IllegalActionException();}
            if(documentoPagador != null && documentoBeneficiario != null && valor != null)
            {

                //busca pagador

                //verifica dados

                //busca beneficiario

                //verifica dados

                //cria entidade

                //salva entidade

                //response
                return new ResponseEntity<>(HttpStatus.OK);
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

}
