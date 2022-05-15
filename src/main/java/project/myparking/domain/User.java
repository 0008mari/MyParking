package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@ApiModel(description = "사용자")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Schema(description = "사용자 이름")
    private String name;

    @Column
    @Schema(description = "사용자 별명")
    private String alias;

    @Column(nullable = false)
    @Schema(description = "사용자 이메일", nullable = false, example = "abc@def.me")
    private String email;

    @Column
    @Schema(description = "사용자 계정 사진")
    private String picture;

    @OneToMany(mappedBy = "user")
    List<Review> reviewList = new ArrayList<>();

//    @Builder
//    public User(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
//
//    public User update(String name, String picture) {
//        this.name = name;
//        this.picture = picture;
//        return this;
//    }
}