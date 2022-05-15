package project.myparking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor
@Entity
public class Parking {

    @Id @Column//(name = "parking_id")
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private String phone;
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
