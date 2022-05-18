package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.myparking.service.ParkingService;
import project.myparking.web.dto.ParkingResponseComplex;
import project.myparking.web.dto.ParkingResponseSimple;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parking")
public class ParkingApiController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("")
    @Operation(summary = "모든 주차장 목록 출력")
    public List<ParkingResponseComplex> showAllParking() {
        return parkingService.findAll();
    }

//    @GetMapping("")
//    @Operation(summary = "모든 주차장 목록 출력")
//    public ResponseEntity<List<ParkingResponseComplex>> showAllParking() {
//        List<ParkingResponseComplex> parkingList = parkingService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(parkingList); }


    @GetMapping("/{addr}")
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public List<ParkingResponseComplex> findParkingByAddr_P(@PathVariable String addr) {
        return parkingService.findByAddr(addr);
    }

    @PostMapping("{addr}")
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public List<ParkingResponseComplex> findParkingByAddr_R(@RequestParam String addr) {
        return parkingService.findByAddr(addr);
    }


}
