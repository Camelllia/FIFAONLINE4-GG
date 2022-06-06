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

    private final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50X2lkIjoiMTMwOTQzNTAwNiIsImF1dGhfaWQiOiIyIiwidG9rZW5fdHlwZSI6IkFjY2Vzc1Rva2VuIiwic2VydmljZV9pZCI6IjQzMDAxMTQ4MSIsIlgtQXBwLVJhdGUtTGltaXQiOiI1MDA6MTAiLCJuYmYiOjE2NTM4OTk5OTIsImV4cCI6MTY2OTQ1MTk5MiwiaWF0IjoxNjUzODk5OTkyfQ.R5Gxow4X-BlCVh2q_qMjUgWlCme55uZ1-tpYQccjBIw";

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