package project.myparking.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.myparking.domain.Parking;

@Getter @AllArgsConstructor
public class ParkingShortDto {
    private String name;
    private double reviewStarAvg;
    private int reviewCount;
    private String address;

    public ParkingShortDto(Parking entity) {
        this.name = entity.getPARKING_NAME();
        this.reviewStarAvg = entity.getReviewStarAvg();
        this.reviewCount = entity.getReviewCount();
        this.address = entity.getADDR();
    }
}
