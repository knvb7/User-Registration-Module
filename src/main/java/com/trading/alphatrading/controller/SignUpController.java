package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/signUp/")
public class SignUpController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("newUser/")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody UserSignUpPayload userSignUpPayload) throws AlgoTradingException {
        ResponseEntity<ApiResponse<String>> response = new ResponseEntity<>(HttpStatus.OK);
        userRegistrationService.registerNewUser(userSignUpPayload);
    }
}
