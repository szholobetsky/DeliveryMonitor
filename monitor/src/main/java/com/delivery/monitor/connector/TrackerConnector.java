package com.delivery.monitor.connector;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.json.JSONObject;
import org.json.JSONArray;


import org.springframework.stereotype.Component;

import com.delivery.monitor.model.ActiveDeliveryRange;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TrackerConnector {
	
	public ActiveDeliveryRange getDeliveriesRange(String tracker) throws IOException {
        ActiveDeliveryRange range = new ActiveDeliveryRange();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet request = new HttpGet(tracker+"/activedelivery");
            // add request headers
            // request.addHeader("custom-key", "mykey");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity); // {minId: 10; maxId:100}
                    JSONObject jsonObject = new JSONObject(result);

                    int minId = jsonObject.getInt("minId");
                    int maxId = jsonObject.getInt("maxId");
                    

                    range.setMinId(minId);
                    range.setMaxId(maxId);
                    return range;
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return range;
    }
    
    public String sendDeliveriesToTracker(String trackerUrl, List deliveries) throws IOException {

        String result = "";
        HttpPost post = new HttpPost(trackerUrl);

        JSONArray jsonArray = new JSONArray(deliveries);
        

        // send a JSON data
        post.setEntity(new StringEntity(jsonArray.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }
    
    public void PostWithBasicAuth(String[] args) throws IOException {

        HttpGet request = new HttpGet("http://localhost:8080/books");

        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password")
        );

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // 401 if wrong user/password
            System.out.println(response.getStatusLine().getStatusCode());   

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }
    
}
