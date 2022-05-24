package project.myparking.web.dto;

import project.myparking.domain.*;

public class ReviewResponse {

    private Parking parking;
    private User user;

    private EvalSpace evalSpace;
    private EvalCostefficient evalCostefficient;
    private EvalParkinglevel evalParkinglevel;
    private EvalStaff evalStaff;
    private EvalRevisit evalRevisit;

    private int starScore;


    public ReviewResponse(Review entity) {
        this.parking = entity.getParking();
        this.user = entity.getUser();

        this.evalCostefficient = entity.getEvalCostefficient();
        this.evalParkinglevel = entity.getEvalParkinglevel();
        this.evalSpace = entity.getEvalSpace();
        this.evalStaff = entity.getEvalStaff();
        this.evalRevisit = entity.getEvalRevisit();

        this.starScore = entity.getStarScore();

    }
}
