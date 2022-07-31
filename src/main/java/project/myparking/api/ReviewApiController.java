package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.User;
import project.myparking.service.ReviewService;
import project.myparking.web.dto.ReviewDto;
import project.myparking.web.dto.ReviewUpdateDto;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ReviewApiController {

    @Autowired
    private final ReviewService reviewService;

    /**
     * API CONTROLLER 에서는 ENTITY 주고받지 마시오
     * V1. 엔티티 직접 노출
     * - orderItem, item 관계를 직접 초기화하면 Hibernate5Module 설정에 의해 엔티티를 JSON 으로 생성한다.
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */

    @GetMapping("/parkings/reviews/all")
    @Operation(summary = "모든 리뷰 출력")
    public List<ReviewDto> findAll() {
        return reviewService.findAllReviews();
    }

    @Operation(summary = "parkingid 값을 가지는 주차장의 리뷰의 정보 출력")
    @GetMapping("/parkings/{parkingid}/reviews")
    public List<ReviewDto> allReviewsByPid(@PathVariable Long parkingid) {
        return reviewService.findReviewsByPid(parkingid);
    }

    @PostMapping("/parkings/{parkingid}/reviews")
    @Operation(summary = "해당 주차장에 리뷰 등록")
    public void addReview(@PathVariable Long parkingid, @RequestBody ReviewDto dto) {

        reviewService.addReview(parkingid, dto);
    }

    @PutMapping("/parkings/reviews/{reviewid}")
    @Operation(summary = "리뷰 수정")
    public void update(@PathVariable Long reviewid, @RequestBody ReviewUpdateDto dto, HttpServletRequest req) {

        User user = reviewService.findReviewWriter(reviewid);

        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
//        if(req.getSession().getAttribute("login") == user.getUserid()){
        reviewService.update(reviewid, dto);
//        }
    }

    @DeleteMapping("/parkings/reviews/{reviewid}")
    @Operation(summary = "리뷰 삭제")
    public void delete(@PathVariable Long reviewid, HttpServletRequest req) {
        User user = reviewService.findReviewWriter(reviewid);

        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
//        if(req.getSession().getAttribute("login") == user.getUserid()){
        reviewService.delete(reviewid);
//        }
    }
}


