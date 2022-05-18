package project.myparking.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.myparking.domain.Parking;
import project.myparking.repository.ParkingRepository;
import project.myparking.web.dto.ParkingResponseComplex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller       // MVC Workflow : return adequate view page (used thymeleaf for a while)
//@RestController     // REST API Workflow
@RequestMapping(value="/api")
public class HomeController {

    @Autowired
    private ParkingRepository parkingRepository;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("loadDB")
    @ResponseBody
    public String load_save(){
        String result = "";
        String key = "7559687550736b79373173754a5357";
        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + key +
                    "/json/GetParkInfo/1/100/");
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
                        (String)tmp.get("SYNC_TIME"),
                        (double)tmp.get("RATES"), (double)tmp.get("TIME_RATE"), (double)tmp.get("ADD_RATES"),
                        (double)tmp.get("ADD_TIME_RATE"),
                        (double)tmp.get("LAT"), (double)tmp.get("LNG"));

                parkingRepository.save(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
            return "DB making fail";
        }
        return "Database myparking 200 success";
    }
}