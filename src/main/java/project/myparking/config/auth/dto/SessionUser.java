package project.myparking.config.auth.dto;

import lombok.Getter;
import project.myparking.domain.User;

import java.io.Serializable;

/**
 * SessionUser 에는 인증된 사용자 정보만 필요
 *
 * User Entity 는 Serializable 구현 x , 직렬화 기능을 가진 세션 dto 를 따로 생성하여 유지보수에 도움을 줌
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    private String roleTitle;
//    private List<ReviewDto> dtolist;

    public SessionUser(User user) {
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.roleTitle = user.getRole().getCode();

    }
}
