package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import java.util.HashMap;
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
public class ParkingController {

    private final ParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingController.class);

    @Operation(summary = "id값을 가진 주차장 반환")
    @GetMapping("/{parkingId}")
    public ResponseEntity<CustomResponse> getParkingById(@PathVariable Long parkingId) {
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "주차장 ID로 주차장 조회 성공", parkingService.getParkingById(parkingId));
    }

    @Operation(summary = "입력한 동네의 주차장 출력")
    @GetMapping
    public ResponseEntity<CustomResponse> getParkingByAddress(@RequestParam String address) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parkingList", parkingService.getParkingByAddress(address));
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "검색어로 주차장 조회 성공", map);
    }

    @Operation(summary = "MAIN PAGE 메인 화면에서는 DB 속의 주차장 전체 목록 출력")
    @GetMapping("/list")
    public ResponseEntity<CustomResponse> findAll() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("parkingList", parkingService.getAll());
        return CustomResponse.CommonResponse(HttpStatus.OK, true,
            "모든 주차장목록 조회 성공", map);
    }
}
