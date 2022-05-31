package com.bahn.api.client;

import com.bahn.utils.UtilLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CustomClient {
    private CloseableHttpClient httpClient;
    private HttpResponse response;
    private String body;

    private int statusCode;

    public CustomClient() {
        httpClient = HttpClientBuilder.create().build();
    }

    public void sendGet(String url) {
        try {
            response = httpClient.execute(new HttpGet(url));
            UtilLogger.logger.info("GET " + url);
        } catch (IOException e) {
            UtilLogger.logger.warn(e.getMessage());
        }
    }

    public int getStatusCode() {
        statusCode = response.getStatusLine().getStatusCode();
        UtilLogger.logger.info("Status code: " + statusCode);
        return statusCode;
    }

    public String getBody() {
        try {
            body = EntityUtils.toString(response.getEntity());
            UtilLogger.logger.info(body);
        } catch (IOException e) {
            UtilLogger.logger.warn(e.getMessage());
        }
        return body;
    }
}
