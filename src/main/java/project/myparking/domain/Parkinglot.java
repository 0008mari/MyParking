package project.myparking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Parkinglot {

    @Id @GeneratedValue
    @Column(name = "parkinglot_id")
    private Long id;

    // @NotEmpty
    private String name;

    private String address;

    private String phonenumber;

    private Long fee;

    @OneToMany(mappedBy = "parkinglot")
    private List<Review> reviews = new ArrayList<>();

}

