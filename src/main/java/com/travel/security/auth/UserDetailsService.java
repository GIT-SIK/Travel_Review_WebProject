package com.travel.security.auth;

import com.travel.domain.User;
import com.travel.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProsessingUrl("/login");
///login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername함수가 실행
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    //시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername");
        User user = userRepository.findById(id);
        System.out.println("user = " + user);
        if(user == null){
            throw new UsernameNotFoundException("해당 아이디의 유저가 없습니다.");
        }
        return new UserDetails(user);
    }
}