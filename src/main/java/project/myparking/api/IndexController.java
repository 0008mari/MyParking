package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.config.auth.LoginUser;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.Parking;
import project.myparking.global.api.CustomResponse;
import project.myparking.service.ParkingService;
import project.myparking.util.DBInit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/initialize")
public class IndexController {

    private ParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingApiController.class);
    private final HttpSession httpSession;

    @GetMapping
    @Operation(summary = "메인화면 최초 접속 이전에 DB 1회 생성 (DB는 서버를 올릴때 한번만 땡겨온다)" +
        "session 에 로그인 되어있는 사용자가 있는지도 확인하여 있으면 User 를 반환" )
    public ResponseEntity<CustomResponse> initDB(@LoginUser SessionUser user) {

        HashMap<String, Object> map = new HashMap<>();
        logger.info("ParkingApiController initDB() ");

        List<Parking> dataList = new ArrayList<>();

        // openApi pull
        if (parkingService.checkParkingCnt() == 0) {
            logger.info("ParkingApiController insert parkinglots() ");
            try {
                dataList = DBInit.run();
                parkingService.insertParkings(dataList);    // 전부 insert

            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("msg", "Parkinglot Data: FIRST TIME CREATED");

        } else {
            logger.info("database already exists NO NEED to refresh");
            map.put("msg", "Parkinglot Data: ALREADY EXISTS");
        }
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", map);
    }

}
