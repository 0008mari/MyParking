package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Parking;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findAllByAddressContaining(String address);
}
