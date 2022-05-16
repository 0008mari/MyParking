package project.myparking.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Parking;
import project.myparking.service.ParkingService;
import project.myparking.web.dto.ParkingListResponseDto;
import project.myparking.web.dto.ParkingRequestDto;
import project.myparking.web.dto.ParkingResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parking")
public class ParkingApiController {

    @Autowired
    private ParkingService parkingService;

    /** * 모든 회원 정보를 가져오는 API * @return ResponseEntity<List<UserResponse>> 200 OK, 회원 정보 목록 */
    @GetMapping("")
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public ResponseEntity<List<UserResponse>> getAllUser() { List<UserResponse> userList = userService.getAllUser(); return ResponseEntity.status(HttpStatus.OK).body(userList); }

    /**
     * 입력한 문구를 제목에 포함하는 주차장 목록 출력
     * Get the list of parking lots which involve the contexts typed in <form></form> tag
     * Query : SELECT p FROM Parking p WHERE p.name LIKE %:name%
     */
//    @GetMapping("/api/v1/parkings")
//    @Operation(summary = "입력한 주소의 주차장 목록 출력")
//    public List<ParkingResponseDto> findParkingByName(@RequestBody ParkingRequestDto requestDto) {
//        return parkingService.findByNameLike(requestDto.getName());
//    }

    @PostMapping("/api/v1/parkings/{addr}")
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public List<ParkingResponseDto> findParkingByName(@RequestParam String addr) {
        return parkingService.findByAddrLike(addr);
    }

    /**
     * 모든 주차장 목록 출력
     */
    @GetMapping("/api/v1/parkings")
    @Operation(summary = "모든 주차장 목록 출력")
    public List<ParkingListResponseDto> showAllParking() {
        return parkingService.findAll();
    }

}
