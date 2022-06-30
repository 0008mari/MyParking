package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.repository.ParkingRepository;
import project.myparking.repository.ReviewRepository;
import project.myparking.repository.UserRepository;
import project.myparking.service.ReviewService;
import project.myparking.service.ReviewsService;
import project.myparking.web.dto.ParkingShortDto;
import project.myparking.web.dto.ReviewDto;
import project.myparking.web.dto.ReviewResponse;
import project.myparking.web.dto.ReviewResponseDto;
import project.myparking.web.dto.ReviewSaveRequestDto;
import project.myparking.web.dto.ReviewUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor // API CONTROLLER 에서는 ENTITY 주고받지 마시오
@RequestMapping("api/v1")
public class ReviewApiController {
    // @Autowired
    // private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;

    /**
     * V1. 엔티티 직접 노출
     * - orderItem, item 관계를 직접 초기화하면 Hibernate5Module 설정에 의해 엔티티를 JSON 으로 생성한다.
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */

    @Operation(summary = "pid값을 가지는 주차장의 리뷰의 정보 출력")
    @GetMapping("/parkings/{pid}/reviews")
    public List<ReviewDto> allReviewsByPid(@PathVariable Long pid) {
        List<Review> reviewList = reviewRepository.findByPid(pid);

        return reviewList.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }
//    /**
//     * V2. 엔티티를 DTO 로 변환
//     */
//    @GetMapping("/api/v2/parking/{PARKING_CODE}/reviews")
//    public List<ReviewDto> findById(@PathVariable("PARKING_CODE") String parkingCode) {
//        return reviewService.findByParkingCode(parkingCode);
//    }
    @PostMapping("/parkings/{pid}/reviews")
    @Operation(summary = "해당 주차장에 리뷰 등록")
    public Long createReview(@PathVariable Long pid, @RequestBody ReviewDto requestDto) {

        Review review = new Review();
        // review.setId();
        review.setUser(userRepository.findOne(requestDto.get()));
        review.setParking(parkingRepository.findOne(pid));

        review.setEvalParkinglevel(requestDto.getEvalParkinglevel());
        review.setEvalRevisit(requestDto.getEvalRevisit());
        review.setEvalSpace(requestDto.getEvalSpace());
        review.setEvalCostefficient(requestDto.getEvalCostefficient());
        review.setEvalStaff(requestDto.getEvalStaff());
        review.setStarScore(requestDto.getStarScore());
        reviewRepository.save(review);
    }

    @GetMapping("/parkings/reviews")
    @Operation(summary = "모든 리뷰 출력")
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream().map(ReviewDto::new).collect(Collectors.toList());
    }



    @PutMapping("/parkings/reviews/{rid}")
    @Operation(summary = "리뷰 수정")
    public Long update(@PathVariable Long rid, @RequestBody ReviewDto requestDto) {
        // return reviewService.update(rid, requestDto);
    }

    @DeleteMapping("/parkings/{pid}/reviews/{rid}")
    @Operation(summary = "리뷰 삭제")
    public Long delete(@PathVariable Long pid, @PathVariable Long rid) {
        reviewService.delete(id);
        return id;
    }

}

