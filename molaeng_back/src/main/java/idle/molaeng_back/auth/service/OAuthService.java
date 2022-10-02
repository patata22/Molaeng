package idle.molaeng_back.auth.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import idle.molaeng_back.auth.model.ApiKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OAuthService {

    @Value("${kakao-token-uri}")
    private String TokenReqURL;

    @Value("${kakao-authorization-grant-type}")
    private String authorization_code;

    @Value("${kakao-redirect-uri}")
    private String redirect_uri;

    @Value("${kakao-client-id}")
    private String client_id;
    @Value("${kakao-client-secret}")
    private String client_secret;
    @Value("${kakao-user-info-uri}")
    private String userInfoReqURL;

    // Kakao OAuth의 AccessToken, RefreshToken을 받아오는 메서드
    public String getAccessToken(String code) {
        String access_token = "";
        String refresh_token = "";
        try {
            URL url = new URL(TokenReqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //HttpURLConnection 설정 값 셋팅
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // buffer 스트림 객체 값 셋팅 후 요청
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=").append(authorization_code);
            sb.append("&client_id=").append(client_id);  //앱 KEY VALUE
            sb.append("&redirect_uri=").append(redirect_uri); // 앱 CALLBACK 경로
            sb.append("&client_secret=").append(client_secret);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //  RETURN 값 result 변수에 저장
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);


            // 토큰 값 저장 및 리턴
            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_token;
    }

    public Map<String,Object> getUserInfo(String access_token) {
        Map<String,Object> resultMap =new HashMap<>();

        try {
            URL url = new URL(userInfoReqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            System.out.println("response:" + result);


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            log.warn("element:: " + element);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            log.warn("id:: "+element.getAsJsonObject().get("id").getAsString());
            String id = element.getAsJsonObject().get("id").getAsString();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            log.warn("email:: " + email);
            resultMap.put("nickname", nickname);
            resultMap.put("id", id);
            resultMap.put("email", email);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
