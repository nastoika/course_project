package application.service.service_impl;

import application.entity.Question;
import application.entity.Test;
import application.repository.QuestionRepository;
import application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void saveQuestion(Question question, Test test) {
        question.setTest(test);
        questionRepository.save(question);
    }
}
