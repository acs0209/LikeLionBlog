package sgdevcamp.blog.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sgdevcamp.blog.data.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
