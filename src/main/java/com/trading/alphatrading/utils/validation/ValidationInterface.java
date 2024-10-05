package com.trading.alphatrading.utils.validation;


import com.trading.alphatrading.api.request.UserSignUpPayload;

public interface ValidationInterface {
    void userValidation(UserSignUpPayload userSignUpPayload);
}
