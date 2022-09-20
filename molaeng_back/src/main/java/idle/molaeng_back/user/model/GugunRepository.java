package idle.molaeng_back.user.model;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GugunRepository extends JpaRepository<Gugun, Integer> {
    Gugun findByGugunName(String gugunName);
}
