package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Review;
import project.myparking.repository.ReviewRepository;
import project.myparking.service.ReviewService;
import project.myparking.service.ReviewsService;
import project.myparking.web.dto.ReviewDto;
import project.myparking.web.dto.ReviewResponse;
import project.myparking.web.dto.ReviewResponseDto;
import project.myparking.web.dto.ReviewSaveRequestDto;
import project.myparking.web.dto.ReviewUpdateRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor // API CONTROLLER 에서는 ENTITY 주고받지 마시오
public class ReviewApiController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    /**
     * V1. 엔티티 직접 노출
     * - orderItem, item 관계를 직접 초기화하면 Hibernate5Module 설정에 의해 엔티티를 JSON 으로 생성한다.
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
//    @Operation(summary = "id값을 가지는 리뷰의 정보 출력")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK !!"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
//    })
    @GetMapping("/api/v1/parking/{PARKING_CODE}/reviews")
    public List<Review> reviewsV1(@PathVariable("PARKING_CODE") String parkingCode) {
//        List<Review> all = reviewRepository.findByParkingCode(parkingCode);
        return all;
    }

    /**
     * V2. 엔티티를 DTO 로 변환
     */
    @GetMapping("/api/v2/parking/{PARKING_CODE}/reviews")
    public List<ReviewDto> findById(@PathVariable("PARKING_CODE") String parkingCode) {
        return reviewService.findByParkingCode(parkingCode);
    }

    @GetMapping("/api/v1/reviews")
    @Operation(summary = "모든 리뷰 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public List<ReviewDto> findAll() {
        return reviewService.findAllAsc();
    }

    @PostMapping("/api/v1/reviews")
    @Operation(summary = "리뷰 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public Long save(@RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.save(requestDto);
    }

    @PutMapping("/api/v1/reviews/{id}")
    @Operation(summary = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/reviews/{id}")
    @Operation(summary = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public Long delete(@PathVariable Long id) {
        reviewService.delete(id);
        return id;
    }

}

