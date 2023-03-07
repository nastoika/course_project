package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthDto {
    private String login;
    private String role;
    private String status;
}
