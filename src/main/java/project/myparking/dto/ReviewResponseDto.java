package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long userId;
    private Long parkingId;
    private String evalSpace;
    private String evalParkinglevel;
    private String evalCostefficient;
    private String evalStaff;
    private String evalRevisit;
    private Integer starScore;

    public ReviewResponseDto (Review review) {
        userId = review.getUser().getId();
        parkingId = review.getParking().getId();
        evalSpace = review.getEvalSpace().getCode();
        evalCostefficient = review.getEvalCostefficient().getCode();
        evalStaff = review.getEvalStaff().getCode();
        evalParkinglevel = review.getEvalParkinglevel().getCode();
        evalRevisit = review.getEvalRevisit().getCode();
        starScore = review.getStarScore();
    }
}
