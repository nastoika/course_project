package application.service.service_impl;

import application.entity.Question;
import application.repository.QuestionRepository;
import application.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Test
    void saveQuestion() {
        Question question = new Question();
        application.entity.Test test = new application.entity.Test();
        QuestionService questionService = new QuestionServiceImpl(questionRepository);

        questionService.saveQuestion(question, test);

        Mockito.verify(questionRepository, Mockito.times(1)).save(Mockito.any(Question.class));
    }
}