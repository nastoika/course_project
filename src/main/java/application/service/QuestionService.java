package application.service;

import application.entity.Question;
import application.entity.Test;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    void saveQuestion(Question question, Test test);
}
