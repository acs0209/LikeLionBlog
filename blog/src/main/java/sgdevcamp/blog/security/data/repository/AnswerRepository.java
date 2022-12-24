package sgdevcamp.blog.security.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.security.data.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {



}
