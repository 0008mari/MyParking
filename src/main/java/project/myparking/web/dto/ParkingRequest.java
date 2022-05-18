package project.myparking.web.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

@Getter
public class ParkingRequest {

    private String addr;
    public ParkingRequest(Parking entity) {
        this.addr = entity.getADDR();
    }
}
