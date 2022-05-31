package com.travel.find;

import com.travel.domain.User;
import com.travel.user.UserRepository;
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

    // 해당 id의 정보와 email이 일치하는가
    public int checkEmail(String id, String email) {
        User user = userRepository.findById(id);
        if (user.getEmail().equals(email)) {
            return 1;
        }
        return 0;
    }

    // 해당 id의 정보와 전화번호가 일치하는가
    public int checkTel(String id, String tel) {
        User user = userRepository.findById(id);
        if (user.getTel().equals(tel)) {
            return 1;
        }
        return 0;
    }

    public void changePassword(String id, String password) {
        User user = userRepository.findById(id);
        user.setPassword(password);
        updateUser(user);
    }

    //유저의 값이 존재하면 수정 없으면 저장 안함
    public void updateUser(User user) {
        if (userRepository.findById(user.getId()) != null)
            userRepository.save(user);
    }
}
