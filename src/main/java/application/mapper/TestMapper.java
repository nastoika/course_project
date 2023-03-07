package application.mapper;

import application.dto.AnswerAndCorrectDto;
import application.dto.QuestionDto;
import application.dto.TestDto;
import application.dto.TestQuestionDto;
import application.entity.Answer;
import application.entity.Question;
import application.entity.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMapper {

    public static List<TestDto> getTestDtoListFromTestList(List<Test> testList) {
        List<TestDto> testDtos = new ArrayList<>();
        for (Test test : testList) {
            TestDto testDto = new TestDto();
            testDto.setTest(test.getTest());
            testDtos.add(testDto);
        }
        return testDtos;
    }

    public static List<TestQuestionDto> getTestQuestionsFromTest(List<Question> questionList) {
        List<TestQuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList) {
            TestQuestionDto questionDto = new TestQuestionDto();
            questionDto.setQuestion(question.getQuestion());
            questionDto.setAnswerAndCorrectDtos(getAnswerAndCorrectFromAnswerList(question.getAnswerList()));
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    private static List<AnswerAndCorrectDto> getAnswerAndCorrectFromAnswerList(List<Answer> answers) {
        List<AnswerAndCorrectDto> answerAndCorrectDtos = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerAndCorrectDto answerAndCorrectDto = new AnswerAndCorrectDto();
            answerAndCorrectDto.setAnswer(answer.getAnswer());
            answerAndCorrectDto.setCorrectly(String.valueOf(answer.getCorrectly()));
            answerAndCorrectDtos.add(answerAndCorrectDto);
        }
        return answerAndCorrectDtos;
    }
}
