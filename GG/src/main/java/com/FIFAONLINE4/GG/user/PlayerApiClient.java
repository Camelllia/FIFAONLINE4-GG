package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

@RequiredArgsConstructor
@Service
public class PlayerApiClient {

    private final String API_KEY = "";

    public JSONObject requestPlayerInfo() {

        JSONObject json = null;

        try {
            
            URL playerInfoUrl = new URL("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json");

            HttpURLConnection conn = (HttpURLConnection) playerInfoUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", API_KEY);
            conn.setRequestProperty("Content-type", "application/json");

            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            json = new JSONObject(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }
    
}
