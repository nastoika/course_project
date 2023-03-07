package application.controller;

import application.dto.AdminReviewDto;
import application.entity.Result;
import application.entity.Test;
import application.entity.User;
import application.service.ResultService;
import application.service.ReviewService;
import application.service.TestService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdminReviewController {

    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/admin/review")
    public ResponseEntity putUserReview(@RequestBody AdminReviewDto adminReviewDto) {
        User user = userService.findUserByLogin(adminReviewDto.getLogin());
        Test test = testService.findTestByTestName(adminReviewDto.getTest().toUpperCase());
        Result result = resultService.findResultByUserAndTestAndResult(user, test, Double.parseDouble(adminReviewDto.getRating()));
        if (user == null || test == null || result == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            reviewService.save(result, adminReviewDto.getReview());
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
}

