package com.trading.alphatrading.service;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {
    Users registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException;

    ResponseEntity<String> sendOtp(String phoneNumber, String channel) throws AlgoTradingException;

    Users getByPhoneNumber(String phoneNumber) throws AlgoTradingException;

    Users getByEmailId(String emailId) throws AlgoTradingException;
}
