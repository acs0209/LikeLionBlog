package sgdevcamp.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgdevcamp.blog.security.data.entity.Answer;
import sgdevcamp.blog.security.data.entity.Question;
import sgdevcamp.blog.security.data.entity.SiteUser;
import sgdevcamp.blog.security.data.repository.AnswerRepository;
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

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

}