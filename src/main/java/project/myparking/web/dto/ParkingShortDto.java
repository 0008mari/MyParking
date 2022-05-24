package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingShortDto {
    private String name;
//    private double reviewStarAvg;
//    private int reviewCount;
    private String address;

    public ParkingShortDto(Parking entity) {
        this.name = entity.getPARKING_NAME();
//        this.reviewStarAvg = entity.getAddress();
//        this.reviewCount = entity.getPhone();
        this.address = entity.getADDR();
    }
}
