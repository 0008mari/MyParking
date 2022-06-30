package project.myparking.web.dto;


import lombok.Data;
import project.myparking.domain.*;

@Data
public class ReviewDto {

    private Long userId;

    private Long parkingId;
    private EvalSpace evalSpace;
    private EvalParkinglevel evalParkinglevel;
    private EvalCostefficient evalCostefficient;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;
    private int starScore;

    public ReviewDto(Review review) {
        userId = review.getUser().getId();
        parkingId = review.getParking().getId();

        evalSpace = review.getEvalSpace();
        evalCostefficient = review.getEvalCostefficient();
        evalStaff = review.getEvalStaff();
        evalParkinglevel = review.getEvalParkinglevel();
        evalRevisit = review.getEvalRevisit();

        starScore = review.getStarScore();
    }
}
