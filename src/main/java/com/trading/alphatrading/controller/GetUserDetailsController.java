package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.api.response.ResponseHandler;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.service.UserRegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/getUserDetails/")
public class GetUserDetailsController {
    private final Logger logger = LogManager.getLogger(GetUserDetailsController.class);

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("usingPhoneNumber")
    public ResponseEntity<ApiResponse> usingPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber) {
        ApiResponse<String> response = new ApiResponse<>();
        Users user;
        try {
            user = userRegistrationService.getByPhoneNumber(phoneNumber);
        } catch (AlgoTradingException e) {
            response.setStatus(e.getStatus());
            response.setResponseMessage(e.getResponseMessage());
            return ResponseHandler.generateResponse(response);
        }
        response.setStatus(HttpStatus.OK);
        response.setResponseMessage("User successfully fetched for the phoneNumber : "+phoneNumber);
        response.setPayload(String.valueOf(user));
        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("usingEmailId")
    public ResponseEntity<ApiResponse> usingEmailId(@RequestParam(value = "emailId") String emailId) {
        ApiResponse<String> response = new ApiResponse<>();
        Users user;
        try {
            user = userRegistrationService.getByEmailId(emailId);
        } catch (AlgoTradingException e) {
            response.setStatus(e.getStatus());
            response.setResponseMessage(e.getResponseMessage());
            return ResponseHandler.generateResponse(response);
        }
        response.setStatus(HttpStatus.OK);
        response.setResponseMessage("User successfully fetched for the emailId : "+emailId);
        response.setPayload(String.valueOf(user));
        return ResponseHandler.generateResponse(response);
    }
}
