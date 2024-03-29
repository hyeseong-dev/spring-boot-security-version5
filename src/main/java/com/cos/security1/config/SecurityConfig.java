package com.cos.security1.config;


// 1)코드받기(인증) 2) 엑세스토큰(권한)
// 3)사용자프로필 정보 가져오기 4-1)정보를 토대로 회원가입을 자동으로 진행함.
// 4-2) (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> <-(집주소), 백화점몰 -> (VIP등급, 일반등급)


import com.cos.security1.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    // 해당 메서드의 리턴되는 객체를 IoC로 등록함.
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .loginPage("/loginForm")
            .userInfoEndpoint()
            .userService(principalOauth2UserService)
                                    // 구글 로그인이 완료된 다음 후 처리가 필요함. TIP 코드X(액세슥토큰 + 사용자 프로필 정보 O)
        ;
    }
}
