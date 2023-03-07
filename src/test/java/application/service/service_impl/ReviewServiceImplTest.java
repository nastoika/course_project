package application.service.service_impl;

import application.entity.Result;
import application.entity.Review;
import application.repository.ReviewRepository;
import application.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    void save() {
        Result result = new Result();
        String reviewText = "review";
        ReviewService reviewService = new ReviewServiceImpl(reviewRepository);

        reviewService.save(result, reviewText);

        Mockito.verify(reviewRepository, Mockito.times(1)).save(Mockito.any(Review.class));
    }
}