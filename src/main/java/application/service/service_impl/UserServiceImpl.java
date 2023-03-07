package application.service.service_impl;

import application.dto.UserForUpdateInfoDto;
import application.entity.*;
import application.repository.UserRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void saveUser(User user, Role role, Status status) {
        user.setRole(role);
        user.setStatus(status);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateLogin(String oldLogin, String newLogin) {
        User user = userRepository.findUserByLogin(oldLogin);
        user.setLogin(newLogin);
        userRepository.save(user);
    }

    @Override
    public boolean checkCorrectPassword(String oldLogin, String oldPassword) {
        User user = userRepository.findUserByLogin(oldLogin);
        String currentPassword = user.getPassword();
        return passwordEncoder.matches(oldPassword,currentPassword);
    }

    @Override
    public void updatePassword(String oldLogin, String newPassword) {
        User user = userRepository.findUserByLogin(oldLogin);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changeUserStatusByLogin(Status userStatus, String login) {
        User user = userRepository.findUserByLogin(login);
        user.setStatus(userStatus);
        userRepository.save(user);
    }

    @Override
    public List<User> gelAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public int findUserRating(User user) {
        OptionalDouble rating = user.getResultList().stream().mapToDouble(Result::getResult).average();
        if (rating.isPresent()) {
            return (int) rating.getAsDouble() / 10;
        } else return 0;
    }

    @Override
    public void updateUser(User user, UserForUpdateInfoDto userForUpdateInfoDto) {
        UserInfo userInfo = user.getUserInfoId();
        userInfo.setName(userForUpdateInfoDto.getName());
        userInfo.setSurname(userForUpdateInfoDto.getSurname());
        userInfo.setPatronymic(userForUpdateInfoDto.getPatronymic());
        userInfo.setExperience(Integer.valueOf(userForUpdateInfoDto.getExperience()));
        userInfo.setPosition(userForUpdateInfoDto.getPosition());
        user.setUserInfoId(userInfo);
        userRepository.save(user);
    }

    @Override
    public String getUserRating(List<Result> resultList) {
        OptionalDouble rating = resultList.stream().mapToDouble(Result::getResult).average();
        if (rating.isPresent()) {
            double result = Math.round(rating.getAsDouble());
            return String.valueOf(result / 10);
        } else {
            return "0";
        }
    }

    @Override
    public boolean checkLoginForExists(String login) {
        return userRepository.findUserByLogin(login) != null;
    }
}
