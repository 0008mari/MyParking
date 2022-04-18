package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private int fee;

    public ParkingResponseDto(Parking entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.fee = entity.getFee();

    }
}
