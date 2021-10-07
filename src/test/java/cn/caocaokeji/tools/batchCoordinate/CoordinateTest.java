package cn.caocaokeji.tools.batchCoordinate;

import cn.caocaokeji.tools.batchCoordinate.service.coordinate.CoordinateService;
import cn.caocaokeji.tools.batchCoordinate.mapper.PolygonMapper;
import cn.caocaokeji.tools.batchCoordinate.mapper.StringMapper;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
public class CoordinateTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private CoordinateService tools;

    @Test
    @FileParameters(mapper = StringMapper.class, value = "src/main/resources/address.csv")
    public void test_coordinateCreateWithAddress(String address) {
        System.out.println(tools.coordinateWithAddress(address));

        //System.out.println(CoordinateTools.locationCoordinateWithAddress(address));
    }

    @Test
    @FileParameters(mapper = PolygonMapper.class, value = "src/main/resources/polygon.csv")
    public void test_coordinateCreateWithPolygon(String polygon, String keywords, String type) {
        List<String> content =tools.coordinateWithPolygon(polygon, keywords, type);
        try {
            FileWriter writer = new FileWriter("src/main/resources/out.csv");

            Iterator<String> iterator = content.iterator();
            while(iterator.hasNext()) {
                writer.append(iterator.next()+System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content);

    }

}
