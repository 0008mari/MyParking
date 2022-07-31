package project.myparking.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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