package com.example.securityspring.service;

import com.example.securityspring.domain.Member;
import com.example.securityspring.dto.MemberSignupRequestDto;
import com.example.securityspring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(MemberSignupRequestDto request) {
        boolean existMember = userRepository.existsById(request.getEmail());

        // 이미 회원이 존재하는 경우
        if (existMember) return null;

        User user = new User(request);
        user.encryptPassword(passwordEncoder);

        userRepository.save(user);
        return user.getEmail();
    }
}