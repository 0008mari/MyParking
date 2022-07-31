package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Parking;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    // Frame 0
    @Query("select count(p) from Parking p")
    int checkDB();

//    int save();

    // Frame 3   JPA E.g. findByEmployeeNameContaining("Jone");
    @Query("select p from Parking p where p.ADDR like %:addr%")
    List<Parking> findByAddress(@Param("addr") String address);

    @Query("select p from Parking p where p.parkingid =:parkingid")
    Parking findOne(@Param("parkingid") Long parkingid);
}
