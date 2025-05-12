package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.util.Scanner;

@Service
public class MarvelService {
    private static final String MARVEL_API_URL = "https://gateway.marvel.com/v1/public/characters";
    private static final String API_KEY = "ec27418913128e765db8d752465d3ab3";
    private static final String TS = "1746533381";
    private static final String HASH = "a5fc45169923e15c608ae97435386e6c";
    private static final String DEFAULT_IMAGE = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRGdLkYMo69JzWGPLPzvnBDwC_qG-M2xaLtw&s";

    public String getHeroImage(String heroName) {
        try {
            String encodedName = URLEncoder.encode(heroName, StandardCharsets.UTF_8.toString());
            String apiUrl = MARVEL_API_URL +
                    "?apikey=" + API_KEY +
                    "&ts=" + TS +
                    "&hash=" + HASH +
                    "&name=" + encodedName;

            System.out.println("Calling Marvel API with URL: " + apiUrl);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String response = scanner.hasNext() ? scanner.next() : "";
                scanner.close();
                
                System.out.println("Response received: " + response.substring(0, Math.min(200, response.length())) + "...");
                
                JSONObject json = new JSONObject(response);
                JSONObject data = json.getJSONObject("data");
                JSONArray results = data.getJSONArray("results");
                
                System.out.println("Number of results: " + results.length());

                if (results.length() > 0) {
                    JSONObject heroJson = results.getJSONObject(0);
                    JSONObject thumbnail = heroJson.getJSONObject("thumbnail");
                    String path = thumbnail.getString("path");
                    String extension = thumbnail.getString("extension");
                    String imageUrl = path + "." + extension;
                    System.out.println("Found image URL: " + imageUrl);
                    return imageUrl;
                } else {
                    System.out.println("No results found for hero: " + heroName);
                }
            } else {
                System.out.println("Error response code: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving hero image: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Returning default image for hero: " + heroName);
        return DEFAULT_IMAGE;
    }
} 