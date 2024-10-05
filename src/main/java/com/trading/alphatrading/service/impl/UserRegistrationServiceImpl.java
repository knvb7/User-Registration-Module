package com.trading.alphatrading.service.impl;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.User;
import com.trading.alphatrading.repository.UserRepository;
import com.trading.alphatrading.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException {
    }

    private User createNewUser(UserSignUpPayload userSignUpPayload){
        return new User(userSignUpPayload.getEmailId(), userSignUpPayload.getPassword(), userSignUpPayload.getPhoneNumber(), userSignUpPayload.getLastName(), userSignUpPayload.getFirstName(), true);
    }

}
