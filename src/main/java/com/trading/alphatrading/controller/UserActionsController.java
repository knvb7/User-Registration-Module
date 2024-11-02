package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.request.UpdatePasswordPayload;
import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.api.response.ResponseHandler;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.service.UserDetailsUpdateService;
import com.trading.alphatrading.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userActions/")
public class UserActionsController {
    private final Logger logger = LogManager.getLogger(UserActionsController.class);
    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("enableUser/")
    public ResponseEntity<ApiResponse> enableUser(@RequestParam Integer userId, @RequestParam Integer brokerId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        ResponseEntity<String> response;
        try {
            userRegistrationService.enableDisableUser(userId, brokerId, true);
        } catch (AlgoTradingException e) {
            String errorMessage = "Unable to disable the strategy for userId : " + userId + " and brokerId : " + brokerId
                    + " . The error is : " + e.getMessage();
            logger.error(errorMessage);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setResponseMessage(errorMessage);
            return ResponseHandler.generateResponse(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Strategy has been successfully disabled for userId : " + userId + " and brokerId : " + brokerId );
        return ResponseHandler.generateResponse(apiResponse);
    }

    @GetMapping("disableUser/")
    public ResponseEntity<ApiResponse> disableUser(@RequestParam Integer userId, @RequestParam Integer brokerId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        ResponseEntity<String> response;
        try {
            userRegistrationService.enableDisableUser(userId, brokerId, false);
        } catch (AlgoTradingException e) {
            String errorMessage = "Unable to disable the strategy for userId : " + userId + " and brokerId : " + brokerId
                    + " . The error is : " + e.getMessage();
            logger.error(errorMessage);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setResponseMessage(errorMessage);
            return ResponseHandler.generateResponse(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Strategy has been successfully disabled for userId : " + userId + " and brokerId : " + brokerId );
        return ResponseHandler.generateResponse(apiResponse);
    }
}
