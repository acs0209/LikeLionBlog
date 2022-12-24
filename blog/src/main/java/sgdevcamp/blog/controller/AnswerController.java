package sgdevcamp.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.dto.request.AnswerForm;
import sgdevcamp.blog.service.AnswerService;
import sgdevcamp.blog.service.QuestionService;
import sgdevcamp.blog.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, String username) {
        Question question = questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(username);

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        if (siteUser == null) {
            return String.format("redirect:/question/detail/%s", id);
        }

        this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }

    @GetMapping("/modify/{id}")
    public String  answerModify(AnswerForm answerForm, @PathVariable("id") Long id) {

        Answer answer = this.answerService.getAnswer(id);

        answerForm.setContent(answer.getContent()); // 답변 수정시 기존의 내용이 필요하므로 AnswerForm 객체에 조회한 값을 저장
        return "answer_form";
    }

    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }

        Answer answer = this.answerService.getAnswer(id);
        this.answerService.modify(answer, answerForm.getContent());

        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable("id") Long id) {
        Answer answer = this.answerService.getAnswer(id);
        this.answerService.delete(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

}
