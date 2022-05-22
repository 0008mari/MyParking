package project.myparking.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity
@Schema(description = "사용자")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "사용자 이름")
    private String name;

    @Column(nullable = false)
    @Schema(description = "사용자 이메일", nullable = false, example = "abc@jiniworld.me")
    private String email;

    @Column
    @Schema(description = "사용자 계정 사진")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "사용자 유형", defaultValue = "0", allowableValues = {"0", "1"})
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}