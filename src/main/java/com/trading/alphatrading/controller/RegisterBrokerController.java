package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.api.response.ResponseHandler;
import com.trading.alphatrading.model.Brokers;
import com.trading.alphatrading.service.BrokerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RegisterBrokerController {

    @Autowired
    private BrokerOperationService brokerOperationService;

    @PostMapping("addBroker")
    public ResponseEntity<ApiResponse> registerNewBroker(@RequestParam String brokerName) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (brokerName == null || brokerName.trim().isEmpty()) {
            apiResponse.setResponseMessage("BrokerName is invalid. Please enter a valid Broker Name.");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseHandler.generateResponse(apiResponse);
        }

        brokerOperationService.addBroker(brokerName);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Broker with name : " + brokerName + " added successfully.");
        return ResponseHandler.generateResponse(apiResponse);
    }

    @DeleteMapping("deleteBroker")
    public ResponseEntity<ApiResponse> deleteBroker(@RequestParam String brokerName) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (brokerName == null || brokerName.trim().isEmpty()) {
            apiResponse.setResponseMessage("BrokerName is invalid. Please enter a valid Broker Name.");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseHandler.generateResponse(apiResponse);
        }

        brokerOperationService.deleteBroker(brokerName);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Broker with name : " + brokerName + " deleted successfully if it existed.");
        return ResponseHandler.generateResponse(apiResponse);
    }

    @GetMapping("getAllBrokers")
    public ResponseEntity<ApiResponse> getAllBrokers() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        List<Brokers> brokersList = brokerOperationService.findAllBrokers();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Fetched all the brokers successfully.");
        apiResponse.setPayload(brokersList.toString()) ;
        return ResponseHandler.generateResponse(apiResponse);
    }
}
