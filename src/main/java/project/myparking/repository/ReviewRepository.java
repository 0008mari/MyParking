package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import java.util.List;
import project.myparking.domain.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    List<Review> findAllByParking(String parkingCode);
    List<Review> findAllByParking(Parking parking);

    List<Review> findAllByUser(User user);

}