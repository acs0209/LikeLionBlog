package sgdevcamp.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.dto.answer.AnswerForm;
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
}
