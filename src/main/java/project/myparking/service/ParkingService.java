package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.repository.ParkingRepository;
import project.myparking.web.dto.ParkingLongDto;
import project.myparking.web.dto.ParkingShortDto;
import project.myparking.web.dto.ReviewDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public int checkDB() {
        return parkingRepository.checkDB();
    }

    public void DBInit(List<Parking> parkingList) {
//        int chk = 0;
//        for(int i=0; i<cnt; i++){
//            parkingRepository.insertOne();
//        }
//        return chk;
        parkingRepository.saveAll(parkingList);
    }


    @Transactional(readOnly = true)
    public List<ParkingShortDto> findByAddress(String address) {
        List<Parking> list = parkingRepository.findByAddress(address);
        if (list.isEmpty()) new IllegalArgumentException(address + " 지역의 주차장은 없습니다.\n");

        // parkingRepository 결과로 넘어온 Parking의 stream을 map을 통해 List로 반환
        return list.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ParkingLongDto> findAll() {
        List<Parking> list = parkingRepository.findAll();

        return list.stream()
                .map(ParkingLongDto::new)
                .collect(Collectors.toList());

        // Converting Entity to DTO
//        if(list.isEmpty()) new IllegalArgumentException("주차장 DB 먼저 생성해주세요!!\n");
//        // parkingRepository 결과로 넘어온 Parking의 stream을 map을 통해 List<ParkingResponseComplex>로 반환
    }

    @Transactional(readOnly = true)
    public ParkingShortDto getParkingById(Long parkingid) {
        Parking p = parkingRepository.findOne(parkingid);

        ParkingShortDto pShortDto = new ParkingShortDto(
                p.getPARKING_NAME(),
                p.getAvgScore(),
                p.getReviewCount(),
                p.getADDR()
        );
        return pShortDto;
        //        return list.stream()
//                .map(ParkingShortDto::new)
    }
}

