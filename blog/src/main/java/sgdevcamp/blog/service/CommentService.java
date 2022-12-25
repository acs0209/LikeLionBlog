package sgdevcamp.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Comment;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.data.repository.CommentRepository;
import sgdevcamp.blog.dto.request.CommentForm;
import sgdevcamp.blog.exception.DataNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Answer answer, SiteUser siteUser ,String content) {
        Comment c = new Comment();
        c.setAnswer(answer);
        c.setSiteUser(siteUser);
        c.setContent(content);
        c.setCreateDate(LocalDateTime.now());
        c = this.commentRepository.save(c);
        return c;
    }

    public Comment getComment(Long id) {
        Optional<Comment> c = this.commentRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public Long modify(Long id, CommentForm commentForm) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            comment.get().setContent(commentForm.getContent());
            comment.get().setModifyDate(LocalDateTime.now());
            this.commentRepository.save(comment.get());
        }
        else {
            throw new DataNotFoundException("comment not found");
        }

        return comment.get().getQuestionId();
    }

    public Long delete(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            this.commentRepository.delete(comment.get());
        }
        else {
            throw new DataNotFoundException("comment not found");
        }
        return comment.get().getQuestionId();
    }

}