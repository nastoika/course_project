package application.controller;

import application.dto.AdminResultDto;
import application.dto.UserStatusForAdminPanelDto;
import application.entity.Result;
import application.entity.Review;
import application.entity.Test;
import application.entity.User;
import application.service.ResultService;
import application.service.TestService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserReviewPanelController {

    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private TestService testService;


    /*
     * Вывыодит общий рейтинг юзера
     * */
    @PostMapping("/api/user/rating")
    public ResponseEntity<String> getUserRating(@RequestBody UserStatusForAdminPanelDto login) {
        if (userService.checkLoginForExists(login.getLogin())) {
            User user = userService.findUserByLogin(login.getLogin());
            String rating = userService.getUserRating(user.getResultList());
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * Выводит отзывы по тестам
     * */
    @PostMapping("/api/user/review")
    public ResponseEntity<List<String>> getUserReviewByLogin(@RequestBody AdminResultDto adminResultDto) {
        if (userService.checkLoginForExists(adminResultDto.getLogin())) {
            User user = userService.findUserByLogin(adminResultDto.getLogin());
            Test test = testService.findTestByTestName(adminResultDto.getTest().toUpperCase());
            Result result = resultService.findResultByUserAndTestAndResult(user, test, Double.parseDouble(adminResultDto.getResult()));
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<String> reviewList = new ArrayList<>();
            for (Review review : result.getReviewList()) {
                String reviewText;
                reviewText = review.getReview();
                reviewList.add(reviewText);
            }
            if (!reviewList.isEmpty()) {
                return new ResponseEntity<>(reviewList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
