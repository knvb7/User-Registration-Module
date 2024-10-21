package com.trading.alphatrading.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.trading.alphatrading.exception.AlgoTradingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ApiCaller {
    private static final Logger logger = LogManager.getLogger(ApiCaller.class);

    public static ResponseEntity<String> callApi(String url, JsonNode value, HttpHeaders headers, HttpHeaders cookies,
                                                 HttpMethod httpMethod) throws AlgoTradingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            httpHeaders.putAll(headers);
        }
        if (cookies != null) {
            StringBuilder cookieHeaders = new StringBuilder();
            for (String cookieName : cookies.keySet()) {
                List<String> cookieValues = cookies.get(cookieName);
                if (cookieValues != null) {
                    for (String cookieValue : cookieValues) {
                        cookieHeaders.append(cookieName).append("=").append(cookieValue).append("; ");
                    }
                }
            }

            if (!cookieHeaders.isEmpty()) {
                httpHeaders.add("Cookie", cookieHeaders.toString());
            }
        }
        HttpEntity<JsonNode> requestEntity = new HttpEntity<>(value, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        new ResponseEntity<>(HttpStatus.OK);
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        try {
            response = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
            if (!response.getStatusCode().is2xxSuccessful() && !response.getStatusCode().is3xxRedirection()) {
                String errorMessage = "The status code while sending the request to the url : " + url + " is not 2xx or 3xx.";
                logger.error("{}. Response for the API call is : {}", errorMessage, response.getBody());
                throw new AlgoTradingException(response.getBody(), HttpStatus.BAD_REQUEST, logger);

            }
        } catch (Exception e) {
            String errorMessage = "The statusCode while sending the request to the url : " + url + " is not 2xx OK or 3xx. The api call threw an exception.";
            logger.error("The stackTrace is : {} , {}. Response for the API call is : {}", e.getStackTrace(), errorMessage, response.getBody(), e);
            throw new AlgoTradingException(response.getBody(), HttpStatus.BAD_REQUEST, logger);
        }
        return response;
    }
}
