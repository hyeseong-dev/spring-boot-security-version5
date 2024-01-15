package com.cos.security1.config.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        //accessToken으로 서드파티에 요청하여 사용자 정보를 얻어옴
        log.debug("--".repeat(10) +"loadUser 메서드 호출!!!!" );
        System.out.println(userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: " + oAuth2User.getAttributes());

        // 회원 가입을 강제로 진행할 예정
        return oAuth2User;
    }
}
