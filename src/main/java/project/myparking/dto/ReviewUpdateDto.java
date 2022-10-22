package project.myparking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.myparking.domain.*;

import project.myparking.enumtype.EvalCostefficient;
import project.myparking.enumtype.EvalParkinglevel;
import project.myparking.enumtype.EvalRevisit;
import project.myparking.enumtype.EvalSpace;
import project.myparking.enumtype.EvalStaff;

@Getter
@AllArgsConstructor
public class ReviewUpdateDto {

    private Long userId;
    private String space;
    private String level;
    private String costefficient;
    private String staff;
    private String revisit;
    private Integer score;
}