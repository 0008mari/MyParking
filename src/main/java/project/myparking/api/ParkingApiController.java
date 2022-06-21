package project.myparking.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Parking;
import project.myparking.exception.NotFoundException;
import project.myparking.repository.ParkingRepository;
import project.myparking.service.ParkingService;
import project.myparking.web.dto.ParkingLongDto;
import project.myparking.web.dto.ParkingShortDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parkings")
public class ParkingApiController {
    @Autowired
    private ParkingService parkingService;
    private final ParkingRepository parkingRepository;

//    ParkingApiController(ParkingRepository repository) {
//        this.parkingRepository = repository;
//    }

    @GetMapping("")
    @Operation(summary = "모든 주차장 목록 출력")
    public List<ParkingShortDto> findAll() {
        List<Parking> list = parkingRepository.findAll();
        return list.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }

    // Single item
    @GetMapping("/{id}")
    Parking one(@PathVariable Long id) {

        return parkingRepository.findOne(id);
//                .orElseThrow(() -> new NotFoundException(id));
    }

    // WRONG
//    @GetMapping("")     // ?addr={addr}
//    @Operation(summary = "입력한 주소의 주차장 목록 출력")
//    public List<ParkingLongDto> findParkingByAddr(Model model
//            , @RequestParam(value = "addr", name = "addr", required = true) String addr) throws Exception{
//        return parkingService.findByAddr(addr);
//    }

    @GetMapping("")     // ?addr={addr}
    @Operation(summary = "입력한 주소의 주차장 목록 출력")
    public List<ParkingShortDto> findParkingByAddr(@RequestParam(required = true) String addr) throws Exception{
//        return parkingService.findByAddr(addr);

        List<Parking> list = parkingRepository.findByAddr(addr);
        if(list.isEmpty()) new IllegalArgumentException(addr + " 지역의 주차장은 없습니다.\n");

        List<Parking> result = null;
        for (Parking p : list) {
            result.add(parkingRepository.findOne(p.getId()));
        }

        // Entity -> Dto Convertion
        return result.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }


    // https://spring.io/guides/tutorials/rest/
    @GetMapping("")
    CollectionModel<EntityModel<Parking>> all() {

        List<EntityModel<Parking>> parkings = parkingRepository.findAll().stream()
                .map(parking -> EntityModel.of(parking,
                        linkTo(methodOn(ParkingApiController.class).one(parking.getId())).withSelfRel(),
                        linkTo(methodOn(ParkingApiController.class).all()).withRel("parkings")))
                .collect(Collectors.toList());

        return CollectionModel.of(parkings, linkTo(methodOn(ParkingApiController.class).all()).withSelfRel());
    }


//    @GetMapping("")
//    @Operation(summary = "모든 주차장 목록 출력")
//    public ResponseEntity<List<ParkingResponseComplex>> showAllParking() {
//        List<ParkingResponseComplex> parkingList = parkingService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(parkingList); }



//    @GetMapping("/{id}")
//    EntityModel<Employee> one(@PathVariable Long id) {
//
//        Employee employee = repository.findById(id) //
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//
//        return EntityModel.of(employee, //
//                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
//    }


    // 외부 코드
//    @RestController
//    @RequestMapping("employees")
//    public class EmployeeController
//    {
//        @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
//        public Employee getEmployeeByName(@PathVariable String name) {
//
//            //pull date
//
//            return employee;
//        }
//    }


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


}
