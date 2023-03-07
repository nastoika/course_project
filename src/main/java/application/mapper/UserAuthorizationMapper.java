package application.mapper;

import application.dto.UserAuthDto;
import application.entity.User;

public class UserAuthorizationMapper {
    public static UserAuthDto getUserAuthorizationDtoFromUser(User authorizeUser) {
        UserAuthDto authorizationDto = new UserAuthDto();
        authorizationDto.setLogin(authorizeUser.getLogin());
        authorizationDto.setRole(authorizeUser.getRole().getRoleName());
        authorizationDto.setStatus(authorizeUser.getStatus().getStatusName());
        return authorizationDto;
    }
}
