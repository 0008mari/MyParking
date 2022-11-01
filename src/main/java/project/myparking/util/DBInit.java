package project.myparking.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.myparking.domain.Parking;

public class DBInit {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static List<Parking> run() throws JSONException, IOException {

        List<Parking> parkingList = new ArrayList<>();
        String authkey = "7559687550736b79373173754a5357";

        int startRow = 1;
        int endRow = 1000;
        int renew = 1000;

        while (endRow <= 16000) {
            String apiUrl = "http://openapi.seoul.go.kr:8088/" + authkey + "/json/GetParkInfo/"+ startRow + "/" + endRow;
            JSONObject json = readJsonFromUrl(apiUrl);

            JSONObject GetParkInfo = json.getJSONObject("GetParkInfo");
//            int list_total_count = GetParkInfo.getInt("list_total_count");

            JSONArray parkJsonArr = GetParkInfo.getJSONArray("row");

            for (int i = 0; i < parkJsonArr.length(); i++) {
                JSONObject park = parkJsonArr.getJSONObject(i);

                String parkingName = park.getString("PARKING_NAME");
                String address = park.getString("ADDR");
                String parkingCode = park.getString("PARKING_CODE");
                String parkingTypeNM = park.getString("PARKING_TYPE_NM");
                String operationRuleNM = park.getString("OPERATION_RULE_NM");
                String tel = park.getString("TEL");
                double capacity = park.getDouble("CAPACITY");
                String payNM = park.getString("PAY_NM");
                String weekdayBeginTime = park.getString("WEEKDAY_BEGIN_TIME");
                String weekdayEndTime = park.getString("WEEKDAY_END_TIME");
                String syncTime = park.getString("SYNC_TIME");
                double rates = park.getDouble("RATES");
                double timeRates = park.getDouble("TIME_RATE");
                double addRates = park.getDouble("ADD_RATES");
                double addTimeRate = park.getDouble("ADD_TIME_RATE");

                Parking p = new Parking(parkingName,
                    address, parkingCode, parkingTypeNM, operationRuleNM,
                    tel, capacity, payNM, weekdayBeginTime, weekdayEndTime,
                    syncTime, rates, timeRates, addRates, addTimeRate);

                parkingList.add(p);
            }

            startRow += renew;
            endRow += renew;
        }
        System.out.println(parkingList.size());
        return parkingList;
    }

    public static void main(String[] args) throws IOException {
       List<Parking> p = run();
        System.out.println(p.size());
    }

}
