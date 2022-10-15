package project.myparking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.*;

import project.myparking.enumtype.EvalCostefficient;
import project.myparking.enumtype.EvalParkinglevel;
import project.myparking.enumtype.EvalRevisit;
import project.myparking.enumtype.EvalSpace;
import project.myparking.enumtype.EvalStaff;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateDto {

    private EvalSpace evalSpace;
    private EvalParkinglevel evalParkinglevel;
    private EvalCostefficient evalCostefficient;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;
    private int starScore;

    @Builder
    public ReviewUpdateDto(Review review)
    {
        this.evalSpace = review.getEvalSpace();
        this.evalParkinglevel = review.getEvalParkinglevel();
        this.evalCostefficient = review.getEvalCostefficient();
        this.evalStaff = review.getEvalStaff();
        this.evalRevisit = review.getEvalRevisit();

        this.starScore = review.getStarScore();
    }
}