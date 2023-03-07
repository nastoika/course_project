package application.service.service_impl;

import application.entity.Role;
import application.entity.Status;
import application.entity.User;
import application.repository.UserRepository;
import application.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void findUserByLogin() {
        String login = "login";
        User user = new User();
        UserService userService = new UserServiceImpl(userRepository, passwordEncoder);

        Mockito.when(userRepository.findUserByLogin(login)).thenReturn(user);
        User returnedUser = userService.findUserByLogin(login);

        Mockito.verify(userRepository, Mockito.times(1)).findUserByLogin(login);
        assertEquals(user, returnedUser);
    }

    @Test
    void saveUser() {
        User user = new User();
        Role role = new Role();
        Status status = new Status();
        UserService userService = new UserServiceImpl(userRepository, passwordEncoder);

        userService.saveUser(user, role, status);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void updateLogin() {
        String oldLogin = "oldLogin";
        String newLogin = "newLogin";
        User user = new User();
        user.setLogin(oldLogin);
        UserService userService = new UserServiceImpl(userRepository, passwordEncoder);

        Mockito.when(userRepository.findUserByLogin(oldLogin)).thenReturn(user);
        userService.updateLogin(oldLogin, newLogin);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        assertEquals(newLogin, user.getLogin());
    }
}