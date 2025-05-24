package com.rahulsmgv.geoapp.service;

import com.rahulsmgv.geoapp.cache.AppCache;
import com.rahulsmgv.geoapp.placeholders.ConstantData;
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

    @Autowired
    AppCache configCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponsePOJO getWeather(String city) {

        WeatherResponsePOJO responsePOJO = redisService.getData(city, WeatherResponsePOJO.class);
        if(responsePOJO != null){
            return responsePOJO;
        }else{
            String finalAPI = configCache.appCache.get(AppCache.keys.weatherapi.toString()).replace(ConstantData.API_KEY, API_KEY).replace(ConstantData.CITY, city);
            /*ResponseEntity<WeatherResponsePOJO> response = restTemplate.getForEntity(URL, WeatherResponsePOJO.class); */
            ResponseEntity<WeatherResponsePOJO> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponsePOJO.class);

            if (response != null){
                redisService.setData(city, response.getBody(), 3000L);
            }
            return response.getBody();
        }
    }
}