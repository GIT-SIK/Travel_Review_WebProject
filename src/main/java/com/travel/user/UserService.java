package com.travel.user;


import com.travel.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public void signupUser(User user){
        userRepository.save(user);

    }

    public int idCheck(String id){
        User user = userRepository.findByUserId(id);
        if(user == null) {
            return 0;
        } else if( user.getUserId().equals(id)) {
            return 1;
        }
            return 0;

    }

}
