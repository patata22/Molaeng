package idle.molaeng_back.user.model;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GugunRepository extends JpaRepository<Gugun, Long> {
    Gugun findByGugunName(String gugunName);
}
