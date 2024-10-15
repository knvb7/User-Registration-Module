package com.trading.alphatrading.utils.validation;


import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;

public interface ValidationInterface {
    void userSignUpValidation(UserSignUpPayload userSignUpPayload) throws AlgoTradingException;

    void validatePhoneNumber(String phoneNumber) throws AlgoTradingException;
}
