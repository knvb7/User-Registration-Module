package com.trading.alphatrading.utils.validation.impl;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.utils.validation.ValidationInterface;

public class ValidationInterfaceImpl implements ValidationInterface {
    @Override
    public void userValidation(UserSignUpPayload userSignUpPayload) {
        String firstName = userSignUpPayload.getFirstName();
        if(firstName == null || firstName.trim().isEmpty()){
            throw new AlgoTradingException();
        }
    }
}
