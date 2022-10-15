package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.global.exception.NoDataException;
import project.myparking.repository.ParkingRepository;
import project.myparking.dto.ParkingLongDto;
import project.myparking.dto.ParkingShortDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Long checkParkingCnt() {
        return parkingRepository.count();
    }

    public void insertParkings(List<Parking> parkingList) {
        parkingRepository.saveAll(parkingList);
    }

    @Transactional(readOnly = true)
    public List<ParkingShortDto> findByAddress(String address) {
        List<Parking> list = parkingRepository.findAllByAddressContaining(address);
        if (list.isEmpty()) throw new NoDataException();

        return list.stream()
                .map(ParkingShortDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ParkingLongDto> getAll() {
        List<Parking> list = parkingRepository.findAll();

        return list.stream()
                .map(ParkingLongDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ParkingShortDto getParkingById(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(()-> new NoDataException());

        return new ParkingShortDto(parking.getName(), parking.getStarAvg(), parking.getReviews().size(), parking.getAddress());
    }
}

