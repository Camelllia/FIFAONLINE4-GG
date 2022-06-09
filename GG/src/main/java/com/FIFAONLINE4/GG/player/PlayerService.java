package com.FIFAONLINE4.GG.player;

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

    public Map<Object, Object> getMapFromPlayerJson() {

        Map<Object, Object> mapList = new HashMap<>();

        try {

            JSONArray jsonArray = playerApiClient.requestPlayerInfo();

            for(int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> map = null;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
                for(Map.Entry<String, Object> m : map.entrySet()) {
                    Object playerId = null;
                    Object playerName = null;
                    if(m.getKey().equals("id")) {
                        playerId = m.getValue();
                    }
                    if(m.getKey().equals("name")) {
                        playerName = m.getValue();
                    }

                    System.out.println((String) playerName + " " + (Integer) playerId);
                    mapList.put(playerName, playerId);

                }
                System.out.println();
            }

            return mapList;

        } catch (Exception e) {
            e.printStackTrace();

            return mapList;
        }
    }
}
