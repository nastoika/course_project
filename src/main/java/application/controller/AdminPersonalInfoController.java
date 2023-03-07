package application.controller;

import application.dto.AdminPersonalInfoDto;
import application.entity.User;
import application.security.JwtTokenProvider;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdminPersonalInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/updateLogin")
    public ResponseEntity<String> updateAdminLogin(@RequestBody AdminPersonalInfoDto adminDto) {
        if (userService.checkLoginForExists(adminDto.getOldLogin()) &&
                !userService.checkLoginForExists(adminDto.getNewLogin())) {
            User user = userService.findUserByLogin(adminDto.getOldLogin());
            String token = jwtTokenProvider.createToken(adminDto.getNewLogin(), user.getRole().getRoleName());
            userService.updateLogin(adminDto.getOldLogin(), adminDto.getNewLogin());
            UserDetails userDetails = userDetailsService.loadUserByUsername(adminDto.getNewLogin());
            Authentication a = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/admin/updatePassword")
    public HttpStatus updateAdminPassword(@RequestBody final AdminPersonalInfoDto adminDto) {
        if (userService.checkLoginForExists(adminDto.getOldLogin()) &&
                userService.checkCorrectPassword(adminDto.getOldLogin(), adminDto.getOldPassword())) {
            userService.updatePassword(adminDto.getOldLogin(),
                    adminDto.getNewPassword());
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
