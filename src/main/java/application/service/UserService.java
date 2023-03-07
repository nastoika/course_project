package application.service;

import application.dto.UserForUpdateInfoDto;
import application.entity.Result;
import application.entity.Role;
import application.entity.Status;
import application.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserByLogin(String login);

    void saveUser(User userFromUserDto, Role role, Status status);

    void updateLogin(String oldLogin, String newLogin);

    boolean checkCorrectPassword(String oldLogin, String oldPassword);

    void updatePassword(String oldLogin, String newPassword);

    void changeUserStatusByLogin(Status userStatus, String login);

    List<User> gelAllUsers();

    int findUserRating(User user);

    void updateUser(User user, UserForUpdateInfoDto userForUpdateInfoDto);

    String getUserRating(List<Result> resultList);

    boolean checkLoginForExists(String login);
}
