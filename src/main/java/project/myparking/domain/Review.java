package project.myparking.domain;

import lombok.*;
import javax.persistence.*;
import project.myparking.enumtype.EvalCostefficient;
import project.myparking.enumtype.EvalParkinglevel;
import project.myparking.enumtype.EvalRevisit;
import project.myparking.enumtype.EvalSpace;
import project.myparking.enumtype.EvalStaff;

@Entity
@Table(name = "reviews")
@Setter @Getter
@NoArgsConstructor
public class Review extends BaseTimeEntity {

    @Id @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_code", referencedColumnName="parking_code")
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn//(name = "keyword_id")
//    private Keyword keyword;

    @Enumerated(EnumType.STRING)
    private EvalSpace evalSpace;
    @Enumerated(EnumType.STRING)
    private EvalParkinglevel evalParkinglevel;
    @Enumerated(EnumType.STRING)
    private EvalCostefficient evalCostefficient;
    @Enumerated(EnumType.STRING)
    private EvalStaff evalStaff;
    @Enumerated(EnumType.STRING)
    private EvalRevisit evalRevisit;

    private Integer starScore;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }
    public void setParking(Parking parking) {
        this.parking = parking;
        parking.getReviews().add(this);
    }

    //==생성 메서드==//
    public static Review addReview(User user, Parking parking,
                                      EvalSpace evalSpace,
                                      EvalCostefficient evalCostefficient,
                                      EvalParkinglevel evalParkinglevel,
                                      EvalStaff evalStaff,
                                      EvalRevisit evalRevisit,
                                   int starScore) {
        Review review = new Review();
        review.setUser(user);
        review.setParking(parking);

        review.setEvalParkinglevel(evalParkinglevel);
        review.setEvalCostefficient(evalCostefficient);
        review.setEvalSpace(evalSpace);
        review.setEvalStaff(evalStaff);
        review.setEvalRevisit(evalRevisit);

        review.setStarScore(starScore);

        return review;
    }

    //==비즈니스 로직 ==//
    /** 리뷰 삭제 */
//    public void delete() {
//        if (user != 지금로그인계정) {
//            throw new IllegalStateException("해당 리뷰의 작성자가 아닙니다.")
//        }
//        // delete this review
//    }


//     리뷰 수정
    public void update(int newStarScore) {
        this.starScore = newStarScore;
    }
}