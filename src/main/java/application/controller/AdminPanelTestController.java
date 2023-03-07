package application.controller;

import application.dto.QuestionDto;
import application.dto.TestDto;
import application.entity.Question;
import application.entity.Test;
import application.entity.Topic;
import application.service.AnswerService;
import application.service.QuestionService;
import application.service.TestService;
import application.service.TopicService;
import application.validation.TestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdminPanelTestController {

    @Autowired
    private TestValidation testValidation;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;


    @PostMapping("/api/admin/add/test")
    public HttpStatus addTest(@RequestBody TestDto testDto) {
        if (testValidation.checkExistsOfTest(testDto.getTest().toUpperCase())) {
            Topic topic = topicService.findTopicByTopicName(testDto.getTopic().toUpperCase());
            testService.saveTest(testDto.getTest().toUpperCase(), topic);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping("/api/admin/add/question")
    public HttpStatus addQuestion(@RequestBody QuestionDto questionDto) {
        if (!testValidation.checkExistsOfTest(questionDto.getTest().toUpperCase())) {
            Test test = testService.findTestByTestName(questionDto.getTest().toUpperCase());
            Question question = new Question();
            question.setQuestion(questionDto.getQuestion());
            questionService.saveQuestion(question, test);
            answerService.saveAnswer(questionDto, question);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
