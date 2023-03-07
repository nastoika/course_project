package application.mapper;

import application.dto.UserRegistrationDto;
import application.entity.User;
import application.entity.UserInfo;

public class UserRegistrationMapper {
    public static User getUserFromUserDto(UserRegistrationDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userDto.getName());
        userInfo.setSurname(userDto.getSurname());
        userInfo.setPatronymic(userDto.getPatronymic());
        userInfo.setExperience(Integer.valueOf(userDto.getExperience()));
        userInfo.setPosition(userDto.getPosition());
        user.setUserInfoId(userInfo);
        return user;
    }
}
