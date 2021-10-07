package cn.caocaokeji.tools.batchCoordinate.mapper;

import junitparams.mappers.DataMapper;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class PolygonMapper implements DataMapper {
    private final int lineToSkip;

    public PolygonMapper() {
        this(1);
    }

    public PolygonMapper(int lineToSkip) {
        this.lineToSkip = lineToSkip;
    }


    @Override
    public Object[] map(Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        String line;
        List<Object> params = new LinkedList<>();
        int lineNo = 0;
        try {
            while ((line = br.readLine()) != null) {
                if (++lineNo > lineToSkip) {
                    String[] content = line.split("#");
                    params.add(new Object[]{content[0], content[1], content[2]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params.toArray();
    }
}
