package project.myparking.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor// (access = AccessLevel.PROTECTED)
public class Parking {
    @Id
    @Column (name = "parking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Transient
    private String SYNC_TIME;           // "2019-06-10 09:36:13"
    private double RATES;               // 150.0
    private double TIME_RATE;           // 5.0
    private double ADD_RATES;           // 150.0
    private double ADD_TIME_RATE;       // 5.0
    @Transient
    private double LAT;                 // 37.64391748
    @Transient
    private double LNG;                 // 127.05856743


    @OneToMany (mappedBy = "parking")
    private List<Review> reviews = new ArrayList<>();

    public Parking(long l, String parking_name, String addr, String parking_code, String parking_type_nm, String operation_rule_nm, String tel, double capacity, String pay_nm, String weekday_begin_time, String weekday_end_time, String sync_time, double rates, double time_rate, double add_rates, double add_time_rate, double lat, double lng) {
    }


//    @Builder
//    public Parking(Long id, String PARKING_NAME, String ADDR,
//                   String PARKING_CODE, String PARKING_TYPE_NM,
//                   String OPERATION_RULE_NM, String TEL, double CAPACITY,
//                   String PAY_NM, String WEEKDAY_BEGIN_TIME, String WEEKDAY_END_TIME,
//                   String SYNC_TIME, double RATES, double TIME_RATE, double ADD_RATES,
//                   double ADD_TIME_RATE, double LAT, double LNG) {
//        this.id = id;
//        this.PARKING_NAME = PARKING_NAME;
//        this.ADDR = ADDR;
//        this.PARKING_CODE = PARKING_CODE;
//        this.PARKING_TYPE_NM = PARKING_TYPE_NM;
//        this.OPERATION_RULE_NM = OPERATION_RULE_NM;
//        this.TEL = TEL;
//        this.CAPACITY = CAPACITY;
//        this.PAY_NM = PAY_NM;
//        this.WEEKDAY_BEGIN_TIME = WEEKDAY_BEGIN_TIME;
//        this.WEEKDAY_END_TIME = WEEKDAY_END_TIME;
//        this.SYNC_TIME = SYNC_TIME;
//        this.RATES = RATES;
//        this.TIME_RATE = TIME_RATE;
//        this.ADD_RATES = ADD_RATES;
//        this.ADD_TIME_RATE = ADD_TIME_RATE;
//        this.LAT = LAT;
//        this.LNG = LNG;
//    }

}