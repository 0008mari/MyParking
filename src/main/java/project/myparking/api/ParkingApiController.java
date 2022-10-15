package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.global.api.CustomResponse;
import project.myparking.service.ParkingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parkings")
public class ParkingApiController {

    private ParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingApiController.class);
    private final HttpSession httpSession;

    @Operation(summary = "입력한 동네의 주차장 출력")
    @GetMapping
    public ResponseEntity<CustomResponse> getParkingByAddress(@RequestParam String address) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.getParkingByAddress(address));
    }

    @Operation(summary = "id값을 가진 주차장 반환")
    @GetMapping("/{parkingId}")
    public ResponseEntity<CustomResponse> getParkingById(@PathVariable Long parkingId) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.getParkingById(parkingId));
    }

    @Operation(summary = "MAIN PAGE 메인 화면에서는 DB 속의 주차장 전체 목록 출력")
    @GetMapping("/list")
    public ResponseEntity<CustomResponse> findAll() {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "초기 DB 생성 여부 반환 성공", parkingService.getAll());
    }
}
