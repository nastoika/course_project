package application.mapper;

import application.dto.AdminResultDto;
import application.dto.UserForAdminPanelDto;
import application.dto.UserForUpdateInfoDto;
import application.entity.Result;
import application.entity.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private UserService userService;

    public List<UserForAdminPanelDto> getUserForAdminPanelFromUser(List<User> userList) {
        List<UserForAdminPanelDto> userForAdminPanelDtoList = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole().getRoleName().equals("ADMIN")) {
                continue;
            } else {
                UserForAdminPanelDto userForAdminPanelDto = new UserForAdminPanelDto();

                userForAdminPanelDto.setLogin(user.getLogin());
                userForAdminPanelDto.setName(user.getUserInfoId().getName());
                userForAdminPanelDto.setSurname(user.getUserInfoId().getSurname());
                userForAdminPanelDto.setPatronymic(user.getUserInfoId().getPatronymic());
                userForAdminPanelDto.setExperience(String.valueOf(user.getUserInfoId().getExperience()));
                userForAdminPanelDto.setPosition(user.getUserInfoId().getPosition());
                userForAdminPanelDto.setRating(String.valueOf(userService.findUserRating(user)));
                userForAdminPanelDto.setStatus(user.getStatus().getStatusName());

                userForAdminPanelDtoList.add(userForAdminPanelDto);
            }
        }
        return userForAdminPanelDtoList;
    }

    public List<AdminResultDto> getAdminResultDtoFromUser(User user) {
        List<AdminResultDto> adminResultDtoList = new ArrayList<>();
        List<Result> results = user.getResultList();
        for (Result result : results) {
            AdminResultDto adminResultDto = new AdminResultDto();
            adminResultDto.setLogin(user.getLogin());
            adminResultDto.setTopic(result.getTest().getTopic().getTopic());
            adminResultDto.setTest(result.getTest().getTest());
            adminResultDto.setResult(String.valueOf(result.getResult()));
            adminResultDtoList.add(adminResultDto);
        }
        return adminResultDtoList;
    }

    public UserForUpdateInfoDto getUserForUpdateDtpFromUser(User user) {
        UserForUpdateInfoDto userForUpdateInfoDto = new UserForUpdateInfoDto();
        userForUpdateInfoDto.setName(user.getUserInfoId().getName());
        userForUpdateInfoDto.setSurname(user.getUserInfoId().getSurname());
        userForUpdateInfoDto.setPatronymic(user.getUserInfoId().getPatronymic());
        userForUpdateInfoDto.setPosition(user.getUserInfoId().getPosition());
        userForUpdateInfoDto.setExperience(String.valueOf(user.getUserInfoId().getExperience()));
        return userForUpdateInfoDto;
    }
}
