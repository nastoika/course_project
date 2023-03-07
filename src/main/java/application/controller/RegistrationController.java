package application.controller;

import application.dto.UserRegistrationDto;
import application.entity.Role;
import application.entity.Status;
import application.enums.RoleEnum;
import application.enums.StatusEnum;
import application.mapper.UserRegistrationMapper;
import application.service.RoleService;
import application.service.StatusService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080")
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final StatusService statusService;

    @Autowired
    public RegistrationController(UserService userService,
                                  RoleService roleService,
                                  StatusService statusService) {
        this.userService = userService;
        this.roleService = roleService;
        this.statusService = statusService;
    }

    @PostMapping("/api/registration")
    public HttpStatus registrationUser(@RequestBody final UserRegistrationDto userDto) {
        if (userService.checkLoginForExists(userDto.getLogin())) {
            return HttpStatus.BAD_REQUEST;
        } else {
            Role role = roleService.findRoleByRoleNameAndSaveIfDoesntExists(RoleEnum.USER.name());
            Status status = statusService.findStatusByStatusNameAndSaveIfDoesntExists(StatusEnum.ACTIVE.name());
            userService.saveUser(UserRegistrationMapper.getUserFromUserDto(userDto), role, status);
            return HttpStatus.OK;
        }
    }
}
