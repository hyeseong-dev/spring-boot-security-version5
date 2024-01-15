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
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest){
        //accessToken으로 서드파티에 요청하여 사용자 정보를 얻어옴
        log.debug("--".repeat(10) +"loadUser 메서드 호출!!!!" );
        System.out.println(oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return null;
    }
}
