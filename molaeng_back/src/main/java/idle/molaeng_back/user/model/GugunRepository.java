package idle.molaeng_back.user.model;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Long> {
    Gugun findByGugunId(long gugunId);

}

