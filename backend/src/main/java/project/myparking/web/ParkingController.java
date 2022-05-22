package project.myparking.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingRepository parkingRepository;

    // 도메인 클래스 컨버터 사용 전
//    @GetMapping("/parkings/{id}")
//    public String findParking(@PathVariable("id") Long id) {
//        Parking parking = parkingRepository.findById(id).get();
//        return parking.getName();
//    }

    // 도메인 클래스 컨버터 사용 후
    @GetMapping("/parkings/{id}")
    public String findParking(@PathVariable("id") Parking parking) {
        return parking.getName();
    }
}
