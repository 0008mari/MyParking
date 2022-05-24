package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @Column(name = "user_id")
    private Long id;

    // @Column(nullable = false) - 앞단에서 처리?
    private String name;
    private String alias;

    @Column(nullable = false)
    private String email;
    private String picture;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    // private Role role;

}


