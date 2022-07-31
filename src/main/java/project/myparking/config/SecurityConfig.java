//package project.myparking.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.SecurityExpressionHandler;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;
//import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    // 정적인 파일에 대한 요청들
//    private static final String[] AUTH_WHITELIST = {
//            // -- swagger ui
//            "/v2/api-docs",
//            "/v3/api-docs/**",
//            "/configuration/ui",
//            "/swagger-resources/**",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**",
//            "/file/**",
//            "/image/**",
//            "/swagger/**",
//            "/swagger-ui/**",
//            // other public endpoints of your API may be appended to this array
//            "/h2/**"
//    };
//
//    @Bean
//    public BCryptPasswordEncoder encodePassword() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
//        return new BCryptPasswordEncoder();
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        // antMatchers는 URL 매핑 할때 개미패턴, mvcMatchers는 mvc패턴
////        http.authorizeRequests()
////                // login 없이 접근 허용 하는 url
////                .antMatchers("/auth/**").permitAll()
////                // '/admin'의 경우 ADMIN 권한이 있는 사용자만 접근이 가능
////                .antMatchers("/admin").hasRole("ADMIN")
////                // 그 외 모든 요청은 인증과정 필요
////                .anyRequest().authenticated();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/", "/info").permitAll()   // /, /info 요청에 대해서는 모두 허용
//                .mvcMatchers("/admin").hasRole("ADMIN")  // /admin 요청에 대해서는 Role이 ADMIN인 사용자만 허용
//                .anyRequest().authenticated()            // 그 외 다른 요청에 대해서는 인증(로그인 여부)만 확인
//                .and()
//                .formLogin()  // 제공되는 formLogin 기능 사용 설정
//                .and()
//                .httpBasic();  // 제공되는 httpBasic 기능 사용 설정
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 정적인 파일 요청에 대해 무시
//        web.ignoring().antMatchers(AUTH_WHITELIST);
//    }
//
//    /**
//     * 인메모리 유저 설정
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("daeun").password("{noop}123").roles("USER").and()
//                .withUser("admin").password("{noop}!@#").roles("ADMIN");
//
//        /*
//        AuthenticationManagerBuilder를 제공하는 configure메서드 오버라이딩을 한다. 이 메서드로 원하는 유저를 임의대로 설정할 수 있다.
//인메모리 유저 설정은 inMemoryAuthentication() 메서드를 사용한다.
//noop은 스프링 시큐리티에서 제공하는 기본 패스워드 인코더방식인데 암호화를 하지 않았다는 것이다.
//{}안에 다른 인코딩 방식을 사용한다면 웹 화면에서 입력한 password가 {}인코딩방식으로 인코딩된 값하고 비교가 된다. ex) {bcrypt}#oijowefijwoj0192310j
//출처: https://deftkang.tistory.com/217?category=1004452 [deftkang의 IT 블로그:티스토리]
//
//         */
//    }
//
//
//
//    //
//
//
//    public SecurityExpressionHandler expressionHandler() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//
//        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setRoleHierarchy(roleHierarchy);
//
//        return handler;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll()
//                .mvcMatchers("/admin").hasRole("ADMIN")
//                .mvcMatchers("/user").hasRole("USER")
//                .anyRequest().authenticated()
//                .expressionHandler(expressionHandler());
//
//        http.formLogin()
//                .loginPage("/login")
//                .permitAll();
//
//        http.httpBasic();
//
//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
//
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//    }
//}
