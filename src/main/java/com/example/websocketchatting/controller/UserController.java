package com.example.websocketchatting.controller;

import com.example.websocketchatting.domain.dto.UserJoinRequest;
import com.example.websocketchatting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/join")
    private String join(@RequestBody UserJoinRequest request) {

        userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return "회원가입 성공";
    }
}
