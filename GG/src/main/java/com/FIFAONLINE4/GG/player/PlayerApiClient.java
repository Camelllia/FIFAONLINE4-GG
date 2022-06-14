package com.FIFAONLINE4.GG.player;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

@RequiredArgsConstructor
@Service
public class PlayerApiClient {

    public JSONArray requestPlayerInfo() {

        try {
            
            URL playerInfoUrl = new URL("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json");

            HttpURLConnection conn = (HttpURLConnection) playerInfoUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            conn.disconnect();

            String unSerialJson = sb.toString();
            JSONArray jsonArray = new JSONArray(unSerialJson);

            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
