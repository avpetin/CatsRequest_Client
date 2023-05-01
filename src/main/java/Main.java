import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
       createHttpClient();
    }

    public static void createHttpClient(){
       try ( CloseableHttpClient httpClient = HttpClientBuilder.create()
               .setDefaultRequestConfig(RequestConfig.custom()
                       .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                       .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                       .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                       .build())
               .build();
       )
       {
           HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
           CloseableHttpResponse response = httpClient.execute(request);
       }
       catch (IOException e){}
    }
}
