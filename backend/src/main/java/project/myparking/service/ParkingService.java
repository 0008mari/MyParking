package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.api.ParkingApiController;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;
import project.myparking.web.dto.ParkingListResponseDto;
import project.myparking.web.dto.ParkingResponseDto;
import project.myparking.web.dto.ReviewsListResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public List<ParkingResponseDto> findByNameLike(String name) {
        List<Parking> list = parkingRepository.findByNameLike(name);
        if(list.isEmpty()) new IllegalArgumentException("해당 이름의 주차장은 없습니다. name=" + name);

        return list.stream()
                .map(ParkingResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ParkingListResponseDto> findAll() {
        return parkingRepository.findAll().stream()
                .map(ParkingListResponseDto::new)
                .collect(Collectors.toList());
    }
}
