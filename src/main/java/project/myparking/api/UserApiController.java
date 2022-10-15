package project.myparking.api;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.error.exception.NoUserException;
import project.myparking.global.api.CustomResponse;
import project.myparking.repository.UserRepository;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserApiController.class);
    private final HttpSession httpSession;

    @GetMapping(value="/user/{userId}")
    public ResponseEntity<CustomResponse> userInfo(@PathVariable Long userId) {

        logger.info("UserApiController userInfo() ");

        // TODO: 로직 수정
        Object data = null;
        String msg = null;
        userRepository.findById(userId).orElseThrow(() -> new NoUserException());

        if( httpSession.getAttribute("user") != null ){
            data = (SessionUser) httpSession.getAttribute("user");
        }
        return CustomResponse.CommonResponse(HttpStatus.CREATED, true,
            "사용자 정보 조회 성공", data);
    }

//    @PostMapping(value="/admin/signup",
////            consumes = {"application/x-www-form-urlencoded"} ,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//    )
//    public User addAdmin(SignupDto dto) { // @RequestBody 붙이면안됌
//
//        logger.info("UserApiController signup ADMIN() ");
//
//        String password = dto.getPassword();
//        String encryptPassword = passwordEncoder.encode(password);
//
//        User user = new User();
//        user.setUsername(dto.getUsername());
//        user.setPassword(encryptPassword);
//        user.setEmail(dto.getEmail());
//        user.setRole(Role.ADMIN);
//
//        return userRepository.save(user);
//    }
//
////    @RequestMapping(value = "/{email}/authenticate", method = RequestMethod.POST,
////            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
////            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
////    public @ResponseBody  Representation authenticate(@PathVariable("email") String anEmailAddress, MultiValueMap paramMap) throws Exception {
////        if(paramMap == null && paramMap.get("password") == null) {
////            throw new IllegalArgumentException("Password not provided");
////        }
////        return null;
////    }
//
////    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
////    public String processForm(@RequestParam Map<String, String> inputValue,  @RequestBody MultiValueMap<String, List<String>> formInfo) {
////    ......
////    ......
////    }
//
//    // https://www.baeldung.com/spring-url-encoded-form-data
//    // https://stackoverflow.com/questions/33796218/content-type-application-x-www-form-urlencodedcharset-utf-8-not-supported-for
//    @PostMapping(value="/user/signup",
////            consumes = {"application/x-www-form-urlencoded"} ,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//    )
//    public User signup(SignupDto dto) { // @RequestBody 붙이면안됌
//
//        logger.info("UserApiController signup USER() ");
//
//        String password = dto.getPassword();
//        String encryptPassword = passwordEncoder.encode(password);
//
//        User user = new User();
//        user.setUsername(dto.getUsername());
//        user.setPassword(encryptPassword);
//        user.setEmail(dto.getEmail());
//        user.setRole(Role.USER);
//
//        return userRepository.save(user);
//    }
//
//    @PostMapping(value="/user/signin",
////            consumes = {"application/x-www-form-urlencoded"} ,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//    )
//    public String signin(SigninDto dto, HttpServletRequest req) { // @RequestBody 붙이면안됌
//
//        logger.info("UserApiController signin USER() ");
//
//        User user = userRepository.findByUsername(dto.getUsername());
//
//        String password = dto.getPassword();
//        String encryptPassword = passwordEncoder.encode(password);
//
//        boolean matches = passwordEncoder.matches(encryptPassword, user.getPassword());
//
//        System.out.println("matches() result " + matches);
//
//        if(matches){
//            logger.info("USER Login Success");
//            req.getSession().setAttribute("user", user);
//            return "true";
//        }else{
//            logger.info("USER Login Fail");
//            return "false";
//        }
//    }

//    @GetMapping("/")
//    public String index(Model model, Principal principal) {
//        if (principal == null) {
//            model.addAttribute("message", "Hello Spring Security");
//        } else {
//            model.addAttribute("message", "Hello, " + principal.getName());
//        }
//
//        return "index";
//    }
}
