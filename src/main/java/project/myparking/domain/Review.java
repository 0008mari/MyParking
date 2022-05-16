package project.myparking.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PID")
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KID")
    private Keyword keyword;

    @Builder
    public Review(String title, User user, Keyword keyword) {
        this.title = title;
        this.user = user;
        this.keyword = keyword;
    }

    public void update(String title, Keyword keyword) {
        this.title = title;
        // this.keyword = keyword;
    }
}