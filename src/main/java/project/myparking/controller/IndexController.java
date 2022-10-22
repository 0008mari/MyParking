package project.myparking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.domain.Parking;
import project.myparking.global.api.CustomResponse;
import project.myparking.service.ParkingService;
import project.myparking.service.UserService;
import project.myparking.util.DBInit;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class IndexController {

    private final ParkingService parkingService;
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(ParkingController.class);

    @GetMapping("/data")
    public ResponseEntity<CustomResponse> dataInitialize() {

        HashMap<String, Object> map = new HashMap<>();
        List<Parking> dataList = new ArrayList<>();

        if (parkingService.checkIfEmpty()) {
            try {
                dataList = DBInit.run();
                parkingService.insertParkings(dataList);    // 전부 insert
            } catch (Exception e) {
                e.printStackTrace();
            }
            return CustomResponse.CommonResponse(HttpStatus.CREATED, true, "최초접근: DB 생성 성공");
        }
        return CustomResponse.CommonResponse(HttpStatus.OK, true, "DB 생성 불필요");
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
//        String token = userService.createToken(loginRequest);
//        return ResponseEntity.ok().body(new TokenResponse(token, "bearer"));
//    }
//
//    @GetMapping("/info")
//    public ResponseEntity<UserResponse> getUserFromToken(HttpServletRequest request) {
//        String name = (String) request.getAttribute("email");
//        User user = userService.findByEmail((String) request.getAttribute("email"));
//        return ResponseEntity.ok().body(UserResponse.of(user));
//    }
}
