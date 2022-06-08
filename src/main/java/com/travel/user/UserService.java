package com.travel.user;


import com.travel.domain.Board;
import com.travel.domain.User;
import com.travel.security.auth.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /* ************************* 관리자 영역 ******************************** */

    /* 관리자페이지 : 유저 전체를 가져옴 */
    public Page<User> findAllUser(Pageable pageable) {
        String column = "id";
        Sort sort = Sort.by(column).descending();
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                10,
                sort);

            return userRepository.findAllUser(pageable);

    }


    /* 관리자가 유저를 탈퇴함 */
    public boolean deleteUserAdmin(String role, String id) {
        if(role.equals("ROLE_ADMIN")){
            userRepository.deleteUser(id);
            return true;
        } else {
            return false;
        }
    }

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
        return updateCheck;
    }

}
