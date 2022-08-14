package project.myparking.domain;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import project.myparking.web.dto.SignupDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    /*
    카카오 프로필 : String nickname, String profile_image, String thumbnail_image_url, Boolean prifile_needs_agreement
     */

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long userid;

//    private String alias;           // 카카오 프로필 : String nickname

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;
    private String password;
    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 이거아님
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;

//    public User(SignupDto dto) {
//        email = dto.getEmail();
//        password = dto.getPassword();
//        username = dto.getUsername();
//        role = Role.USER;
//    }

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(String email, String username, Role role, String picture, String pwd) {
        this.username = username;
        this.password = encryptPassword(pwd);
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.username = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public String getRoleTitle(){
        return this.role.getTitle();
    }


//    public void encryptPassword(PasswordEncoder passwordEncoder) {
//        password = passwordEncoder.encode(password);
//    }
    public String encryptPassword(String pwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(pwd == null) pwd = "0";
        return passwordEncoder.encode(pwd);
    }
}


