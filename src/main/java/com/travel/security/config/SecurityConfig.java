package com.travel.security.config;

import com.travel.security.auth.UserDetailsService;
import com.travel.security.handler.LoginFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement() // 세션관리
                .maximumSessions(1)
                .expiredUrl("/user/login")
                .maxSessionsPreventsLogin(false);

        http.authorizeRequests()
//              .antMatchers("/user/mypage").authenticated() // 로그인할 경우에만 진입가능한 경로 (마이페이지 없기 때문에 주석처리)
                .antMatchers("/user/admin/**").access("hasRole('ROLE_ADMIN')") // admin으로 시작하는 주소는 ROLE_ADMIN만 접근가능
                .antMatchers("/board/write").authenticated()//로그인시 후기작성
                .anyRequest().permitAll() // 모든 사람이 접근할 수 있다.
              .and()
                .formLogin()
                .loginPage("/user/login") // 로그인페이지 설정
                .loginProcessingUrl("/login") // login주소가 호출시 시큐리티가 낚아채서 CustomUserDetails 로 이동
                .defaultSuccessUrl("/") //로그인 성공 후
                .failureHandler(loginFailHandler()) // 로그인 실패시 핸들러로 오류 메시지 출력
              .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // 로그아웃경우 기본경로 위치설정
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);


        http
                .exceptionHandling()
                .accessDeniedPage("/autherror");
    }


    // 회원가입할 때나 비밀번호 변경할 때 사용될 Encode
    @Bean
    public  BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    
    // 기본적으로 로그인할 때 BCrypt타입 password를 passwordEncoder 사용하여 userDetailsService로 반환
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }
}