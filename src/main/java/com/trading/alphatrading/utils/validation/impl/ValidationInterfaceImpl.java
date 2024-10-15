package com.trading.alphatrading.utils.validation.impl;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.utils.validation.ValidationInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

public class ValidationInterfaceImpl implements ValidationInterface {
    private final Logger logger = LogManager.getLogger(ValidationInterfaceImpl.class);
    @Override
    public void userSignUpValidation(UserSignUpPayload userSignUpPayload) throws AlgoTradingException {
        String firstName = userSignUpPayload.getFirstName();
        if(firstName == null || firstName.trim().isEmpty()){
            String errorMessage = "FirstName is either null or empty.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
    }

    @Override
    public void validatePhoneNumber(String phoneNumber) throws AlgoTradingException {
        String phoneNumberPattern = "^\\+?[1-9]\\d{1,14}$";

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            String errorMessage = "Phone number is either null or empty.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }

        if (!phoneNumber.matches(phoneNumberPattern)) {
            String errorMessage = "Phone number format is invalid. Expected format: +<CountryCode><PhoneNumber>";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
        // Additional validations like length, specific country code, etc., can be added here if needed.
    }
}
