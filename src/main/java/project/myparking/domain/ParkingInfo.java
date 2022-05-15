package project.myparking.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class ParkingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String PARKING_NAME;        // "마들스타디움(근린공원)(구)"
    @Column private String ADDR;                // "노원구 상계동 770-2"

    @Column private String PARKING_CODE;        // "1012254"
            private String PARKING_TYPE;        // "NW"
    @Column private String PARKING_TYPE_NM;     // "노외 주차장"
    private String OPERATION_RULE;              // "1"
    @Column private String OPERATION_RULE_NM;   // "시간제 주차장"

    @Column private String TEL;                 // "02-2289-6735"

    @Column private double CAPACITY;            // 177.0
    private String PAY_YN;                      // "Y"
    @Column private String PAY_NM;              // "유료"
    private String NIGHT_FREE_OPEN;             // "N"
    @Column private String NIGHT_FREE_OPEN_NM;  // "야간 미개방"
    @Column private String WEEKDAY_BEGIN_TIME;  // "0000"
    @Column private String WEEKDAY_END_TIME;    // "2400"
    @Column private String WEEKEND_BEGIN_TIME;  // "0000"
    @Column private String WEEKEND_END_TIME;    // "2400"
    @Column private String HOLIDAY_BEGIN_TIME;  // "0000"
    @Column private String HOLIDAY_END_TIME;    // "2400"
    @Column private String SYNC_TIME;           // "2019-06-10 09:36:13"
    private String SATURDAY_PAY_YN;             // "N"
    @Column private String SATURDAY_PAY_NM;     // "무료"
    private String HOLIDAY_PAY_YN;              // "N"
    @Column private String HOLIDAY_PAY_NM;      // "무료"
    @Column private String FULLTIME_MONTHLY;    // "100000"
    private String GRP_PARKNM;                  // ""
    @Column private double RATES;               // 150.0
    @Column private double TIME_RATE;           // 5.0
    @Column private double ADD_RATES;           // 150.0
    @Column private double ADD_TIME_RATE;       // 5.0
    private double BUS_RATES;                   // 0.0
    private double BUS_TIME_RATE;               // 0.0
    private double BUS_ADD_TIME_RATE;           // 0.0
    private double BUS_ADD_RATES;               // 0.0
    @Column private double DAY_MAXIMUM;         // 11000.0
    @Column private double LAT;                 // 37.64391748
    @Column private double LNG;                 // 127.05856743

}
