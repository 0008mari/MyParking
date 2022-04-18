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

@RequiredArgsConstructor
@RestController
// @Tag(name = "Parking", description = "주차장 API")
public class ParkingApiController {

    private final ParkingService parkingService;    // Read-Only

    /**
     * 입력한 문구를 제목에 포함하는 주차장 목록 출력
     * Get the list of parking lots which involve the contexts typed in <form></form> tag
     * Query : SELECT p FROM Parking p WHERE p.name LIKE %:name%
     */
    @Operation(summary = "입력한 문구를 제목에 포함하는 주차장 목록 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public List<ParkingResponseDto> findParkingByName(@RequestBody ParkingRequestDto requestDto) {
        return parkingService.findByNameLike(requestDto.getName());
    }

    /**
     * 모든 주차장 목록 출력
     */
    @GetMapping("/api/v1/parkings")
    @Operation(summary = "모든 주차장 목록 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    public List<ParkingListResponseDto> showAllParking() {
        return parkingService.findAll();
    }

}
