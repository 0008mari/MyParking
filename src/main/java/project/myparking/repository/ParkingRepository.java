package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Parking;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Query("select p from Parking p where p.ADDR like '%:addr%'")
    List<Parking> findByAddr(@Param("addr") String addr);

    @Query("select p from Parking p where p.id =:pid")
    Parking findOne(@Param("pid") Long pid);
}
