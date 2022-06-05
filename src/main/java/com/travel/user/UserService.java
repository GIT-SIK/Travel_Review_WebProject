package com.travel.user;


import com.travel.domain.User;
import com.travel.security.auth.UserDetails;
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


    /* ************************* 유저 영역 ******************************** */

    /* 내정보 유저 탈퇴함 */
    public void deleteUser(User user) {
        if (userRepository.findById(user.getId()) != null)
            userRepository.delete(userRepository.findById(user.getId()));
    }


    /* 내정보 유저 정보 가져옴 */
    public User infoUser(UserDetails userDetails) {
        return userRepository.findById(userDetails.getUser().getId());
    }


    /* 내정보 비밀번호 변경 */
    public int updateUser(String id, String password) {
        int updateCheck = userRepository.updateUser(id, password);
        System.out.println(updateCheck);
        return updateCheck;
    }
}
