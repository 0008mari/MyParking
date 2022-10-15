package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.domain.User;
import project.myparking.dto.ReviewDto;
import project.myparking.dto.ReviewUpdateDto;
import project.myparking.global.exception.NoDataException;
import project.myparking.repository.ParkingRepository;
import project.myparking.repository.ReviewRepository;
import project.myparking.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;

//    @Transactional
//    public void addReview(@NotNull ReviewDto dto) {
//
//        // 엔티티 조회
//        Parking parking = parkingRepository.findOne(dto.getParkingid());
//        User user = userRepository.findOne(dto.getUserid());
//
//        // 리뷰 생성
//        Review review = new Review();
//        review.setParking(parking);
//        review.setUser(user);
//
//        review.setEvalStaff(dto.getEvalStaff());
//        review.setEvalSpace(dto.getEvalSpace());
//        review.setEvalCostefficient(dto.getEvalCostefficient());
//        review.setEvalRevisit(dto.getEvalRevisit());
//        review.setEvalParkinglevel(dto.getEvalParkinglevel());
//        review.setStarScore(dto.getStarScore());
//
//        reviewRepository.save(review);
//    }

    @Transactional
    public void update(Long reviewId, ReviewUpdateDto dto) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new NoDataException());

        review.setEvalSpace(dto.getEvalSpace());
        review.setEvalParkinglevel(dto.getEvalParkinglevel());
        review.setEvalCostefficient(dto.getEvalCostefficient());
        review.setEvalStaff(dto.getEvalStaff());
        review.setEvalRevisit(dto.getEvalRevisit());
        review.setStarScore(dto.getStarScore());

        reviewRepository.save(review);
    }

    @Transactional
    public void delete (Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
               .orElseThrow(() -> new NoDataException());

        reviewRepository.delete(review);
    }

    public ReviewDto findByParkingCode(String parkingCode) {
        Review review = reviewRepository.findByParkingCode(parkingCode);
        return new ReviewDto(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDto> findReviewsByParkingId(Long parkingId){
        List<Review> reviewList = reviewRepository.findAllByParkingId(parkingId);
        if (reviewList.isEmpty()) {
            throw new NoDataException();
        }
        return reviewList.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }
    public User getReviewWriter(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(()-> new NoDataException()).getUser();
    }

    public ReviewDto findOne(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoDataException());
        return new ReviewDto(review);
    }

    public List<ReviewDto> getReviewsByUid(Long userId) {
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        if (reviewList.isEmpty()) {
            throw new NoDataException();
        }
        return reviewList.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }
}