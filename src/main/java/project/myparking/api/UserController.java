package project.myparking.api;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.domain.User;
import project.myparking.dto.SignupDto;
import project.myparking.error.exception.LoginFailException;
import project.myparking.global.api.CustomResponse;
import project.myparking.repository.UserRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@RequestBody SignupDto dto) {
        User user = userRepository.save(new User(dto.getUsername(), dto.getEmail(), dto.getPassword()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        return CustomResponse.CommonResponse(HttpStatus.CREATED, true,
            "회원가입 성공", map);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody SignupDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        String message = null;
        if (user == null) {
            throw new LoginFailException();
        }
        if (user.getEmail().equals(dto.getEmail()) && user.getPassword()
            .equals(dto.getPassword())) {
            message = "로그인 성공";
        }
        else {
            message = "이메일 또는 비밀번호가 잘못 되었습니다. 다시 입력해주세요";
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("username", user.getUsername());
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            message, map);
    }


// JWT 좀 해보려고 했구만 ㅠㅠ

//    private final PasswordEncoder passwordEncoder;
//    private final JwtAuthenticationProvider jwtAuthenticationProvider;
//
//    @PostMapping("/join")
//    public void join(@RequestBody SignupDto dto) {
//
//        User user = new User(dto.getEmail(), dto.getName(), dto.getPassword());
//        user.setRole(Role.USER);
//        userRepository.save(user);
//
//    }
//
//    @PostMapping("/login")
//    public SignupDto login(@RequestBody SigninDto dto, HttpServletResponse response) {
//        User user = userRepository.findByEmail(dto.getEmail())
//            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//
//        String token = jwtAuthenticationProvider.createToken(user.getUsername(),
//            user.getRoles());
//        response.setHeader("X-AUTH-TOKEN", token);
//
//        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
//
//        return new SignupDto(user);
//    }
//
//    @PostMapping("/logout")
//    public void logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//    }
//
//    @GetMapping("/info")
//    public SignupDto getInfo() {
//        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (details != null && !(details instanceof String))
//            return new SignupDto((User) details);
//        return null;
//    }
}


