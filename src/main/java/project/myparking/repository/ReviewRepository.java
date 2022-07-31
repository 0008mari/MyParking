package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("delete from Review r where r.reviewid = :reviewid")
    void deleteByRid(@Param("reviewid") Long reviewid);

    @Query("select r.user from Review r where r.reviewid = :reviewid")
    User findWriter(@Param("reviewid") Long reviewid);

    @Query("select r from Review r where r.parking.parkingid = :parkingid")
    List<Review> findReviewsByPid(@Param("parkingid") Long parkingid);

    @Query("select r from Review r where r.reviewid = :reviewid")
    Review findOne(@Param("reviewid") Long reviewid);

    @Query("select r from Review r where r.user.userid = :userid")
    List<Review> findByUid(@Param("userid") Long userid);

    @Query("select r from Review r where r.parking.PARKING_CODE = :parkingCode")
    Review findByPCode(@Param("parkingCode") String parkingCode);

    @Query("select r from Review r where r.user.userid = :userid and r.parking.PARKING_CODE = :parkingcode")
    List<Review> findByUidPid(@Param("userid") Long userId, @Param("parkingcode") String parkingcode);

    @Query("select avg(r.starScore) from Parking p, Review r where r.parking.parkingid=:parkingid")
    int getAvgScoreFromReviewsOfThisParking(@Param("parkingid") Long parkingid);

    @Query("select count(r) from Review r where r.parking.parkingid=:parkingid")
    int getReviewCountOfThisParking(@Param("parkingid") Long parkingid);


}