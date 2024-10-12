package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.model.Brokers;
import com.trading.alphatrading.repository.BrokersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registerNewBroker/")
public class RegisterBrokerController {
    @Autowired
    private BrokersRepository brokersRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> registerNewBroker(@RequestParam String brokerName) {
        ResponseEntity<ApiResponse<String>> response = new ResponseEntity<>(HttpStatus.OK);
        if (brokerName == null || brokerName.trim().isEmpty()) {
            return response.
        }

        Brokers broker = new Brokers();
        broker.setName(brokerName);
        brokersRepository.save(broker);
        return ResponseEntity.ok("Broker added successfully");
    }
}
