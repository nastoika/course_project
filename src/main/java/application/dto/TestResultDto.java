package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResultDto {
    private String login;
    private String test;
    private String correctAnswer;
    private String generalAmountAnswer;
}
