package project.myparking.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public ReviewsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}