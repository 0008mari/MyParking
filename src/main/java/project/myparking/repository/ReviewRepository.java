package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r.user from Review r where r.id = :reviewId")
    User findWriter(@Param("reviewId") Long reviewId);

    @Query("select r from Review r where r.parking.id = :parkingId")
    List<Review> findAllByParkingId(@Param("parkingId") Long parkingId);

    @Query("select r from Review r where r.user.id = :userId")
    List<Review> findAllByUserId(Long userId);
    @Query("select r from Review r where r.parking.code = :parkingCode")
    Review findByParkingCode(@Param("parkingCode") String parkingCode);

    @Query("select r from Review r where r.user.id = :userid and r.parking.code = :parkingcode")
    List<Review> findByUidPid(@Param("userid") Long userId, @Param("parkingcode") String parkingcode);

    @Query("select avg(r.starScore) from Parking p, Review r where r.parking.id=:parkingid")
    int getAvgScoreFromReviewsOfThisParking(@Param("parkingid") Long parkingid);

    @Query("select count(r) from Review r where r.parking.id=:parkingid")
    int getReviewCountOfThisParking(@Param("parkingid") Long parkingid);


}