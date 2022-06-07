package com.travel.domain.dataInput;

import com.travel.domain.Festival;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.travel.festival.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class FestivalData {
    @Autowired
    private final FestivalRepository festivalRepository;

//    @EventListener(ApplicationReadyEvent.class) // spring boot 시작시 자동 실행 annotation(db에 데이터 들어갔으니 주석처리)
    public void datainput() throws IOException, ParseException {
        String serviceKey = URLDecoder.decode("mvyx0%2FL8ioEfk7nurSXw5%2BbfTa1CeVn1gfOLCivYnlcUPrjZyCnjdF6fSh7%2FhPAKOuhN7wR4PVN%2BVRpNMvYJkA%3D%3D", "UTF-8");
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1001", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); // 연결 확인

        Object obj = JSONValue.parse(new InputStreamReader(conn.getInputStream()));

        // JSON to JSONObject
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject jsonObjectResponse = (JSONObject) jsonObject.get("response");
        JSONObject jsonObjectBody = (JSONObject) jsonObjectResponse.get("body");
        JSONArray jsonObjectItems = (JSONArray) jsonObjectBody.get("items");

        for(int i = 0; i < jsonObjectItems.size(); i++) {
            JSONObject jsonObjectItem = (JSONObject) jsonObjectItems.get(i);
            System.out.println("jsonObjectItem = " + jsonObjectItem); // 확인용

            int idx = i;
            String name = jsonObjectItem.get("fstvlNm").toString();
            String location = jsonObjectItem.get("opar").toString();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObjectItem.get("fstvlStartDate").toString());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObjectItem.get("fstvlEndDate").toString());
            String content = jsonObjectItem.get("fstvlCo").toString();
            String tel = jsonObjectItem.get("phoneNumber").toString();
            String homepage = jsonObjectItem.get("homepageUrl").toString();
            String roadAddress = jsonObjectItem.get("rdnmadr").toString();
            String lotNumAddress = jsonObjectItem.get("lnmadr").toString();
            String latitude = jsonObjectItem.get("latitude").toString();
            String longitude = jsonObjectItem.get("longitude").toString();

            festivalRepository.save(Festival.builder()
                    .idx(idx).name(name).location(location)
                    .startDate(startDate).endDate(endDate).content(content).tel(tel)
                    .homepage(homepage).roadAddress(roadAddress).lotNumAddress(lotNumAddress)
                    .latitude(latitude).longitude(longitude).build());
        }

        conn.disconnect();
    }
}
