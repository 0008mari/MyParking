package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import project.myparking.web.dto.RegiDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    /*
    카카오 프로필 : String nickname, String profile_image, String thumbnail_image_url, Boolean prifile_needs_agreement
     */

     @Id @Column(name = "user_id")  // auto_increment seq 따로 관리를 위해서 (인조키)
     private Long userid;
    private String alias;           // 카카오 프로필 : String nickname
    @Column(nullable = false)
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(RegiDto dto) {
        alias = dto.getAlias();
        email = dto.getEmail();
        password = dto.getPassword();
        name = dto.getName();
        role = Role.USER; // 회원가입하는 사용자 권한 기본 USER (임시)
    }

    private String picture;        // 카카오 프로필 :String profile_image

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

//    public void encryptPassword(PasswordEncoder passwordEncoder) {
//        password = passwordEncoder.encode(password);
//    }

}


