package project.myparking.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.*;

import java.security.Key;

@Getter
@NoArgsConstructor
public class ReviewSaveRequest {

    private User user;
    private Parking parking;
    private EvalSpace evalSpace;
    private EvalCostefficient evalCostefficient;
    private EvalParkinglevel evalParkinglevel;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;    private int starScore;

    @Builder
    public ReviewSaveRequest(User user, Parking parking,
                             EvalSpace evalSpace, EvalCostefficient evalCostefficient,
                             EvalParkinglevel evalParkinglevel, EvalStaff evalStaff,
                             EvalRevisit evalRevisit, int starScore) {
        this.user = user;
        this.parking = parking;
        this.evalSpace = evalSpace;
        this.evalCostefficient = evalCostefficient;
        this.evalParkinglevel = evalParkinglevel;
        this.evalStaff = evalStaff;
        this.evalRevisit = evalRevisit;
        this.starScore = starScore;
    }

//    public Review toEntity() {
//        return Review.builder().build();
////                .user(user)
////                .parking(parking)
////                .keyword(keyword)
////                .starScore(starScore)
////                .build();
//    }
}
