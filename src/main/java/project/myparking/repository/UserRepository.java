package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.myparking.domain.Parking;
import project.myparking.domain.Review;
import project.myparking.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id =:userId")
    User findOne(@Param("userId") Long userId);

    @Query("SELECT u FROM User u where u.email =:email")
    User findByEmail(@Param("email")String email);
}