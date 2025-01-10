package App.Infra.Persistence.Repository;

import App.Infra.Persistence.Entity.AuthEntity;
import org.hibernate.query.criteria.JpaFetchParent;
import org.hibernate.sql.exec.spi.JdbcCallParameterExtractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
}
