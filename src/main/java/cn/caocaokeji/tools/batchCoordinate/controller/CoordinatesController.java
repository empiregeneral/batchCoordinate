package cn.caocaokeji.tools.batchCoordinate.controller;

import cn.caocaokeji.tools.batchCoordinate.service.coordinate.CoordinateService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/coordinate")
public class CoordinatesController {
    @Autowired
    CoordinateService coordinateService;

    @RequestMapping(value = "/batch", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String batchCoordinates(@RequestParam(value = "polygon") String polygon,
                                   @RequestParam(value = "keywords") String keywords,
                                   @RequestParam(value = "type") String type) {
        List<String> content = coordinateService.coordinateWithPolygon(polygon, keywords, type);
        return JSONObject.toJSON(content).toString();
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String coordinate(@RequestParam(value = "address") String address) {
        return coordinateService.coordinateWithAddress(address);
    }
}
