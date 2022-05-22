package project.myparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.myparking.domain.Keyword;
import project.myparking.domain.Review;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

//    List<Keyword> findAllAsc();
}
