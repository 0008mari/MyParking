package project.myparking.web.dto;

import project.myparking.domain.Parking;

//@Json
public class ParkingResponseComplex {

    private String PARKING_NAME;        // "마들스타디움(근린공원)(구)"
    private String ADDR;                // "노원구 상계동 770-2"
    private String PARKING_CODE;        // "1012254"
    private String PARKING_TYPE_NM;     // "노외 주차장"
    private String OPERATION_RULE_NM;   // "시간제 주차장"
    private String TEL;                 // "02-2289-6735"
    private double CAPACITY;            // 177.0
    private String PAY_NM;              // "유료"
    private String WEEKDAY_BEGIN_TIME;  // "0000"
    private String WEEKDAY_END_TIME;    // "2400"
    private double RATES;               // 150.0
    private double TIME_RATE;           // 5.0
    private double ADD_RATES;           // 150.0
    private double ADD_TIME_RATE;       // 5.0

    public ParkingResponseComplex(Parking entity) {
        this.PARKING_NAME = entity.getPARKING_NAME();
        this.ADDR = entity.getADDR();
        this.PARKING_CODE = entity.getPARKING_CODE();
        this.PARKING_TYPE_NM = entity.getPARKING_TYPE_NM();
        this.OPERATION_RULE_NM = entity.getOPERATION_RULE_NM();
        this.TEL = entity.getTEL();
        this.CAPACITY = entity.getCAPACITY();
        this.PAY_NM = entity.getPAY_NM();
        this.WEEKDAY_BEGIN_TIME = entity.getWEEKDAY_BEGIN_TIME();
        this.WEEKDAY_END_TIME = entity.getWEEKDAY_END_TIME();
        this.RATES = entity.getRATES();
        this.TIME_RATE = entity.getTIME_RATE();
        this.ADD_RATES = entity.getADD_RATES();
        this.ADD_TIME_RATE = entity.getADD_TIME_RATE();
    }

}
