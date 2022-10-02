package idle.molaeng_back.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserId(long userId);

    int countUserByUuid(String uuid);

    User findByUuid(String uuid);

}
