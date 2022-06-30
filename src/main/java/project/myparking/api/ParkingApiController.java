package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import project.myparking.web.dto.ParkingLongDto;
import project.myparking.web.dto.ParkingShortDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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
    private final ParkingRepository parkingRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("")
    @Operation(summary = "메인화면 최초 접속 이전에 DB 1회 생성 (DB는 서버를 올릴때 한번만 땡겨온다)")
    public void load_save(@RequestParam("addr") String addr, Model model){
        String result = "";
        String key = "7559687550736b79373173754a5357";
        try {
            String requestAddr=addr;
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + key +
                    "/json/GetParkInfo/1/10/" + requestAddr);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject GetParkInfo = (JSONObject)jsonObject.get("GetParkInfo");
            Long totalCount=(Long)GetParkInfo.get("list_total_count");

            JSONObject subResult = (JSONObject)GetParkInfo.get("RESULT");
            JSONArray infoArr = (JSONArray) GetParkInfo.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                Parking infoObj=new Parking(
                        i+(long)1,
                        (String)tmp.get("PARKING_NAME"),
                        (String)tmp.get("ADDR"),
                        (String)tmp.get("PARKING_CODE"),
                        (String)tmp.get("PARKING_TYPE_NM"),
                        (String)tmp.get("OPERATION_RULE_NM"),
                        (String)tmp.get("TEL"),
                        (double)tmp.get("CAPACITY"),
                        (String)tmp.get("PAY_NM"),
                        (String)tmp.get("WEEKDAY_BEGIN_TIME"),
                        (String)tmp.get("WEEKDAY_END_TIME"),
                        (String)tmp.get("SYNC_TIME"),
                        (double)tmp.get("RATES"),
                        (double)tmp.get("TIME_RATE"),
                        (double)tmp.get("ADD_RATES"),
                        (double)tmp.get("ADD_TIME_RATE"),
                        (double)tmp.get("LAT"),
                        (double)tmp.get("LNG"));

                parkingRepository.save(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Operation(summary = "주차장 전체 목록 출력")
    @GetMapping("/parkings")
    public List<ParkingShortDto> findAll() {
        List<Parking> list = parkingRepository.findAll();
        return list.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "입력한 동네의 주차장 출력")
    @GetMapping("/parking")
    public List<ParkingShortDto> getParkingByAddr(@RequestParam String addr) {
        List<Parking> list = parkingRepository.findByAddr(addr);
        return list.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }

    // Single item
    @Operation(summary = "id값을 가진 주차장 반환")
    @GetMapping("/parkings/{id}")
    public ParkingShortDto getParkingById(@PathVariable Long id) {
        // List<Parking> list = parkingRepository.findOne(id);
        Parking p = parkingRepository.findOne(id);

        ParkingShortDto pShortDto = new ParkingShortDto(
                p.getPARKING_NAME(),
                p.getAvgScore(),
                p.getReviewCount(),
                p.getADDR()
        );
        return pShortDto;
//        return list.stream()
//                .map(ParkingShortDto::new)
//                .collect(Collectors.toList());
//                .orElseThrow(() -> new NotFoundException(id));
    }

    /**
     * POST 나 PUT 을 사용하는 메소드에는 @RequestBody 로 받아오고,
     * GET 메소드는 @RequestParam 어노테이션 사용
     * @RequestParam vs @PathVariable
     *
     * => 아닌것 같기도
     */


//    @PostMapping("")
//    @Operation(summary = "입력한 주소의 주차장 목록 출력 With RequestBody")
//    public List<ParkingResponseComplex> findParkingByAddr(@RequestBody @Valid
//                                                     ParkingRequest request) {
//        return parkingService.findByAddr(request.getAddr());
//    }


//     WRONG
//    @GetMapping("")     // ?addr={addr} : 그냥 requestParameter 쓰면 이게 spring 에서 자동으로 query url 로 전달된다4
//    @Operation(summary = "입력한 주소의 주차장 목록 출력")
//    public List<ParkingLongDto> findParkingByAddr(Model model
//            , @RequestParam(value = "addr", name = "addr", required = true) String addr) throws Exception{
//        return parkingService.findByAddr(addr);
//    }


    // https://spring.io/guides/tutorials/rest/
//    @GetMapping("")
//    CollectionModel<EntityModel<Parking>> all() {
//
//        List<EntityModel<Parking>> parkings = parkingRepository.findAll().stream()
//                .map(parking -> EntityModel.of(parking,
//                        linkTo(methodOn(ParkingApiController.class).one(parking.getId())).withSelfRel(),
//                        linkTo(methodOn(ParkingApiController.class).all()).withRel("parkings")))
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(parkings, linkTo(methodOn(ParkingApiController.class).all()).withSelfRel());
//    }


//    @GetMapping("")
//    @Operation(summary = "모든 주차장 목록 출력")
//    public ResponseEntity<List<ParkingResponseComplex>> showAllParking() {
//        List<ParkingResponseComplex> parkingList = parkingService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(parkingList); }


    // 외부 코드
//    @RestController
//    @RequestMapping("employees")
//    public class EmployeeController
//    {
//        @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
//        public Employee getEmployeeByName(@PathVariable String name) {
//            //pull date
//            return employee;
//        }
//    }
}
