package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.myparking.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //List<Review> findById(Long id);

    @Query("SELECT p FROM Review p ORDER BY p.id ASC")
    List<Review> findAllAsc();
}