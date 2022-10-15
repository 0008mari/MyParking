package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.config.auth.LoginUser;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.Review;
import project.myparking.domain.User;
import project.myparking.dto.ReviewDto;
import project.myparking.global.api.CustomResponse;
import project.myparking.global.exception.CustomException;
import project.myparking.service.ReviewService;
import project.myparking.util.StringUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewApiController {

    private final ReviewService reviewService;
    /**
     * API CONTROLLER 에서는 ENTITY 주고받지 마시오
     * V1. 엔티티 직접 노출
     * - orderItem, item 관계를 직접 초기화하면 Hibernate5Module 설정에 의해 엔티티를 JSON 으로 생성한다.
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */

    @GetMapping("/{reviewId}")
    public ResponseEntity<CustomResponse> getReviewById(@PathVariable Long reviewId) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "리뷰ID 로 작성된 리뷰 출력 성공", reviewService.getReviewById(reviewId));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> allReviewsByParkingIdOrUserId(@RequestParam(required = false) String parkingId,
        @RequestParam(required = false) String userId) {

        Long getParkingId = 0L;
        Long getUserId = 0L;
        Object data = null;

        if(!StringUtil.controllerParamIsBlank(parkingId) && StringUtil.controllerParamIsBlank(userId)) {
            try {
                getParkingId = Long.parseLong(parkingId);
            } catch (NumberFormatException e) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 주차장 ID 입니다. 리뷰목록 조회에 실패했습니다.");
            }
            data = reviewService.getReviewsByParkingId(getParkingId);
        }
        else if(StringUtil.controllerParamIsBlank(parkingId) && !StringUtil.controllerParamIsBlank(userId)) {
            try {
                getParkingId = Long.parseLong(parkingId);
            } catch (NumberFormatException e) {
                throw new CustomException(HttpStatus.BAD_REQUEST,
                    "잘못된 사용자 ID 입니다. 리뷰목록 조회에 실패했습니다.");
            }
            data = reviewService.getReviewsByUserId(getUserId);
        }

        // TODO: 입력 값 2개일 때 예외 처리 해줘야함
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "주차장 ID 또는 사용자 ID로 해당 주차장에 작성된 리뷰 출력 성공", data);
    }

    @PostMapping("/new")
    @Operation(summary = "해당 주차장에 리뷰 등록")
    public ResponseEntity<CustomResponse> addReview(@RequestBody ReviewDto dto) {
        Review review = reviewService.addReview(dto);
        
        if(review == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "리뷰 작성 실패");
        }
        return CustomResponse.CommonResponse(HttpStatus.CREATED, true,
            "리뷰 작성 성공", review.getId());
    }
//
//    @PutMapping("/reviews/{reviewid}")
//    @Operation(summary = "리뷰 수정")
//    public String update(@PathVariable Long reviewid, @RequestBody ReviewUpdateDto dto,
//                       HttpServletRequest req, @LoginUser SessionUser loginuser) {
//
//        User user = reviewService.findReviewWriter(reviewid);
//
//        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
//        if(user.getEmail() == loginuser.getEmail()){
//            reviewService.update(reviewid, dto);
//            return "Review Update Success";
//        } else{
//            return "Review Update Fail";
//        }
//    }
//
    @DeleteMapping("/reviews/{reviewid}")
    @Operation(summary = "리뷰 삭제")
    public ResponseEntity<CustomResponse> deleteReview (@PathVariable Long reviewId, HttpServletRequest req, @LoginUser SessionUser loginuser) {
        User user = reviewService.getReviewWriter(reviewId);

        // 내가 작성한 리뷰일 경우에만 리뷰 삭제
        if(user.getEmail() == loginuser.getEmail()){
            reviewService.delete(reviewId);
            return CustomResponse.CommonResponse(HttpStatus.OK, true,
                "리뷰 삭제 성공", reviewId);
        } else {
            return CustomResponse.CommonResponse(HttpStatus.BAD_REQUEST, false, "리뷰 삭제 실패");
        }
    }

}
