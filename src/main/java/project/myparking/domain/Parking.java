package project.myparking.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "parkings")
public class Parking {

    @Column(name = "parking_name", length = 500)
    @NotNull
    private String name;

    @Column(name = "parking_address", length = 500)
    @NotNull
    private String address;

    @Id
    @Column(name = "parking_code", length = 100)
    @NotNull
    private String code;

    @Column(name = "parking_type_name", length = 20)
    @NotNull
    private String typeName;

    @Column(name = "parking_operation_rule_name", length = 20)
    @NotNull
    private String opRuleName;

    @Column(name = "parking_tel", length = 20)
    @NotNull
    private String tel;

    @Column(name = "parking_capacity")
    @NotNull
    private double capacity;

    @Column(name = "parking_pay_name", length = 20)
    @NotNull
    private String payName;

    @Column(name = "parking_weekday_begin_time")
    @NotNull
    private String weekdayBeginTime;

    @Column(name = "parking_weekday_end_time")
    @NotNull
    private String weekdayEndTime;

    @Transient
    private String syncTime;           // "2019-06-10 09:36:13"

    @Column(name = "parking_rates")
    @NotNull
    private double rates;               // 기본 주차 요금

    @Column(name = "parking_time_rate", length = 20)
    @NotNull
    private double timeRate;           // 기본 주차 시간(분 단위)

    @Column(name = "parking_add_rates", length = 20)
    @NotNull
    private double addRates;           // 추가 단위 요금

    @Column(name = "parking_add_time_rate", length = 20)
    @NotNull
    private double addTimeRate;       // 추가 단위 시간(분 단위)

    @Column(name = "parking_latitude", length = 20)
    @NotNull
    private double latitude;

    @Column(name = "parking_longitude", length = 20)
    @NotNull
    private double longitude;

    @OneToMany (mappedBy = "parking", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public double getStarAvg(){
        double total = 0;
        if(!reviews.isEmpty()){
            for (Review r : reviews) {
                total += r.getStarScore();
            }
            total /= reviews.size();
        }
        return total;
    }

    public int getReviewCnt() {
        return reviews.size();
    }

    public Parking(String name, String address, String code, String typeName, String opRuleName, String tel,
        double capacity, String payName, String weekdayBeginTime, String weekdayEndTime, String syncTime,
        double rates, double timeRate, double addRates, double addTimeRate, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.code = code;
        this.typeName = typeName;
        this.opRuleName = opRuleName;
        this.tel = tel;
        this.capacity = capacity;
        this.payName = payName;
        this.weekdayBeginTime = weekdayBeginTime;
        this.weekdayEndTime = weekdayEndTime;
        this.syncTime = syncTime;
        this.rates = rates;
        this.timeRate = timeRate;
        this.addRates = addRates;
        this.addTimeRate = addTimeRate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addReview(Review review) {
//        review.setParking(this);
        this.reviews.add(review);
    }
}
