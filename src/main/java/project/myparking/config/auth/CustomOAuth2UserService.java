package project.myparking.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.myparking.config.auth.dto.OAuthAttributes;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 구글 로그인 이후 가져온 사용자의 정보(email, name, picture 등)들을 기반으로 가입 및 정보수정, 세션 저장등의 기능을 지원
 *
 * OAuth2User : A representation of a user Principal that is registered with an OAuth 2.0 Provider.
 * An OAuth 2.0 user is composed of one or more attributes, for example, first name, middle name, last name, email,
 * phone number, address, etc. Each user attribute has a "name" and "value" and is keyed by the "name" in getAttributes().
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService(); // 대리, 위임 객체생성
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // registrationId : 현재 로그인 진행 중이 서비스를 구분하는 코드 ( 구글, 네이버, 카카오 동시에 여러개 써서 구분 필요 )
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // userNameAttributeName : OAuth2 로그인 진행 시 키가 되는 필드값 (PK)
        // 구글 기분 코드는 "sub" , 네이버 카카오는 기본 지원 x
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        
        // OAuthAttributes : OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute를 담을 클래스
        //                                             무슨소셜로그인인지, 속성명, 속성값들
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                .orElse(attributes.toEntity());

        User user = userRepository.findByEmail(attributes.getEmail());
        if(user != null){
            user.update(attributes.getName(), attributes.getPicture());
        }
        else{
            user = attributes.toEntity();
        }
        return userRepository.save(user);
    }
}
