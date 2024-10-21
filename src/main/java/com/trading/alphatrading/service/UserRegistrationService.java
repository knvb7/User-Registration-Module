package com.trading.alphatrading.service;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRegistrationService {
    Users registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException;

    void sendOtpTwilio(String phoneNumber) throws AlgoTradingException;

    Boolean verifyOtpTwilio(String phoneNumber, String otpEntered);

    void sendOtpEmail(String fromEmailId, List<String> toEmailIds, String subject, String body) throws AlgoTradingException;

    Users getByPhoneNumber(String phoneNumber) throws AlgoTradingException;

    Users getByEmailId(String emailId) throws AlgoTradingException;
}
