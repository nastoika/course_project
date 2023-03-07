package application.service.service_impl;

import application.dto.QuestionDto;
import application.entity.Answer;
import application.entity.Question;
import application.repository.AnswerRepository;
import application.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private AnswerRepository answerRepository;

    @Test
    void saveAnswer() {
        Question question = new Question();
        QuestionDto questionDto = new QuestionDto();
        questionDto.setAnswer1("a");
        questionDto.setAnswer2("b");
        questionDto.setAnswer3("c");
        questionDto.setAnswer4("d");
        questionDto.setCorrect1("0");
        questionDto.setCorrect2("0");
        questionDto.setCorrect3("0");
        questionDto.setCorrect4("1");
        AnswerService answerService = new AnswerServiceImpl(answerRepository);

        answerService.saveAnswer(questionDto, question);

        Mockito.verify(answerRepository, Mockito.times(4)).save(Mockito.any(Answer.class));
    }
}