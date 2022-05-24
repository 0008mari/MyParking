package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.myparking.service.ParkingService;
import project.myparking.web.dto.ParkingLongDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parking")
public class ParkingApiController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("")
    @Operation(summary = "모든 주차장 목록 출력")
    public List<ParkingLongDto> showAllParking() {
        return parkingService.findAll();
    }
//    @GetMapping("")
//    @Operation(summary = "모든 주차장 목록 출력")
//    public ResponseEntity<List<ParkingResponseComplex>> showAllParking() {
//        List<ParkingResponseComplex> parkingList = parkingService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(parkingList); }

    @GetMapping("?addr={addr}")
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public List<ParkingLongDto> findParkingByAddr(Model model
            , @RequestParam(value = "addr", name = "addr", required = true) String addr) throws Exception{
        return parkingService.findByAddr(addr);
    }

/**
 * POST 나 PUT 을 사용하는 메소드에는 @RequestBody 로 받아오고, 
 * GET 메소드는 @RequestParam 어노테이션 사용
 * @RequestParam vs @PathVariable
 */

//    @PostMapping("")
//    @Operation(summary = "입력한 주소의 주차장 목록 출력 With RequestBody")
//    public List<ParkingResponseComplex> findParkingByAddr(@RequestBody @Valid
//                                                     ParkingRequest request) {
//        return parkingService.findByAddr(request.getAddr());
//    }


}
