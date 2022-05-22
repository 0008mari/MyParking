package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingListResponseDto {
    private String name;
    private String address;
    private String phone;
    private int fee;

    public ParkingListResponseDto(Parking entity) {
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.fee = entity.getFee();
    }
}
