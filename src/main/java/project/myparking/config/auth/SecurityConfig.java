package project.myparking.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;


    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 스프링시큐리티 앞단 설정들을 할 수 있다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
//               .passwordEncoder(passwordEncoder());

    }

    /**
     * 스프링시큐리티의 각종 설정은 HttpSecurity로 대부분 하게 됩니다.
     *
     * 리소스(URL) 접근 권한 설정
     * 인증 전체 흐름에 필요한 Login, Logout 페이지 인증완료 후 페이지 인증 실패 시 이동페이지 등등 설정
     * 인증 로직을 커스텀하기위한 커스텀 필터 설정
     * 기타 csrf, 강제 https 호출 등등 거의 모든 스프링시큐리티의 설정
     *
     * .oauth2Login()      OAuth2 로그인 기능에 대한 여러 설정 진입점
     * .userInfoEndpoint() OAuth2 로그인 성공 이후 사용자 정보 가져올 때 설정 담당
     * .userService(customOAuth2UserService)  소셜로그인 성공시 후속 조치를 진행할 UserService 구현체 등록
     *                                        리소스 서버에서 사용자 정보 가져온 상태에서 추가 기능 명시 가능
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//                .csrf().disable().headers().frameOptions().disable()
//                .and()

//        http
//                .authorizeRequests()
//                .antMatchers("/parkings/**", "/", "/login").permitAll()
//                .antMatchers("/reviews/**").hasRole(Role.USER.name())
//                .antMatchers("/admin").hasRole(Role.ADMIN.name())  // /admin 요청에 대해서는 Role이 ADMIN인 사용자만 허용
//
//                .anyRequest().authenticated()
//                .and()
//                    .logout().logoutSuccessUrl("/")
//                .and()
//                    .oauth2Login()
//                    .userInfoEndpoint()
//                    .userService(customOAuth2UserService);
        }
}
