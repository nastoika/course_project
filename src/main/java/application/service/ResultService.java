package application.service;

import application.entity.Result;
import application.entity.Test;
import application.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface ResultService {
    Result findResultByUserAndTestAndResult(User user, Test test, Double rating);

    void save(Test test, User user, double userResult);
}
