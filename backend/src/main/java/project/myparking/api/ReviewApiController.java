package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.myparking.service.ReviewsService;
import project.myparking.web.dto.ReviewsListResponseDto;
import project.myparking.web.dto.ReviewsResponseDto;
import project.myparking.web.dto.ReviewsSaveRequestDto;
import project.myparking.web.dto.ReviewsUpdateRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewsService reviewsService;    // CRUD "api에서 Entity주고받지마세요" 꼭 DTO로!!!

    @Operation(summary = "id값을 가지는 리뷰의 정보 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/api/v1/reviews/{id}")
    public ReviewsResponseDto findById(@PathVariable Long id) {
        return reviewsService.findById(id);
    }

    @GetMapping("/api/v1/reviews")
    @Operation(summary = "모든 리뷰 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public List<ReviewsListResponseDto> findAll() {
        return reviewsService.findAllAsc();
    }

    @PostMapping("/api/v1/reviews")
    @Operation(summary = "리뷰 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public Long save(@RequestBody ReviewsSaveRequestDto requestDto) {
        return reviewsService.save(requestDto);
    }

    @PutMapping("/api/v1/reviews/{id}")
    @Operation(summary = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public Long update(@PathVariable Long id, @RequestBody ReviewsUpdateRequestDto requestDto) {
        return reviewsService.update(id, requestDto);
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
        reviewsService.delete(id);
        return id;
    }

}
