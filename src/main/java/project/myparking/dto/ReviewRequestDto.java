package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {

    private Long userId;
    private Long parkingId;
    private Integer space;
    private Integer level;
    private Integer costefficient;
    private Integer staff;
    private Integer revisit;
    private Integer score;

}
