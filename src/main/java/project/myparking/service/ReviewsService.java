package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;
import project.myparking.domain.Review;
import project.myparking.repository.ReviewRepository;
import project.myparking.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewsService {
    private final ReviewRepository reviewsRepository;

    @Transactional
    public Long save(ReviewsSaveRequestDto requestDto) {
        return reviewsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewsUpdateRequestDto requestDto) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviews.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviewsRepository.delete(reviews);
    }

    public ReviewsResponseDto findById(Long id) {
        Review entity = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReviewsResponseDto(entity);
    }

    public List<ReviewsListResponseDto> findAllAsc() {
        return reviewsRepository.findAllAsc().stream()
                .map(ReviewsListResponseDto::new)
                .collect(Collectors.toList());
    }
}