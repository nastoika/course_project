package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String experience;
    private String position;
}
