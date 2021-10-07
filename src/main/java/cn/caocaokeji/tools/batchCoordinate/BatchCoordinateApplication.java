package cn.caocaokeji.tools.batchCoordinate;

import cn.caocaokeji.tools.batchCoordinate.service.coordinate.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BatchCoordinateApplication {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(BatchCoordinateApplication.class, args);

    }

    public void run(String... args) {
        CoordinateService tools = (CoordinateService) context.getBean("coordinateTools");
        tools.coordinateWithAddress("拱墅区天水桥后营弄51号");
    }


}
