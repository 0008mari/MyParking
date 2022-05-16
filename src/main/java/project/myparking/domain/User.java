package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseTimeEntity {

    @Id @Column(name = "UID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String alias;

    @Column(nullable = false)
    private String email;
    private String picture;

    @OneToMany(mappedBy = "user")
    List<Review> reviewList = new ArrayList<>();

    private Role role;
}


