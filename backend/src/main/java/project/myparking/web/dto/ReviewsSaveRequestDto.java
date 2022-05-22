package project.myparking.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;

@Getter
@NoArgsConstructor
public class ReviewsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public ReviewsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
