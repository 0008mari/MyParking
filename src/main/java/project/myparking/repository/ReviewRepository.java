package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.parking.id = :parkingId")
    List<Review> findAllByParkingId(@Param("parkingId") Long parkingId);

    @Query("select r from Review r where r.user.id = :userId")
    List<Review> findAllByUserId(Long userId);
    @Query("select r from Review r where r.parking.code = :parkingCode")
    Review findByParkingCode(@Param("parkingCode") String parkingCode);

}