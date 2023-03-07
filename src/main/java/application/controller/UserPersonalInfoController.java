package application.controller;

import application.dto.AdminPersonalInfoDto;
import application.dto.UserForUpdateInfoDto;
import application.entity.User;
import application.mapper.UserMapper;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserPersonalInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/user/update/{login}")
    public ResponseEntity<UserForUpdateInfoDto> getUsersInformation(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        if (user != null) {
            UserForUpdateInfoDto userForUpdateInfoDto = userMapper.getUserForUpdateDtpFromUser(user);
            return new ResponseEntity<>(userForUpdateInfoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/user/update")
    public HttpStatus updateUserInformation(@RequestBody UserForUpdateInfoDto userForUpdateInfoDto) {
        if (userService.checkLoginForExists(userForUpdateInfoDto.getLogin())) {
            User user = userService.findUserByLogin(userForUpdateInfoDto.getLogin());
            userService.updateUser(user, userForUpdateInfoDto);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping("/api/user/updatePassword")
    public HttpStatus updateUserPassword(@RequestBody final AdminPersonalInfoDto userDto) {
        if (userService.checkLoginForExists(userDto.getOldLogin()) &&
                userService.checkCorrectPassword(userDto.getOldLogin(), userDto.getOldPassword())) {
            userService.updatePassword(userDto.getOldLogin(),
                    userDto.getNewPassword());
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
