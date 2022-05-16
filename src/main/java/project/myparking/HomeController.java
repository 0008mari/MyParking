package project.myparking;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/*
/8080/api 에서 조회할 위치 입력(해당위치의 데이터 db저장됨) -> 8080/api/parki
 */

@Controller
@RequestMapping(value="/api")
public class HomeController {

    @Autowired
    private ParkingRepository infoRepository;

    @GetMapping
    public String index(){
        return "index";
    }

    @PostMapping
    public String load_save(){
        String result = "";
        String key = "7559687550736b79373173754a5357";
        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + key +
                    "/json/GetParkInfo/1/1000/");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject GetParkInfo = (JSONObject)jsonObject.get("GetParkInfo");
            Long totalCount=(Long)GetParkInfo.get("list_total_count");

            JSONObject subResult = (JSONObject)GetParkInfo.get("RESULT");
            JSONArray infoArr = (JSONArray) GetParkInfo.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                Parking infoObj=new Parking(
                        i+(long)1, (String)tmp.get("PARKING_NAME"),(String)tmp.get("ADDR"),
                        (String)tmp.get("PARKING_CODE"), (String)tmp.get("PARKING_TYPE_NM"),
                        (String)tmp.get("OPERATION_RULE_NM"), (String)tmp.get("TEL"),
                        (double)tmp.get("CAPACITY"), (String)tmp.get("PAY_NM"),
                        (String)tmp.get("WEEKDAY_BEGIN_TIME"), (String)tmp.get("WEEKDAY_END_TIME"),
                        (String)tmp.get("SYNC_TIME"), (String)tmp.get("SATURDAY_PAY_NM"),
                        (double)tmp.get("RATES"), (double)tmp.get("TIME_RATE"), (double)tmp.get("ADD_RATES"),
                        (double)tmp.get("ADD_TIME_RATE"), (double)tmp.get("DAY_MAXIMUM"),
                        (double)tmp.get("LAT"), (double)tmp.get("LNG"), new::List<Review>);

                infoRepository.save(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/findparking";
    }

//    @PostMapping
//    public String load_save(@RequestParam("addr") String addr, Model model){
//        String result = "";
//        String key = "7559687550736b79373173754a5357";
//        try {
//            String requestAddr=addr;
//            URL url = new URL("http://openapi.seoul.go.kr:8088/" + key +
//                    "/json/GetParkInfo/1/10/" + requestAddr);
//            BufferedReader bf;
//            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            result = bf.readLine();
//
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
//            JSONObject GetParkInfo = (JSONObject)jsonObject.get("GetParkInfo");
//            Long totalCount=(Long)GetParkInfo.get("list_total_count");
//
//            JSONObject subResult = (JSONObject)GetParkInfo.get("RESULT");
//            JSONArray infoArr = (JSONArray) GetParkInfo.get("row");
//
//            for(int i=0;i<infoArr.size();i++){
//                JSONObject tmp = (JSONObject)infoArr.get(i);
//                ParkingInfo infoObj=new ParkingInfo(
//                        i+(long)1, (String)tmp.get("PARKING_NAME"),(String)tmp.get("ADDR"),
//                        (String)tmp.get("PARKING_CODE"), (String)tmp.get("PARKING_TYPE"), (String)tmp.get("PARKING_TYPE_NM"),
//                        (String)tmp.get("OPERATION_RULE"), (String)tmp.get("OPERATION_RULE_NM"), (String)tmp.get("TEL"),
//                        (double)tmp.get("CAPACITY"), (String)tmp.get("PAY_YN"), (String)tmp.get("PAY_NM"),
//                        (String)tmp.get("NIGHT_FREE_OPEN"), (String)tmp.get("NIGHT_FREE_OPEN_NM"),
//                        (String)tmp.get("WEEKDAY_BEGIN_TIME"), (String)tmp.get("WEEKDAY_END_TIME"),
//                        (String)tmp.get("WEEKEND_BEGIN_TIME"), (String)tmp.get("WEEKEND_END_TIME"),
//                        (String)tmp.get("HOLIDAY_BEGIN_TIME"), (String)tmp.get("HOLIDAY_END_TIME"),
//                        (String)tmp.get("SYNC_TIME"), (String)tmp.get("SATURDAY_PAY_YN"), (String)tmp.get("SATURDAY_PAY_NM"),
//                        (String)tmp.get("HOLIDAY_PAY_YN"), (String)tmp.get("HOLIDAY_PAY_NM"),
//                        (String)tmp.get("FULLTIME_MONTHLY"),
//                        (String)tmp.get("GRP_PARKNM"), (double)tmp.get("RATES"),
//                        (double)tmp.get("TIME_RATE"), (double)tmp.get("ADD_RATES"),
//                        (double)tmp.get("ADD_TIME_RATE"), (double)tmp.get("BUS_RATES"),
//                        (double)tmp.get("BUS_TIME_RATE"), (double)tmp.get("BUS_ADD_TIME_RATE"),
//                        (double)tmp.get("BUS_ADD_RATES"), (double)tmp.get("DAY_MAXIMUM"),
//                        (double)tmp.get("LAT"), (double)tmp.get("LNG"));
//
//                infoRepository.save(infoObj);
//            }
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/findparking";
//    }

}