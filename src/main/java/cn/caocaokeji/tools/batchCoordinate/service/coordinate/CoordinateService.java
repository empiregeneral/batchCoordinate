package cn.caocaokeji.tools.batchCoordinate.service.coordinate;

import cn.caocaokeji.tools.batchCoordinate.constants.Constants;
import cn.caocaokeji.tools.batchCoordinate.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service("coordinateTools")
public class CoordinateService {

    @Value("${spring.amap.key}")
    private String KEY;

    private  String coordinate(String cityCode, String address) {
        try {
            address = URLEncoder.encode(address, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = Constants.BASE_URL + "/geocode/geo?address=" + address + "&" + cityCode +"&output=json&key="+ KEY;
        String coordinateString = null;
        String content = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONArray pathArray = jsonObject.getJSONArray("geocodes");
        coordinateString = pathArray.getJSONObject(0).getString("location");
        return coordinateString;
    }

    public List<String> coordinateWithPolygon(String polygon, String keywords, String type) {
        try {
            polygon = URLEncoder.encode(polygon, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int current = 1;
        final int total = 60;

        List<List<String>> tmpList = new LinkedList<>();

        for (int page = current; page <= total; page++) {
            String url = String.format(Constants.BASE_URL + "/place/polygon?polygon=%s&keywords=%s&type=%s&extensions=base&output=json&offset=25&page=%d&key=%s", polygon, keywords, type, page, KEY);
            String content = HttpClientUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("pois");
            List<String> locations = new LinkedList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                locations.add(jsonArray.getJSONObject(i).getString("location"));
            }
            tmpList.add(locations);
        }
        return tmpList.stream().flatMap(x -> x.stream()).collect(Collectors.toList());

    }

    public String coordinateWithAddress(String address) {
        return coordinate("0571", address);
    }

}
