package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;
import project.myparking.enumtype.EvalCostefficient;
import project.myparking.enumtype.EvalParkinglevel;
import project.myparking.enumtype.EvalRevisit;
import project.myparking.enumtype.EvalSpace;
import project.myparking.enumtype.EvalStaff;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long userId;
    private Long parkingId;
    private EvalSpace evalSpace;
    private EvalParkinglevel evalParkinglevel;
    private EvalCostefficient evalCostefficient;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;
    private int starScore;

    /* Review Entity to ReviewDto */
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
