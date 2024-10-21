package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.api.response.ApiResponse;
import com.trading.alphatrading.api.response.ResponseHandler;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.service.UserRegistrationService;
import com.trading.alphatrading.utils.Constants;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/signUp/")
public class SignUpController {
    private final Logger logger = LogManager.getLogger(SignUpController.class);
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("newUser/")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody UserSignUpPayload userSignUpPayload) {
        ApiResponse<String> response = new ApiResponse<>();
        Users user;
        try {
            user = userRegistrationService.registerNewUser(userSignUpPayload);
        } catch (AlgoTradingException e) {
            String errorMessage = "Validation failed for user signUp. The error is : " + e.getMessage();
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

    @GetMapping("phoneNumber/sendOtp")
    public ResponseEntity<ApiResponse> sendOtpPhoneNumber(@RequestParam String phoneNumber) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        ResponseEntity<String> response;
        try {
            userRegistrationService.sendOtpTwilio(phoneNumber);
        } catch (AlgoTradingException e) {
            String errorMessage = "Unable to send Otp to phoneNumber : " + phoneNumber + " . The error is : " + e.getMessage();
            logger.error(errorMessage);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setResponseMessage(errorMessage);
            return ResponseHandler.generateResponse(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Otp sent successfully to the phoneNumber : " + phoneNumber + ".");
//        apiResponse.setPayload(response.getBody());
        return ResponseHandler.generateResponse(apiResponse);
    }

    @GetMapping("phoneNumber/verifyOtp")
    public ResponseEntity<ApiResponse> verifyOtpPhoneNumber(@RequestParam String phoneNumber, @RequestParam String otpEntered) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Boolean isValid = userRegistrationService.verifyOtpTwilio(phoneNumber, otpEntered);
        if (isValid) {
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setResponseMessage("PhoneNumber : " + phoneNumber + " has been verified successfully.");
        } else {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setResponseMessage("Invalid Otp entered. Please reenter the otp for the phoneNumber : " + phoneNumber);
        }
        return ResponseHandler.generateResponse(apiResponse);
    }

    @PostMapping("sendOtp/email")
    public ResponseEntity<ApiResponse> sendOtpEmail(@RequestParam List<String> toEmailIds) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        ResponseEntity<String> response;
        try {
            Random random = new Random();
            String otp = String.valueOf(random.nextInt(999999));
            String subject = "Your OTP is : " + otp;
            userRegistrationService.sendOtpEmail("mihir.dalal.0911@gmail.com", toEmailIds, subject, subject);
        } catch (AlgoTradingException e) {
            String errorMessage = "Unable to send Otp to emailIds : " + toEmailIds + " . The error is : " + e.getMessage();
            logger.error(errorMessage);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setResponseMessage(errorMessage);
            return ResponseHandler.generateResponse(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setResponseMessage("Otp sent successfully to the toEmailIds : " + toEmailIds + ".");
        return ResponseHandler.generateResponse(apiResponse);
    }
}
