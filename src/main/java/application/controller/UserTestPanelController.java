package application.controller;

import application.dto.TestDto;
import application.dto.TestQuestionDto;
import application.dto.TestResultDto;
import application.entity.Test;
import application.entity.Topic;
import application.entity.User;
import application.mapper.TestMapper;
import application.service.ResultService;
import application.service.TestService;
import application.service.TopicService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserTestPanelController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;

    @PostMapping("/api/user/test")
    public ResponseEntity<List<TestDto>> getTests(@RequestBody TestDto testDto) {
        Topic topic = topicService.findTopicByTopicName(testDto.getTopic().toUpperCase());
        if (topic != null) {
            List<TestDto> testDtos = TestMapper.getTestDtoListFromTestList(topic.getTestList());
            if (!testDtos.isEmpty()) {
                return new ResponseEntity<>(testDtos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/user/test/question")
    public ResponseEntity<List<TestQuestionDto>> getTestQuestions(@RequestBody TestDto testDto) {
        Test test = testService.findTestByTestName(testDto.getTest().toUpperCase());
        List<TestQuestionDto> questionDtos = TestMapper.getTestQuestionsFromTest(test.getQuestionList());
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @PostMapping("/api/user/test/result")
    public ResponseEntity<String> getTestResult(@RequestBody TestResultDto testResultDto) {
        Test test = testService.findTestByTestName(testResultDto.getTest().toUpperCase());
        User user = userService.findUserByLogin(testResultDto.getLogin());
        double userResult = (Double.parseDouble(testResultDto.getCorrectAnswer())
                / Double.parseDouble(testResultDto.getGeneralAmountAnswer())) * 100;
        resultService.save(test, user, userResult);
        String resultToSending = String.valueOf(userResult);
        return new ResponseEntity<>(resultToSending, HttpStatus.OK);
    }
}
