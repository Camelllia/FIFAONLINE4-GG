package com.FIFAONLINE4.GG.player;

import com.FIFAONLINE4.GG.user.UserTradeResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PlayerService {

    private final PlayerApiClient playerApiClient;

    public Map<Integer, String> getMapFromPlayerJson() {

        Map<Integer, String> mapList = new HashMap<>();

        try {

            JSONArray jsonArray = playerApiClient.requestPlayerInfo();

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject playerInfoJson = (JSONObject) jsonArray.get(i);
                String playerName = playerInfoJson.getString("name");
                String playerId = playerInfoJson.getString("id");
                mapList.put(Integer.valueOf(playerId),playerName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mapList;
        }
    }

    public UserTradeResponseDto[] replacePlayerName(Map<Integer, String> playerInfoMap, UserTradeResponseDto[] userTradeResponseDtos) {

        for(int i = 0; i < userTradeResponseDtos.length; i++) {
           userTradeResponseDtos[i].setSpid(playerInfoMap.get(userTradeResponseDtos[i].getSpid()));
        }

        return userTradeResponseDtos;
    }
}
