package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingRequestDto {

    private String name;
    public ParkingRequestDto(Parking entity) {
        this.name = entity.getName();
    }
}
