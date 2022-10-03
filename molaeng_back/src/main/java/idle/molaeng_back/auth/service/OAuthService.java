package idle.molaeng_back.auth.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import idle.molaeng_back.auth.model.OAuthTokenDto;
import idle.molaeng_back.auth.model.OAuthUserRes;
import idle.molaeng_back.global.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


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

    @Value("${kakao-admin-key}")
    private String admin_key;

    @Autowired
    private final RedisUtil redisUtil;

    public OAuthService(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    // Kakao OAuth의 AccessToken, RefreshToken을 받아오는 메서드
    public OAuthTokenDto getAccessToken(String code) {
        OAuthTokenDto resultToken = null;

        String access_token = "";
        String refresh_token = "";
        try {
            URL url = new URL(TokenReqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //HttpURLConnection 설정 값 셋팅
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // buffer 스트림 객체 값 셋팅 후 요청, KAKAO 공식 문서에서 요구하는 파라미터 스트림을 통해 전송함.
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=").append(authorization_code);
            sb.append("&client_id=").append(client_id);  //앱 KEY VALUE
            sb.append("&redirect_uri=").append(redirect_uri); // 앱 CALLBACK 경로
            sb.append("&client_secret=").append(client_secret);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();


            log.info("200 코드가 나오면 토큰 생성 성공입니다. " + conn.getResponseCode());

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

            resultToken = OAuthTokenDto.builder()
                    .accessToken(access_token)
                    .refreshToken(refresh_token)
                    .build();

            log.info("accessToken 발급 성공 " + resultToken.getAccessToken());
            log.info("refreshToken 발급  성공입니다. " + resultToken.getRefreshToken());



            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultToken;
    }

    public OAuthUserRes getUserInfo(String access_token) {
        OAuthUserRes oAuthUserRes = null;

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

            log.info("1111111111111111");
            long uuid = element.getAsJsonObject().get("id").getAsLong();
            log.info("2222222222222222");
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            log.info("3333333333333333");
//            String profileImg = properties.getAsJsonObject().get("profile_image").getAsString();

            log.info("사진~~~~~~~~~~~~~~" );

            oAuthUserRes = OAuthUserRes.builder()
                    .nickname(nickname)
//                    .profileImg(profileImg)
                    .uuid(uuid)
                    .build();

            log.info("userInfo 정보 받아오기 성공", oAuthUserRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oAuthUserRes;
    }


    // logout 시 호출하는 메서드
    public void kakaoLogout(String uuid){
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "KakaoAK " + admin_key);
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("target_id_type=user_id");
            sb.append("&target_id="+uuid);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while((line = br.readLine()) != null) {
                result+=line;
            }
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 앱과 사용자 카카오계정의 연결을 끊는 메서드
    public void unlink(Long id) {
        String reqURL = "https://kapi.kakao.com/v1/user/unlink";
        log.info("회원아이디"+id);
        log.info("앱키"+admin_key);
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "KakaoAK " + admin_key);
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("target_id_type=user_id");
            sb.append("&target_id="+id);

            log.info("주소"+sb.toString());

            bw.write(sb.toString());
            bw.flush();


            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AccessToken 갱신하는 메서드
    public String updateAccessToken(String refresh_token) {
        String accessToken = "";
        String refreshToken = "";

        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=refresh_token");
            sb.append("&client_id="+client_secret);
            sb.append("&refresh_token="+refresh_token);

            bw.write(sb.toString());
            bw.flush();

            //결과코드 200이면 성공
            log.info("200 코드가 나오면 토큰 생성 성공입니다. " + conn.getResponseCode());

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while((line = br.readLine())!=null) {
                result += line;
            }
            System.out.println("response body="+result);

            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

            log.info("refresh_token여부"+ String.valueOf(jsonObject.has("refresh_token")));
            log.info("access_token여부"+String.valueOf(jsonObject.has("access_token")));
            accessToken = jsonObject.get("access_token").getAsString();
            String uuid = jsonObject.get("id").getAsString();

            if(jsonObject.has("refresh_token")){
                refreshToken = jsonObject.get("refresh_token").getAsString();
                //리프레시토큰 DB저장 -> 아직 여기에서 사용 X
                // refreshToken은 redis에 저장하기 {"userId": refresh_token}
//                redisUtil.setDataExpire(String.valueOf(uuid), refresh_token, JwtUtil.REFRESH_TOKEN_VALIDITY);


            }

            br.close();
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

}
