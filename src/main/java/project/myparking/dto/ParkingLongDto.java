package project.myparking.dto;

import lombok.Getter;
import project.myparking.domain.Parking;

// 쿼리가 총 1+N+N 번 실행된다 (N+1 problem) -> 2학기에 fetch join으로 최적화

@Getter
public class ParkingLongDto {

    private String name;
    private String address;
    private String code;
    private String typeName;
    private String opRuleName;
    private String tel;
    private double capacity;
    private String payName;
    private String weekdayBeginTime;
    private String weekdayEndTime;
    private double rates;
    private double timeRate;
    private double addRates;
    private double addTimeRate;

    public ParkingLongDto(Parking parking) {
        this.name = parking.getName();
        this.address = parking.getAddress();
        this.code = parking.getCode();
        this.typeName = parking.getTypeName();
        this.opRuleName = parking.getOpRuleName();
        this.tel = parking.getTel();
        this.capacity = parking.getCapacity();
        this.payName = parking.getPayName();
        this.weekdayBeginTime = parking.getWeekdayBeginTime();
        this.weekdayEndTime = parking.getWeekdayEndTime();
        this.rates = parking.getRates();
        this.timeRate = parking.getTimeRate();
        this.addRates = parking.getAddRates();
        this.addTimeRate = parking.getAddTimeRate();
    }

}