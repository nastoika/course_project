package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReviewDto {
    private String login;
    private String topic;
    private String test;
    private String result;
    private String review;
}
