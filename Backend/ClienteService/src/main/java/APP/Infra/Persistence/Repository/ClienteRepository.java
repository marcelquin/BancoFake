package APP.Infra.Persistence.Repository;

import APP.Infra.Persistence.Entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findBynome(String nome);
    Optional<ClienteEntity> findBydocumento(Long documento);
}
