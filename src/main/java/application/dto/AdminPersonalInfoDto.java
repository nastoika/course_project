package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminPersonalInfoDto {
    private String oldLogin;
    private String newLogin;
    private String oldPassword;
    private String newPassword;
}
