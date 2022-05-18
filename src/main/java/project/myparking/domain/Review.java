package project.myparking.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "PID")
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "UID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "KID")
    private Keyword keyword;

    private int starScore;

    @Builder
    public Review() {

    }

    public void update(int newStarScore) {
        this.starScore = newStarScore;
    }
}