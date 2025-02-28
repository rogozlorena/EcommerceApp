package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.ReviewDTO;
import com.shop.ecommerce.dtos.builders.ReviewBuilder;
import com.shop.ecommerce.entities.Review;
import com.shop.ecommerce.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        // Convert DTO to entity using the builder
        Review review = ReviewBuilder.toEntity(reviewDTO);

        // Save the review entity to the database
        Review savedReview = reviewRepository.save(review);

        // Return the saved review as a DTO
        return ReviewBuilder.toReviewDTO(savedReview);
    }

    // Get Review by ID
    public ReviewDTO getReviewById(long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            return ReviewBuilder.toReviewDTO(reviewOptional.get());
        } else {
            throw new IllegalArgumentException("Review with ID " + id + " not found");
        }
    }


    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewBuilder::toReviewDTO)
                .collect(Collectors.toList());
    }


    public ReviewDTO updateReview(long id, ReviewDTO reviewDTO) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            // Update the review's fields
            Review review = reviewOptional.get();
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());
            review.setReviewDate(reviewDTO.getReviewDate());

            // Save the updated review entity
            Review updatedReview = reviewRepository.save(review);

            // Return the updated review as DTO
            return ReviewBuilder.toReviewDTO(updatedReview);
        } else {
            throw new IllegalArgumentException("Review with ID " + id + " not found");
        }
    }


    public void deleteReview(long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Review with ID " + id + " not found");
        }
    }
}
