package project.myparking.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Parking {
    @Id @Column(name = "PID")
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
    private String SYNC_TIME;           // "2019-06-10 09:36:13"
    private double RATES;               // 150.0
    private double TIME_RATE;           // 5.0
    private double ADD_RATES;           // 150.0
    private double ADD_TIME_RATE;       // 5.0
    private double LAT;                 // 37.64391748
    private double LNG;                 // 127.05856743

    @OneToMany(mappedBy = "PID")
    List<Review> reviews = new ArrayList<>();


//    @Builder
//    public Parking(String name, String address, String phone, int fee) {
//        this.name = name;
//        this.address = address;
//        this.phone = phone;
//        this.fee = fee;
//    }
//
//    public void update(String name, String address, String phone, int fee) {
//        this.name = name;
//        this.address = address;
//        this.phone = phone;
//        this.fee = fee;
//    }
}
