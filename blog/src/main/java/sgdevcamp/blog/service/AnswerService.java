package sgdevcamp.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.data.repository.AnswerRepository;
import sgdevcamp.blog.dto.request.AnswerForm;
import sgdevcamp.blog.exception.DataNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser user) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answer.setSiteUser(user);
        this.answerRepository.save(answer);
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return  answer.get();
        }
        else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public Long modify(Long id, AnswerForm answerForm) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            answer.get().setContent(answerForm.getContent());
            answer.get().setModifyDate(LocalDateTime.now());
            this.answerRepository.save(answer.get());
        }
        else {
            throw new DataNotFoundException("answer not found");
        }

        return answer.get().getQuestion().getId();

    }

    public Long delete(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            this.answerRepository.delete(answer.get());
        }
        else {
            throw new DataNotFoundException("answer not found");
        }
        return answer.get().getQuestion().getId();

    }

}