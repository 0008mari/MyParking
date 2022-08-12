package project.myparking.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.myparking.config.auth.dto.CustomUserDetails;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
//    UserDbService userdbService;
    //데이터베이스에서 로그인 아이디에 해당하는 정보를 읽어 들이기 위해서 UserDbService를 구현한 객체를 주입받고 있다.


    /** email AS loginId **/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // loginId에 해당하는 정보를 데이터베이스에서 읽어 CustomUser객체에 저장한다.
        // 해당 정보를 CustomUserDetails객체에 저장한다

        User user = userRepository.findByEmail(email);
        if(user==null)
            throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(user.getEmail());
        customUserDetails.setPassword(user.getPassword());

        List<UserRoleEntity> customRoles = userRepository.findByEmail(email);
        // 로그인 한 사용자의 권한 정보를 GrantedAuthority를 구현하고 있는 SimpleGrantedAuthority객체에 담아
        // 리스트에 추가한다. MemberRole 이름은 "ROLE_"로 시작되야 한다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(customRoles != null) {
            for(UserRoleEntity customRole : customRoles) {
                authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
            }
        }

        // CustomUserDetails객체에 권한 목록 (authorities)를 설정한다.
        customUserDetails.setAuthorities(authorities);
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
        return customUserDetails;

    }
}
