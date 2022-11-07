package project.myparking.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;

@Getter
public class ReviewResponseDto {

    private Long userId;
    private String code;
    private String space;
    private String level;
    private String costefficient;
    private String staff;
    private String revisit;
    private Integer score;

    public ReviewResponseDto (Review review) {
        userId = review.getUser().getId();
        code = review.getParking().getCode();
        space = review.getEvalSpace().getCode();
        costefficient = review.getEvalCostefficient().getCode();
        staff = review.getEvalStaff().getCode();
        level = review.getEvalParkinglevel().getCode();
        revisit = review.getEvalRevisit().getCode();
        score = review.getStarScore();
    }
}
