package sgdevcamp.blog.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.data.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {



}
