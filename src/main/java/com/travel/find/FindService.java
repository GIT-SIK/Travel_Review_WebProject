package com.travel.find;

import com.travel.domain.User;
import com.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {

    private final UserRepository userRepository;

    // 전화번호를 통해 유저 정보 찾기
    public String findIdByTel(String tel) {
        User user = userRepository.findByTel(tel);
        String message;
        if (user == null) {
            message = "회원 정보를 찾을 수 없습니다.";
        } else {
            message = "회원님의 ID는 [" + user.getId() + "] 입니다";
        }
        return message;
    }

    // 메일 주소를 통해 유저 정보 찾기
    public String findIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        String message;
        if (user == null) {
            message = "회원 정보를 찾을 수 없습니다.";
        } else {
            message = "회원님의 ID는 [" + user.getId() + "] 입니다";
        }
        return message;
    }
}
