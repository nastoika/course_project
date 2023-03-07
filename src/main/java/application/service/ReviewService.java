package application.service;

import application.entity.Result;
import application.entity.Review;
import application.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    void save(Result result, String review);
}
