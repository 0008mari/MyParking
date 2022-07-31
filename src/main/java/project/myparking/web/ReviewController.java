//package project.myparking.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import project.myparking.domain.Parking;
//import project.myparking.domain.User;
//import project.myparking.service.ParkingService;
//import project.myparking.service.ReviewService;
//import project.myparking.service.UserService;
//import project.myparking.web.dto.ParkingLongDto;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class ReviewController {
//
//    private final ReviewService reviewService;  // 생성자 주입
//    private final UserService userService;
//    private final ParkingService parkingService;
//
//    @GetMapping(value = "/review")
//    public String createForm(Model model) {
//        List<User> users = userService.findUsers();
//        List<ParkingLongDto> parkings = parkingService.findAll();
//        model.addAttribute("users", users);
//        model.addAttribute("parkings", parkings);
//
//        // return view (Not in use)
//        return "order/orderForm";
//    }
//
//    @PostMapping(value = "/review")
//    public String order(@RequestParam("userId") Long userId,
//                        @RequestParam("parkingId") Long parkingId,
//                        @RequestParam("evalSpace") int evalSpace,
//                        @RequestParam("evalCostefficient") int evalCostefficient,
//                        @RequestParam("evalParkinglevel") int evalParkinglevel,
//                        @RequestParam("evalStaff") int evalStaff,
//                        @RequestParam("evalRevisit") int evalRevisit
//                        ) {
//        reviewService.review(parkingId, userId, );
//        return "redirect:/orders";
//    }
//}
