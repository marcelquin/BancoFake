package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.AcountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcountRepository extends JpaRepository<AcountEntity,Long> {

    Optional<AcountEntity> findByacount(String acount);
}
