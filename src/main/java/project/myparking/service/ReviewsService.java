package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Long save(ReviewSaveRequest requestDto) {
        return reviewsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewUpdateRequest request) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviews.update(request.getStarScore());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Review reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        reviewsRepository.delete(reviews);
    }

    public ReviewResponse findById(Long id) {
        Review entity = reviewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new ReviewResponse(entity);
    }

    public List<ReviewResponse> findAllAsc() {
        return reviewsRepository.findAllAsc().stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }
}