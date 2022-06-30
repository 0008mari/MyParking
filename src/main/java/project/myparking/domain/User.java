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

    // @Id // @Column(name = "user_id")
    // private Long id;

    // @Column(nullable = false) - 앞단에서 처리?
    private String alias;

    @Id @Column(nullable = false)
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(MemberSignupRequestDto request) {
        email = request.getEmail();
        password = request.getPassword();
        name = request.getName();
        role = Role.USER; // 회원가입하는 사용자 권한 기본 USER (임시)
    }


    private String picture;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}


