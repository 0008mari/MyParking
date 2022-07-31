package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Parking;
import project.myparking.exception.NotFoundException;
import project.myparking.repository.ParkingRepository;
import project.myparking.repository.ReviewRepository;
import project.myparking.service.ParkingService;
import project.myparking.util.DBInit;
import project.myparking.web.dto.ParkingLongDto;
import project.myparking.web.dto.ParkingShortDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ParkingApiController {

    @Autowired
    private ParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingApiController.class);


    @GetMapping("")
    @Operation(summary = "메인화면 최초 접속 이전에 DB 1회 생성 (DB는 서버를 올릴때 한번만 땡겨온다)")
    public String initDB() {

        String msg = "NO";
        logger.info("ParkingApiController initDB() ");

        List<Parking> dataList = new ArrayList<>();

        // openApi pull
        if (parkingService.checkDB() == 0) {
            logger.info("ParkingApiController insert parkinglots() ");
            try {
                dataList = DBInit.run();
                System.out.println(dataList.size());
//                /** product table insert data */
//                for (int i = 0; i < dataList.size(); i++) {
//                    parkingService.insertOneParking(dataList.get(i));
//                    System.out.println(i + " : " + productList.get(i).toString());
//                }
                parkingService.DBInit(dataList);

            } catch (Exception e) {
                e.printStackTrace();
            }
            msg = "YES";
        } else {
            logger.info("database already exists NO NEED to refresh");
            msg = "NO";
        }
        return msg;
    }

    @Operation(summary = "메인 화면에서는 DB 속의 주차장 전체 목록 출력")
    @GetMapping("/parkings/all")
    public List<ParkingLongDto> findAll() {
        return parkingService.findAll();
    }

    // 원래 get 인데 충돌나서 post 로 잠시 변경
    @Operation(summary = "입력한 동네의 주차장 출력")
    @GetMapping("/parkings")
    public List<ParkingShortDto> getParkingByAddress(@RequestParam String address) {
        return parkingService.findByAddress(address);
    }

    @Operation(summary = "id값을 가진 주차장 반환")
    @GetMapping("/parkings/{parkingid}")
    public ParkingShortDto getParkingById(@PathVariable Long parkingid) {
        return parkingService.getParkingById(parkingid);
    }



}
