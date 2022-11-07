package project.myparking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.myparking.domain.Parking;

@Getter @AllArgsConstructor
public class ParkingShortDto {
    private String code;
    private String name;
    private double reviewStarAvg;
    private int reviewCount;
    private String address;
    private double latitude;
    private double longitude;

    public ParkingShortDto(Parking parking) {
        this.code = parking.getCode();
        this.name = parking.getName();
        this.reviewStarAvg = parking.getStarAvg();
        this.reviewCount = parking.getReviews().size();
        this.address = parking.getAddress();
        this.latitude = parking.getLatitude();
        this.longitude = parking.getLongitude();
    }
}
