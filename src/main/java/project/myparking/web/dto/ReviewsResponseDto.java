package project.myparking.web.dto;

import project.myparking.domain.Review;

import java.time.LocalDateTime;

public class ReviewsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;

    public ReviewsResponseDto(Review entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();

    }
}
