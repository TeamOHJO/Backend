package com.example.yanolja.global.springsecurity;

import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

      /*  System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken());
        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());*/

        OAuth2User oauth2User = super.loadUser(userRequest);

        OAuth2UserInfo oauth2Userinfo = null;
        String provider = userRequest.getClientRegistration()
            .getRegistrationId(); //google kakao facebook...

        if (provider.equals("google")) {
            oauth2Userinfo = new GoogleUserInfo(oauth2User.getAttributes());
        } else if (provider.equals("naver")) {
            oauth2Userinfo = new NaverUserInfo((Map) oauth2User.getAttributes().get("response"));
        }

        Optional<User> user = userRepository.findByEmailAndProvider(
            oauth2Userinfo.getEmail(), oauth2Userinfo.getProvider());

        //이미 소셜로그인을 한적이 있는지 없는지
        if (user.isEmpty()) {
            User newUser = User.builder()
                .email(oauth2Userinfo.getEmail())
                .username(oauth2Userinfo.getName())
                .password("OAuth2")  //Oauth2로 로그인을 해서 패스워드는 의미없음.
                .phonenumber(oauth2Userinfo.getPhoneNumber())
                .authority("ROLE_USER")
                .provider(provider)
                .build();

            userRepository.save(newUser);
            return new PrincipalDetails(newUser, oauth2User.getAttributes());
        } else {
            return new PrincipalDetails(user.get(), oauth2User.getAttributes());
        }
    }
}
