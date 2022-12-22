package sgdevcamp.blog;

import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.repository.AnswerRepository;
import sgdevcamp.blog.data.repository.QuestionRepository;
import sgdevcamp.blog.service.QuestionService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpaQuestion() {

		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Optional<Question> question = this.questionRepository.findById(1L);
		if(question.isPresent()) {
			Question q = question.get();
			assertEquals("sbb gogogo", q.getContent());
		}

		Question question1 = this.questionRepository.findBySubject("sbb is what");
		assertEquals(1L, question1.getId());

		Question question2 = this.questionRepository.findBySubjectAndContent("ohho", "hihihihihii");
		assertEquals(2L, question2.getId());

		List<Question> questionList = this.questionRepository.findBySubjectLike("sbb%");
		Question question3 = questionList.get(0);
		assertEquals(1L, question3.getId());

		Optional<Question> oq = this.questionRepository.findById(1L);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);

	}

	@Autowired
	private AnswerRepository answerRepository;

	@Transactional
	@Test
	void testJpaAnswer() {

		Optional<Question> question = questionRepository.findById(1L);
//		assertTrue(question.isPresent());
//		Question q = question.get();
//		Answer answer1 = new Answer();
//		answer1.setContent("hello");
//		answer1.setCreateDate(LocalDateTime.now());
//		answer1.setQuestion(q);
//		this.answerRepository.save(answer1);
//		Answer answer2 = new Answer();
//		answer2.setContent("hi");
//		answer2.setCreateDate(LocalDateTime.now());
//		answer2.setQuestion(q);
//		this.answerRepository.save(answer2);

		assertEquals(4, answerRepository.findAll().size());
		Optional<Answer> answer = answerRepository.findById(3L);
		assertTrue(answer.isPresent());
		Answer answer3 = answer.get();
		assertEquals(3, answer3.getId());

		assertTrue(question.isPresent());
		Question question1 = question.get();
		List<Answer> answers = question1.getAnswerList();
		Answer answer1 = answers.get(0);

		answer1.getQuestion();
	}

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpaQuestionService() {
		for (int i = 1; i <= 200; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}
	}


}
