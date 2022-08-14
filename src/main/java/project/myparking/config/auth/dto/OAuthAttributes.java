package project.myparking.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import project.myparking.domain.Role;
import project.myparking.domain.User;

import java.util.Map;

/**
 * OAuth2UserService 를 통해 가져온 OAuth2User 의 attributes 를 담는 클래스
 */
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    /**
     * of() : OAuth2User 에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나하나를 변환해야함
     */
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        // 속성명 , 속성값들 담은 Map 반환
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * toEntity() : 처음 가입하는 시점에 OAuthAttributes 에서 User 엔티티를 생성
     *              가입할때 기본 권한을 GUEST 로 주기 위해 role 빌더값에는 Role.GUEST 사용
     *              OAuthAttributes 클래서 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성
     */
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}
