package com.travel.security.auth;

import com.travel.domain.User;
import com.travel.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// SecurityConfig 파일 내 loginProsessingUrl("/login");
///login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername함수가 실행
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    //시큐리티 session => Authentication => UserDetails
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id);
        if(user == null){
            throw new UsernameNotFoundException("해당 아이디가 존재하지 않습니다.");
        }
        return new UserDetails(user);
    }
}