package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Review;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {


//    @Query("SELECT r FROM Review r ORDER BY r.id ASC")
//    List<Review> findAllAsc();

    @Query("select r from Review r where r.parking.id = :pid")
    List<Review> findByPid(@Param("pid") Long pid);

    @Query("select r from Review r where r.parking.PARKING_CODE = :parkingCode")
    List<Review> findByParkingCode(@Param("parkingCode") String parkingCode);

    @Query("select r from Review r where r.user = :userId and r.parking.PARKING_CODE = :parkingCode")
    List<Review> findByUserParking(@Param("userId") Long userId, @Param("parkingCode") String parkingCode);


}