package project.myparking.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.myparking.service.ParkingService;
import project.myparking.service.ReviewsService;
import project.myparking.web.dto.ReviewsResponseDto;

@RequiredArgsConstructor
@Controller
public class IndexController {

    // Parking : Read-Only , Reviews : CRUD
    private final ParkingService parkingService;
    private final ReviewsService reviewsService;

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("posts", ReviewsService.findAllAsc());
//        // 향후 로그인 구현시 user must not be null
////        if (user != null) {
////            model.addAttribute("userName", user.getName());
////        }
//        return "index";
//    }

    @GetMapping("/reviews/save")
    public String postsSave() {
        return "posts-save";
    }


    @GetMapping("/reviews/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        ReviewsResponseDto dto = reviewsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}