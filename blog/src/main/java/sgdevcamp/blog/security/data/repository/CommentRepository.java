package sgdevcamp.blog.security.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.security.data.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
