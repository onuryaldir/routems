package org.development.routems.service;

import lombok.RequiredArgsConstructor;
import org.development.routems.configuration.AIServiceConfig;
import org.development.routems.model.CreateRouteRequest;
import org.development.routems.model.TrainModelRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AiRequestService {

    private final AIServiceConfig aiServiceConfig;

    public ResponseEntity<String> makeRequest(CreateRouteRequest createRouteRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateRouteRequest> requestHttpEntity =  new HttpEntity<>(createRouteRequest,headers);
        return restTemplate.postForEntity(aiServiceConfig.getIp()+":"+aiServiceConfig.getPort() +"/predict",requestHttpEntity,String.class);
    }

    public ResponseEntity<String> trainModel(TrainModelRequest trainModelRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = String.format("{ \"user_age\": %d, \"user_country_code\": %d }", trainModelRequest.getUserAge(), trainModelRequest.getUserCountryCode());

        HttpEntity<String> requestHttpEntity =  new HttpEntity<>(json,headers);
        return restTemplate.postForEntity("http://127.0.0.1:5001/train",requestHttpEntity,String.class);
    }
}
