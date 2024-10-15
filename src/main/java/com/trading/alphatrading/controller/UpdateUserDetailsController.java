package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.api.response.ResponseHandler;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/updateDetails/")
public class UpdateUserDetailsController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("updateName/")
    public ResponseEntity<ApiResponse> updateName(@Valid @RequestBody UpdateNamePayload updateNamePayload) {
        ApiResponse<String> response = new ApiResponse<>();
        Users user;
        try {
            user = userRegistrationService.registerNewUser(userSignUpPayload);
        } catch (AlgoTradingException e){
            String errorMessage = "Validation failed for user signUp. The error is : "+e.getMessage();
            logger.error(errorMessage);
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(errorMessage);
            return ResponseHandler.generateResponse(response);
        }
        response.setStatus(HttpStatus.OK);
        response.setResponseMessage("User successfully registered in the ");
        response.setPayload(String.valueOf(user));
        return ResponseHandler.generateResponse(response);
    }
}
