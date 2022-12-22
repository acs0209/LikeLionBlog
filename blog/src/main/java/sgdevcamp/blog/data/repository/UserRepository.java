package sgdevcamp.blog.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.data.entity.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {



}
