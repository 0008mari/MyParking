package project.myparking.web.dto;


import lombok.Data;
import project.myparking.domain.*;

@Data
public class ReviewDto {

    private String userName;
    private EvalSpace evalSpace;
    private EvalParkinglevel evalParkinglevel;
    private EvalCostefficient evalCostefficient;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;
    private int starScore;

    public ReviewDto(Review review) {
        userName = review.getUser().getName();
        evalSpace = review.getEvalSpace();
        evalCostefficient = review.getEvalCostefficient();
        evalStaff = review.getEvalStaff();
        evalParkinglevel = review.getEvalParkinglevel();
        evalRevisit = review.getEvalRevisit();

        starScore = review.getStarScore();
    }
}
