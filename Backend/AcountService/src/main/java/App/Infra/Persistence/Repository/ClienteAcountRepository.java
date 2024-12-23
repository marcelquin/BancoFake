package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.ClientAcountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteAcountRepository extends JpaRepository<ClientAcountEntity,Long> {
}
