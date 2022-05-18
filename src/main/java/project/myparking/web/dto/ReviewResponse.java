package project.myparking.web.dto;

import project.myparking.domain.Keyword;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ReviewResponse {

    private Parking parking;
    private User user;

    private Keyword keyword;
    private int starScore;


    public ReviewResponse(Review entity) {
        this.parking = entity.getParking();
        this.user = entity.getUser();

        this.keyword = entity.getKeyword();

        this.starScore = entity.getStarScore();

    }
}
