package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForAdminPanelDto {
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private String experience;
    private String position;
    private String rating;
    private String status;
}
