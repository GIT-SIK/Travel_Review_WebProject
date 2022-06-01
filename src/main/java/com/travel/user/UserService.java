package com.travel.user;


import com.travel.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;



    /* ************************************* 회원가입 ************************************* */
    
    /* 회원가입 영역 */
    public void signupUser(User user){
        userRepository.save(user);
    }

    /* 중복된 아이디 */
    public boolean isId(String id){
        User user = userRepository.findById(id);
        if(user == null) {
            return false;
        } else if( user.getId().equals(id)) {
            return true;
        } else {
            return false;
        }
    }
    /* ************************************* 회원가입 끝 ************************************* */
}
