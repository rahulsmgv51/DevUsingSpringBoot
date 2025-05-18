package com.rahulsmgv.geoapp.service;

import com.rahulsmgv.geoapp.pojo.SystemInfoRequest;
import com.rahulsmgv.geoapp.pojo.SystemInfoResponsePOJO;
import com.rahulsmgv.geoapp.pojo.WeatherResponsePOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String URL;
    @Value("${weather.api.key}")
    private String API_KEY;

    public WeatherResponsePOJO getWeather(String city) {
        String finalAPI = URL.replace("API_KEY", "").replace("CITY", city);
        //ResponseEntity<WeatherResponsePOJO> response = restTemplate.getForEntity(URL, WeatherResponsePOJO.class);
        ResponseEntity<WeatherResponsePOJO> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponsePOJO.class);
        return response.getBody();
    }
}
