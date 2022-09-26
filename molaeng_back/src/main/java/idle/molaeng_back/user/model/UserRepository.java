package idle.molaeng_back.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserId(long userId);

}
