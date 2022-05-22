package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Review;

import java.time.LocalDateTime;

@Getter
public class ReviewsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime modifiedDate;

    public ReviewsListResponseDto(Review entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}