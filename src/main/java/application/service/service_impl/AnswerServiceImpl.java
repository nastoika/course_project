package application.service.service_impl;

import application.dto.QuestionDto;
import application.entity.Answer;
import application.entity.Question;
import application.repository.AnswerRepository;
import application.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void saveAnswer(QuestionDto questionDto, Question question) {
        Answer answer1 = new Answer(questionDto.getAnswer1(), questionDto.getCorrect1().equals("1"), question);
        Answer answer2 = new Answer(questionDto.getAnswer2(), questionDto.getCorrect2().equals("1"), question);
        Answer answer3 = new Answer(questionDto.getAnswer3(), questionDto.getCorrect3().equals("1"), question);
        Answer answer4 = new Answer(questionDto.getAnswer4(), questionDto.getCorrect4().equals("1"), question);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
    }
}
