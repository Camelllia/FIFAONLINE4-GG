package com.FIFAONLINE4.GG.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PlayerService {

    private final PlayerApiClient playerApiClient;

    public Map<String, Object> getMapFromPlayerJson() {

        Map<String, Object> mapList = new HashMap<>();

        try {

            JSONArray jsonArray = playerApiClient.requestPlayerInfo();

            System.out.println(jsonArray.length());

            for(int i = 0; i < jsonArray.length(); i++) {
                Map<String, Object> map = null;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map = new ObjectMapper().readValue(jsonObject.toString(), Map.class);
                for(Map.Entry<String, Object> m : map.entrySet()) {
                    mapList.put(m.getKey(), m.getValue());
                }
            }

            return mapList;

        } catch (Exception e) {
            e.printStackTrace();

            return mapList;
        }
    }
}
