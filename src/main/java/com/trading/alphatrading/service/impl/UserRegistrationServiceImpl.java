package com.trading.alphatrading.service.impl;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.repository.UserRepository;
import com.trading.alphatrading.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException {
    }

    private Users createNewUser(UserSignUpPayload userSignUpPayload){
        return new Users(userSignUpPayload.getEmailId(), userSignUpPayload.getPassword(), userSignUpPayload.getPhoneNumber(), userSignUpPayload.getLastName(), userSignUpPayload.getFirstName(), true);
    }

}
