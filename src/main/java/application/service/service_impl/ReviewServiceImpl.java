package application.service.service_impl;

import application.entity.Result;
import application.entity.Review;
import application.repository.ReviewRepository;
import application.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(Result result, String reviewText) {
        Review review = new Review();
        review.setResult(result);
        review.setReview(reviewText);
        reviewRepository.save(review);
    }
}
