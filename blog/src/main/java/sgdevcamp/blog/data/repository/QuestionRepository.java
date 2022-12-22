package sgdevcamp.blog.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.data.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

        Question findBySubject(String subject);

        Question findBySubjectAndContent(String subject, String content);

        List<Question> findBySubjectLike(String subject);

        Page<Question> findAll(Pageable pageable);

}