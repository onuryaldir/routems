package org.development.routems.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public ResponseEntity<String> predict(CreateRouteRequest createRouteRequest) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Create root node
        ObjectNode rootNode = objectMapper.createObjectNode();

        // Add selected_letters array node
        rootNode.putPOJO("selected_letters", createRouteRequest.getSelectedLetters());

        // Add components_filename field
        rootNode.put("destination_id", "TR34");

        rootNode.put("user_id", 1);

        rootNode.put("components_filename", "MODEL_TR34_1.pkl");


        // Convert ObjectNode to JSON string
        String jsonString = objectMapper.writeValueAsString(rootNode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestHttpEntity =  new HttpEntity<>(jsonString,headers);
        return restTemplate.postForEntity("http://127.0.0.1:5002/predict",requestHttpEntity,String.class);
    }

    public ResponseEntity<String> trainModel(TrainModelRequest trainModelRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = String.format("{ \"user_age\": %d, \"user_country_code\": %d,  \"destination_id\": %s, \"user_id\": %d }", trainModelRequest.getUserAge(), trainModelRequest.getUserCountryCode(), "TR34", 1);

        HttpEntity<String> requestHttpEntity =  new HttpEntity<>(json,headers);
        return restTemplate.postForEntity("http://127.0.0.1:5001/train",requestHttpEntity,String.class);
    }
}
