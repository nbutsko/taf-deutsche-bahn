package com.bahn.api.client;

import com.bahn.logger.UtilLogger;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class CustomClient {
    private CloseableHttpClient httpClient;
    private HttpResponse response;
    private String body;
    private int statusCode;

    public CustomClient() {
        httpClient = HttpClientBuilder.create().build();
    }

    public void sendGet(String url, List<NameValuePair> parameters) {
        try {
            HttpGet httpGet = new HttpGet(url);
            URI uri = new URIBuilder(httpGet.getURI()).addParameters(parameters).build();
            httpGet.setURI(uri);
            response = httpClient.execute(httpGet);
            UtilLogger.logger.info("GET " + uri);
        } catch (URISyntaxException | IOException e) {
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
            //UtilLogger.logger.info(body);
        } catch (IOException e) {
            UtilLogger.logger.warn(e.getMessage());
        }
        return body;
    }

    public void closeClient() {
        try {
            httpClient.close();
        } catch (IOException e) {
            UtilLogger.logger.warn(e.getMessage());
        }
    }
}
