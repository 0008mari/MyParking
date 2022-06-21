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
import project.myparking.repository.ReviewRepository;
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
@RequestMapping("api/v1/parkings")
public class ReviewApiController {

    @Autowired
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    /**
     * V1. 엔티티 직접 노출
     * - orderItem, item 관계를 직접 초기화하면 Hibernate5Module 설정에 의해 엔티티를 JSON 으로 생성한다.
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */

    @Operation(summary = "pid값을 가지는 주차장의 리뷰의 정보 출력")
    @GetMapping("/{parkingId}/reviews")
    public List<ReviewDto> allReviewsByPid(@PathVariable Long parkingId) {
        List<Review> reviewList = reviewRepository.findByPid(parkingId);

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

    @GetMapping("/reviews")
    @Operation(summary = "모든 리뷰 출력")
    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream().map(ReviewDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{parkingId}/reviews")
    @Operation(summary = "해당 주차장에 리뷰 등록")
    public Long createReview(@RequestBody ReviewDto requestDto) {

        return reviewRepository.save(new Review());
    }

    @PutMapping("/api/v1/reviews/{id}")
    @Operation(summary = "리뷰 수정")
    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/reviews/{id}")
    @Operation(summary = "리뷰 삭제")
    public Long delete(@PathVariable Long id) {
        reviewService.delete(id);
        return id;
    }

}

