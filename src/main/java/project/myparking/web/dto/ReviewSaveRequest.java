package project.myparking.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Keyword;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import java.security.Key;

@Getter
@NoArgsConstructor
public class ReviewSaveRequest {

    private User user;
    private Parking parking;
    private Keyword keyword;
    private int starScore;

    @Builder
    public ReviewSaveRequest(User user, Parking parking, Keyword keyword, int starScore) {
        this.user = user;
        this.parking = parking;
        this.keyword = keyword;
        this.starScore = starScore;
    }

    public Review toEntity() {
        return Review.builder().build();
//                .user(user)
//                .parking(parking)
//                .keyword(keyword)
//                .starScore(starScore)
//                .build();
    }
}
