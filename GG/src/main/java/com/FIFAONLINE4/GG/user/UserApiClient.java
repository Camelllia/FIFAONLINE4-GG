package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class UserApiClient {

    private final RestTemplate restTemplate;

    private final String API_KEY = "";

    private final String userInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";

    private final String userDivisionUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/maxdivision";

    private final String userTradeUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/markets?tradetype={tradetype}&offset=0&limit=10";

    public UserResponseDto requestUserInfo(String nickname) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, UserResponseDto.class, nickname).getBody();
    }

    public UserDivisionResponseDto[] requestUserDivision(String accessId) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(userDivisionUrl, HttpMethod.GET, entity, UserDivisionResponseDto[].class, accessId).getBody();
    }

    public UserTradeResponseDto[] requestUserTrade(String accessId, String tradeType) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(userTradeUrl, HttpMethod.GET, entity, UserTradeResponseDto[].class, accessId, tradeType).getBody();
    }

}