//package project.myparking.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import project.myparking.domain.Parking;
//import project.myparking.repository.ParkingRepository;
//
//@RestController
//@RequestMapping("/parkings")
//@RequiredArgsConstructor
//public class ParkingController {
//
//    private final ParkingRepository parkingRepository;
//
////    도메인 클래스 컨버터는 Spring Data Jpa를 사용하게 되면 Common 프로젝트에 있는 클래스 인데,
////    간단하게, id-> 엔티티 타입으로 변환 해주거나, 엔티티 -> id 로 변환해 주는 컨버터를 제공해준다.
//
//    // 도메인 클래스 컨버터 사용 전
////    @GetMapping("/parkings/{id}")
////    public String findParking(@PathVariable("id") Long id) {
////        Parking parking = parkingRepository.findById(id).get();
////        return parking.getName();
////    }
//
//    // 도메인 클래스 컨버터 사용 후
//    @GetMapping("/parkings/{id}")
//    public String findParking(@PathVariable("id") Parking parking) {
//        return parking.getPARKING_NAME();
//    }
//}
