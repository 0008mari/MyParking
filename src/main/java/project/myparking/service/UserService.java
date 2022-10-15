package project.myparking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true) // 성능향상
public class UserService {

    private final UserRepository userRepository;
//
//    /**
//     * 회원가입
//     */
//    @Transactional //변경
//    public Long join(User user) {
//        validateDuplicateUser(user); //중복 회원 검증
//        userRepository.save(user);
//        return user.getUserid();
//    }
//
//    /**
//     * 회원 중복 검증
//     */
//    private void validateDuplicateUser(User user) {
//        User findUsers =
//                userRepository.findByEmail(user.getEmail());
//        if (findUsers != null) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//
//    /**
//     * 전체 회원 조회
//     */
//    public List<User> findUsers() {
//        return userRepository.findAll();
//    }
//
//    public User findOne(Long userId) {
//        return userRepository.findOne(userId);
//    }
}
