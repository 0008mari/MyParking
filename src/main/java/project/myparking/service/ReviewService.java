package project.myparking.service;

import lombok.RequiredArgsConstructor;
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
    private final ReviewRepository reviewsRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;

    /** 리뷰 */
    @Transactional
    public Long review(Long parkingId, Long userId) {

        // 엔티티 조회
        Parking parking = parkingRepository.findOne(parkingId);
        User user = userRepository.findOne(userId);

        // 리뷰 생성
        Review review = Review.createReview(user, parking, );

        return reviewsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewUpdateRequest request) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviews.update(request.getStarScore());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviewsRepository.delete(reviews);
    }

    public List<ReviewDto> findByParkingCode(String parkingCode) {
        Review entity = reviewsRepository.findByParking(parkingCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReviewResponse(entity);
    }

    public List<ReviewDto> findAllAsc() {
        return reviewsRepository.findAllAsc().stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }
}