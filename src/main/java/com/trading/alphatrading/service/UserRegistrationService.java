package com.trading.alphatrading.service;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {
    void registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException;
}
