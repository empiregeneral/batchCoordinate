package cn.caocaokeji.tools.batchCoordinate.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    public static String doGet(String url) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;

        String content = "";

        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity());
            }
        } finally {
            if ( response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return content;
        }

    }

}
