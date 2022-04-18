package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @Column//(name = "review_id")
    private Long id;

    @Schema(description = "리뷰 제목")
    private String title;
    @Schema(description = "리뷰 본문 내용")
    private String content;
    @Schema(description = "리뷰 작성자")
    private String author;  // User uesrid 로 바꾸고 only logged in user 만 리뷰작성가능으로 수정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn//(name = "parking_id")
    @Schema(description = "리뷰 대상 주차장")
    private Parking parking;

    @Builder
    public Review(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}