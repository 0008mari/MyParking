package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.myparking.domain.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByParking(String parkingCode);
    List<Review> findAllByUser(Long userId);

}