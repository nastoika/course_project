package application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestQuestionDto {
    private String question;
    private List<AnswerAndCorrectDto> answerAndCorrectDtos;
}
