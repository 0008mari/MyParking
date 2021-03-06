package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@ApiModel(description = "주차장 정보 도메인")
public class Parking {

    @Id @Column//(name = "parking_id")
    @GeneratedValue
    private Long id;

    @ApiModelProperty(notes = "주차장 이름")
    private String name;
    @ApiModelProperty(notes = "주차장 주소")
    private String address;
    @ApiModelProperty(notes = "주차장 전화번호")
    private String phone;
    @ApiModelProperty(notes = "주차장 요금")
    private int fee;

    @OneToMany(mappedBy = "parking")
    List<Review> reviews = new ArrayList<>();

    @Builder
    public Parking(String name, String address, String phone, int fee) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fee = fee;
    }

    public void update(String name, String address, String phone, int fee) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fee = fee;

    }
}
