package com.trading.alphatrading.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.repository.UsersRepository;
import com.trading.alphatrading.service.UserRegistrationService;
import com.trading.alphatrading.utils.validation.ValidationInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.trading.alphatrading.utils.ApiCaller.callApi;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final Logger logger = LogManager.getLogger(UserRegistrationServiceImpl.class);
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ValidationInterface validationInterface;

    @Value("${twilio.phoneVerificationUrl}")
    private String twilioPhoneVerificationUrl;

    @Value("${twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;


    @Override
    public Users registerNewUser(UserSignUpPayload userSignUpPayload) throws AlgoTradingException {
        validationInterface.userSignUpValidation(userSignUpPayload);
        Users user = new Users();
        user.setActive(true);
        user.setPassword(userSignUpPayload.getPassword());
        user.setEmailId(userSignUpPayload.getEmailId());
        user.setLastName(userSignUpPayload.getLastName());
        user.setFirstName(userSignUpPayload.getFirstName());
        user.setPhoneNumber(userSignUpPayload.getPhoneNumber());
        userRepository.save(user);
        return user;
    }

    @Override
    public ResponseEntity<String> sendOtp(String phoneNumber, String channel) throws AlgoTradingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("To", phoneNumber);
        requestBody.put("Channel", channel);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(twilioAccountSid, twilioAuthToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return callApi(twilioPhoneVerificationUrl, requestBody, headers, null,
                HttpMethod.POST);

    }

    @Override
    public Users getByPhoneNumber(String phoneNumber) throws AlgoTradingException {
        Optional<Users> user = userRepository.findByPhoneNumber(phoneNumber);
        if(user.isEmpty()){
            String errorMessage = "Phone number provided does not match any entry for any existing user.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.NOT_FOUND, logger);
        }
        return user.get();
    }

    @Override
    public Users getByEmailId(String emailId) throws AlgoTradingException {
        Optional<Users> user = userRepository.findByEmailId(emailId);
        if(user.isEmpty()){
            String errorMessage = "EmailId provided does not match any entry for any existing user.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.NOT_FOUND, logger);
        }
        return user.get();
    }
}
