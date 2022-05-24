package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;
import project.myparking.web.dto.ParkingLongDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    @Transactional(readOnly = true)
    public List<ParkingLongDto> findByAddr(String addr) {
        List<Parking> list = parkingRepository.findByAddr(addr);
        if(list.isEmpty()) new IllegalArgumentException(addr + " 지역의 주차장은 없습니다.\n");

        // parkingRepository 결과로 넘어온 Parking의 stream을 map을 통해 List로 반환
        return list.stream()
                .map(ParkingLongDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Parking> findAll() {
        List<Parking> list = parkingRepository.findAll();

        // Converting Entity to DTO
//        if(list.isEmpty()) new IllegalArgumentException("주차장 DB 먼저 생성해주세요!!\n");
//        // parkingRepository 결과로 넘어온 Parking의 stream을 map을 통해 List<ParkingResponseComplex>로 반환
//        return list.stream()
//                .map(ParkingResponseComplex::new)
//                .collect(Collectors.toList());
    }

}

