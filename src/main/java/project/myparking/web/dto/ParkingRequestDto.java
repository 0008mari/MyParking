package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingRequestDto {

    private String addr;
    public ParkingRequestDto(Parking entity) {
        this.addr = entity.getADDR();
    }
}
