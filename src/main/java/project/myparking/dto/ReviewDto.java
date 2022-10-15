package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.myparking.domain.*;
import project.myparking.enumtype.EvalCostefficient;
import project.myparking.enumtype.EvalParkinglevel;
import project.myparking.enumtype.EvalRevisit;
import project.myparking.enumtype.EvalSpace;
import project.myparking.enumtype.EvalStaff;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long userid;

    private Long parkingid;
    private EvalSpace evalSpace;
    private EvalParkinglevel evalParkinglevel;
    private EvalCostefficient evalCostefficient;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;
    private int starScore;

    /* Review Entity to ReviewDto */
    public ReviewDto(Review review) {
        userid = review.getUser().getId();
        parkingid = review.getParking().getId();
        evalSpace = review.getEvalSpace();
        evalCostefficient = review.getEvalCostefficient();
        evalStaff = review.getEvalStaff();
        evalParkinglevel = review.getEvalParkinglevel();
        evalRevisit = review.getEvalRevisit();
        starScore = review.getStarScore();
    }

//    @Builder
//    public ReviewWriteDto(Long userid, Long parkingid,
//                          EvalSpace evalSpace, EvalCostefficient evalCostefficient,
//                          EvalParkinglevel evalParkinglevel, EvalStaff evalStaff,
//                          EvalRevisit evalRevisit, int starScore) {
//        this.userid = userid;
//        this.parkingid = parkingid;
//        this.evalSpace = evalSpace;
//        this.evalCostefficient = evalCostefficient;
//        this.evalParkinglevel = evalParkinglevel;
//        this.evalStaff = evalStaff;
//        this.evalRevisit = evalRevisit;
//        this.starScore = starScore;
//    }
}
