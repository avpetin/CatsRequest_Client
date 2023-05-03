import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CloseableHttpResponse response =
                createHttpClient("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        getJsonFromResponse(response);
        jsonToList(getJsonFromResponse(response));
    }

    public static CloseableHttpResponse createHttpClient(String url){
       try ( CloseableHttpClient httpClient = HttpClientBuilder.create()
               .setDefaultRequestConfig(RequestConfig.custom()
                       .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                       .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                       .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                       .build())
               .build();
       )
       {
           HttpGet request = new HttpGet(url);
           return httpClient.execute(request);
       }
       catch (IOException e){}
       return null;
    }

    public static String getJsonFromResponse(CloseableHttpResponse response){
        String res = null;
   //     try {
/*            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                content.append(line);
            }
            return content.toString();*/

        HttpEntity entity = response.getEntity();
            if (entity != null && entity.isStreaming()) {
                try(  InputStream instream = entity.getContent();){
                    res = new String(instream.readAllBytes(), "UTF-8");
                    return res;
                }
                catch (IOException e) {}
            }
            return null;
    }

    public static List<CatsInfo> jsonToList(String dataFromServer){
        List<CatsInfo> catsInfos = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CatsInfo catsInfo = objectMapper.readValue(dataFromServer, CatsInfo.class);
        }
        catch (IOException e){}
        return catsInfos;
    }
}
