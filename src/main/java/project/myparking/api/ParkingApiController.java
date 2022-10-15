package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.myparking.config.auth.LoginUser;
import project.myparking.config.auth.dto.SessionUser;
import project.myparking.domain.Parking;
import project.myparking.global.api.CustomResponse;
import project.myparking.service.ParkingService;
import project.myparking.util.DBInit;
import project.myparking.dto.ParkingLongDto;
import project.myparking.dto.ParkingShortDto;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ParkingApiController {

    private ParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingApiController.class);
    private final HttpSession httpSession;

    @GetMapping("/initialize")
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

    @Operation(summary = "MAIN PAGE 메인 화면에서는 DB 속의 주차장 전체 목록 출력")
    @GetMapping("/parking/list")
    public ResponseEntity<CustomResponse> findAll() {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.getAll());
    }

    // 원래 get 인데 충돌나서 post 로 잠시 변경
    @Operation(summary = "입력한 동네의 주차장 출력")
    @GetMapping("/parking")
    public ResponseEntity<CustomResponse> getParkingByAddress(@RequestParam String address) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.findByAddress(address));
    }

    @Operation(summary = "id값을 가진 주차장 반환")
    @GetMapping("/parkings/{parkingid}")
    public ResponseEntity<CustomResponse> getParkingById(@PathVariable Long parkingid) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.getParkingById(parkingid));
    }

}
