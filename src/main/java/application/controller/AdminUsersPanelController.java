package application.controller;

import application.dto.UserForAdminPanelDto;
import application.dto.UserStatusForAdminPanelDto;
import application.entity.Status;
import application.entity.User;
import application.mapper.UserMapper;
import application.service.StatusService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AdminUsersPanelController {

    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<UserForAdminPanelDto>> showAllUsers() {
        List<User> userList = userService.gelAllUsers();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserForAdminPanelDto> userForAdminPanelDtoList = userMapper.getUserForAdminPanelFromUser(userList);
        if (!userForAdminPanelDtoList.isEmpty()) {
            return new ResponseEntity<>(userForAdminPanelDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/admin/users")
    public HttpStatus blockOrUnblockUser(@RequestBody final UserStatusForAdminPanelDto userStatusDto) {
        if (userService.checkLoginForExists(userStatusDto.getLogin())) {
            Status userStatus = statusService.findStatusByStatusNameAndSaveIfDoesntExists(userStatusDto.getStatus());
            userService.changeUserStatusByLogin(userStatus, userStatusDto.getLogin());
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
