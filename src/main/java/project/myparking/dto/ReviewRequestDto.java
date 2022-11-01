package project.myparking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {

    private Long userId;
    private String parkingCode;
    private String space;
    private String level;
    private String costefficient;
    private String staff;
    private String revisit;
    private Integer score;

}
