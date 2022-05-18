package project.myparking.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;

@Getter
//@NoArgsConstructor
public class ReviewUpdateRequest {
    private String spacious;
    private String difficulty;
    private String pricefairness;
    private String gentleness;
    private String revisitYN;

    private int starScore;

    @Builder
    public ReviewUpdateRequest(Review review)
    {
        this.spacious = review.getKeyword().getSpacious();
        this.difficulty = review.getKeyword().getDifficulty();
        this.pricefairness = review.getKeyword().getPricefairness();
        this.gentleness = review.getKeyword().getGentleness();
        this.revisitYN = review.getKeyword().getRevisitYN();

        this.starScore = review.getStarScore();
    }
}