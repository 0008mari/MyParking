//package project.myparking.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///** ArgumentResolver 로 교체 */
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//            .exposedHeaders("X-AUTH-TOKEN")
//            .allowCredentials(true)
//            .allowedOrigins("http://localhost:3000");
//    }
//}
//
////    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
////        this.bearerAuthInterceptor = bearerAuthInterceptor;
////    }
////
////    public void addInterceptors(InterceptorRegistry registry){
////        System.out.println(">>> 인터셉터 등록");
////        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/info");
////    }
//
////    private static final String[] EXCLUDE_PATHS = {
////        "/parkings/**",
////        "/error/**"
////    };
////
////    @Autowired
////    private JwtInterceptor jwtInterceptor;
////
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(jwtInterceptor)
////            .addPathPatterns("/**")
////            .excludePathPatterns(EXCLUDE_PATHS);
////    }
//
//
//
////    private final LoginUserArgumentResolver loginUserArgumentResolver;
////
////    @Override
////    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
////        argumentResolvers.add(loginUserArgumentResolver);
////    }
