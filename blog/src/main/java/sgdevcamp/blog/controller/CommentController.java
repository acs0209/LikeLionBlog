package sgdevcamp.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Comment;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.dto.request.CommentForm;
import sgdevcamp.blog.service.AnswerService;
import sgdevcamp.blog.service.CommentService;
import sgdevcamp.blog.service.QuestionService;
import sgdevcamp.blog.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping(value = "/create/answer/{id}")
    public String createAnswerComment(CommentForm commentForm) {
        return "comment_form";
    }

    @PostMapping("/create/answer/{id}")
    public String createAnswerComment(@PathVariable("id") Long id, @Valid CommentForm commentForm, BindingResult bindingResult) {
        //Question question = this.questionService.getQuestion(id);
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(commentForm.getUsername());
        if (bindingResult.hasErrors()) {
            return "comment_form";
        }
        Comment c = this.commentService.create(answer, siteUser ,commentForm.getContent());
        return String.format("redirect:/question/detail/%s", c.getQuestionId());
    }

    @GetMapping("/modify/{id}")
    public String modifyComment(CommentForm commentForm, @PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);

         commentForm.setContent(comment.getContent());
        return "comment_form";
    }

    @PostMapping("/modify/{id}")
    public String modifyComment(@Valid CommentForm commentForm, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "comment_form";
        }

        Comment comment = this.commentService.getComment(id);
        comment = this.commentService.modify(comment, commentForm.getContent());
        return String.format("redirect:/question/detail/%s", comment.getQuestionId());
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);

        this.commentService.delete(comment);
        return String.format("redirect:/question/detail/%s", comment.getQuestionId());
    }

}