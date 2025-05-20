package com.rahulsmgv.geoapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulsmgv.geoapp.pojo.SystemInfoRequest;
import com.rahulsmgv.geoapp.pojo.SystemInfoResponsePOJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SystemInfoService {
    @Autowired
    private RestTemplate restTemplatePost;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${system-info.api.url}")
    private String URL;

    public SystemInfoResponsePOJO getSystemInfo(SystemInfoRequest systemInfoRequest) throws JsonProcessingException {

        HttpEntity<SystemInfoRequest> httpEntity = new HttpEntity<>(systemInfoRequest);
        ResponseEntity<SystemInfoResponsePOJO> response = restTemplatePost.exchange(URL, HttpMethod.POST, httpEntity, SystemInfoResponsePOJO.class);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        log.info("Response received from System information, {}", json);
        return response.getBody();
    }
}
