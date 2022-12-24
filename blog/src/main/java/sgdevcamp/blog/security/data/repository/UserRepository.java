package sgdevcamp.blog.security.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.security.data.entity.SiteUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByusername(String username);

}
