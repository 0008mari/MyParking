package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ApiModel(description = "리뷰 정보 도메인")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Schema(description = "리뷰 제목")
    private String title;

//    @Column @Schema(description = "리뷰 본문 내용")
//    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Schema(description = "리뷰 주차장")
    private ParkingInfo parking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Schema(description = "리뷰 작성자")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KEYWORD_ID")
    @Schema(description = "리뷰 내용을 구성하는 키워드")
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