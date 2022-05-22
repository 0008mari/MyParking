package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.myparking.domain.Parking;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Query("select p from Parking p where p.name like '%?1%'")
    List<Parking> findByNameLike(String name);
}
