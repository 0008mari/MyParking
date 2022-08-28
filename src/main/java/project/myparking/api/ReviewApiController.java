package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.myparking.config.auth.LoginUser;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.User;
import project.myparking.service.ReviewService;
import project.myparking.web.dto.ReviewDto;
import project.myparking.web.dto.ReviewUpdateDto;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ReviewApiController {

    @Autowired
    private final ReviewService reviewService;
    Logger logger = LoggerFactory.getLogger(ReviewApiController.class);

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

    @Operation(summary = "reviewid로 리뷰 찾기")
    @GetMapping("/reviews/{reviewid}")
    public ReviewDto findOne(@PathVariable Long reviewid) {
        return reviewService.findOne(reviewid);
    }

    @Operation(summary = "userid 로 리뷰 찾기")
    @GetMapping("/reviews")
    public List<ReviewDto> allReviewsByUid(@RequestParam Long userid) {
        return reviewService.findReviewsByUid(userid);
    }

    @PostMapping("/reviews/new")
    @Operation(summary = "해당 주차장에 리뷰 등록")
    public void addReview(@RequestBody ReviewDto dto) {

        reviewService.addReview(dto);
    }

    @PutMapping("/reviews/{reviewid}")
    @Operation(summary = "리뷰 수정")
    public String update(@PathVariable Long reviewid, @RequestBody ReviewUpdateDto dto,
                       HttpServletRequest req, @LoginUser SessionUser loginuser) {

        User user = reviewService.findReviewWriter(reviewid);

        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
        if(user.getEmail() == loginuser.getEmail()){
            reviewService.update(reviewid, dto);
            return "Review Update Success";
        } else{
            return "Review Update Fail";
        }
    }

    @DeleteMapping("/reviews/{reviewid}")
    @Operation(summary = "리뷰 삭제")
    public String delete(@PathVariable Long reviewid, HttpServletRequest req, @LoginUser SessionUser loginuser) {
        User user = reviewService.findReviewWriter(reviewid);

        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
        if(user.getEmail() == loginuser.getEmail()){
            reviewService.delete(reviewid);
            return "Review Delete Success";
        } else {
            return "Review Delete Fail";
        }
    }
}


