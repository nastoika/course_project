package application.controller;

import application.dto.AdminResultDto;
import application.entity.User;
import application.mapper.UserMapper;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdminResultController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/result/{login}")
    public ResponseEntity<List<AdminResultDto>> getUsersResult(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        if (user.getResultList().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AdminResultDto> userForAdminPanelDtoList = userMapper.getAdminResultDtoFromUser(user);
        if (!userForAdminPanelDtoList.isEmpty()) {
            return new ResponseEntity<>(userForAdminPanelDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
