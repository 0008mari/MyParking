package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.Review;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {

    private Long userId;
    private Long parkingId;
    private Integer evalSpace;
    private Integer evalParkinglevel;
    private Integer evalCostefficient;
    private Integer evalStaff;
    private Integer evalRevisit;
    private Integer starScore;

}
