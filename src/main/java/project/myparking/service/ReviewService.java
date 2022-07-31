package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.domain.User;
import project.myparking.repository.ParkingRepository;
import project.myparking.repository.ReviewRepository;
import project.myparking.repository.UserRepository;
import project.myparking.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;

    @Transactional
    public void addReview(Long parkingId, @NotNull ReviewDto dto) {

        // 엔티티 조회
        Parking parking = parkingRepository.findOne(parkingId);
        User user = userRepository.findOne(dto.getUserid());

        // 리뷰 생성
        Review review = new Review();
        review.setParking(parking);
        review.setUser(user);

        review.setEvalStaff(dto.getEvalStaff());
        review.setEvalSpace(dto.getEvalSpace());
        review.setEvalCostefficient(dto.getEvalCostefficient());
        review.setEvalRevisit(dto.getEvalRevisit());
        review.setEvalParkinglevel(dto.getEvalParkinglevel());
        review.setStarScore(dto.getStarScore());

        reviewRepository.save(review);
    }

    @Transactional
    public void update(Long reviewId, ReviewUpdateDto dto) {
        Review review = reviewRepository.findOne(reviewId);
               // .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        review.setEvalSpace(dto.getEvalSpace());
        review.setEvalParkinglevel(dto.getEvalParkinglevel());
        review.setEvalCostefficient(dto.getEvalCostefficient());
        review.setEvalStaff(dto.getEvalStaff());
        review.setEvalRevisit(dto.getEvalRevisit());
        review.setStarScore(dto.getStarScore());

        // UPDATE 시에 merge 사용은 BAD
        reviewRepository.save(review);
    }

    @Transactional
    public void delete (Long reviewId) {
        Review review = reviewRepository.findOne(reviewId);
               // .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviewRepository.delete(review);
    }

    public ReviewDto findByPCode(String parkingCode) {

        Review review = reviewRepository.findByPCode(parkingCode);
               // .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReviewDto(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDto> findReviewsByPid(Long parkingid){
        List<Review> reviewList = reviewRepository.findReviewsByPid(parkingid);
            //  .orElseThrow(() -> new NotFoundException(parkingid));

        return reviewList.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }
    public List<ReviewDto> findAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());

    }

    public User findReviewWriter(Long reviewid){
        return reviewRepository.findWriter(reviewid);
    }
}