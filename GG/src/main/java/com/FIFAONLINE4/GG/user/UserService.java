package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserApiClient userApiClient;

    public UserResponseDto searchUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }

    public UserDivisionResponseDto[] searchUserDivision(String accessid) {

        UserDivisionResponseDto[] userDivisionInfos = null;
        userDivisionInfos = userApiClient.requestUserDivision(accessid);

        for(int i = 0; i < userDivisionInfos.length; i++) {
                String[] dateArr = userDivisionInfos[i].getAchievementDate().split("T");
                userDivisionInfos[i].setAchievementDate(dateArr[0]);
        }

        return userDivisionInfos;
    }

    public String replaceDivision(int division) {

        switch (division) {
            case 800:
                return "슈퍼 챔피언스";
            case 900:
                return "챔피언스";
            case 1000:
                return "슈퍼챌린지";
            case 1100:
                return "챌린지1";
            case 1200:
                return "챌린지2";
            case 1300:
                return "챌린지3";
            case 2000:
                return "월드클래스1";
            case 2100:
                return "월드클래스2";
            case 2200:
                return "월드클래스3";
            case 2300:
                return "프로1";
            case 2400:
                return "프로2";
            case 2500:
                return "프로3";
            case 2600:
                return "세미프로1";
            case 2700:
                return "세미프로2";
            case 2800:
                return "세미프로3";
            case 2900:
                return "유망주1";
            case 3000:
                return "유망주2";
            case 3100:
                return "유망주3";
        }

        return "";
    }
}
