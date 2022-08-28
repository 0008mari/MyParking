package project.myparking.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.Role;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserApiController.class);
    private final HttpSession httpSession;

    @GetMapping(value="/user/info")
    public SessionUser loggedinUser() {

        logger.info("UserApiController loggedinUser() ");

        if( httpSession.getAttribute("user") != null ){
            return (SessionUser) httpSession.getAttribute("user");
        }
        else{
            return null;
        }
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

//////////////////////////////////////////////////////////////////////////////////////////////////
//    @Autowired
//    SampleService sampleService;
//
//        @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String signup(@RequestBody com.example.securityspring.dto.SigninDto request) {
//        return authService.signup(request);
//    }
//
//    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String login(@RequestBody JwtRequestDto request) {
//        return "login";
//    }
//
//    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String signup(@RequestBody com.example.securityspring.dto.SigninDto request) {
//        return "signup";
//    }

//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @GetMapping("/logout")
//    public String logoutForm() {
//        return "logout";
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
//
//    @GetMapping("/info")
//    public String info(Model model) {
//        model.addAttribute("message", "Info");
//        return "info";
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard(Model model, Principal principal) {
//        model.addAttribute("message", "Hello, " + principal.getName());
//        sampleService.dashboard();
//        return "dashboard";
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model, Principal principal) {
//        model.addAttribute("message", "Hello Admin, " + principal.getName());
//        return "admin";
//    }
//
//    @GetMapping("/user")
//    public String user(Model model, Principal principal) {
//        model.addAttribute("message", "Hello User, " + principal.getName());
//        return "user";
//    }
//
//    @GetMapping("/async-handler")
//    @ResponseBody
//    public Callable<String> asyncHandler() {
//        SecurityLogger.log("MVC");
//        return new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                SecurityLogger.log("Callable");
//                return "Async Handler";
//            }
//        };
//    }
//
//    @GetMapping("/async-service")
//    @ResponseBody
//    public String asyncService() {
//        SecurityLogger.log("MVC, before async service");
//        sampleService.asyncService();
//        SecurityLogger.log("MVC, after async service");
//        return "Async Service";
//    }
}
