package application.service;

import application.dto.QuestionDto;
import application.entity.Question;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    void saveAnswer(QuestionDto questionDto, Question question);
}
