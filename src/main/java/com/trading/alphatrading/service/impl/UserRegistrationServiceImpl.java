package com.trading.alphatrading.service.impl;

import com.trading.alphatrading.api.request.UserSignUpPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.model.UsersBrokers;
import com.trading.alphatrading.repository.UsersBrokersRepository;
import com.trading.alphatrading.repository.UsersRepository;
import com.trading.alphatrading.service.UserRegistrationService;
import com.trading.alphatrading.utils.MailSenderUtil;
import com.trading.alphatrading.utils.validation.ValidationInterface;
import com.trading.alphatrading.utils.TwilioUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final Logger logger = LogManager.getLogger(UserRegistrationServiceImpl.class);

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ValidationInterface validationInterface;

    @Autowired
    private TwilioUtil twilioUtil;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Autowired
    private UsersBrokersRepository usersBrokersRepository;

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
    public void sendOtpTwilio(String phoneNumber) throws AlgoTradingException{
        twilioUtil.sendOtpTwilio(phoneNumber);
    }

    @Override
    public Boolean verifyOtpTwilio(String phoneNumber, String otpEntered) {
        return twilioUtil.verifyOtpTwilio(phoneNumber, otpEntered);
    }

    @Override
    public void sendOtpEmail(String fromEmailId, List<String> toEmailIds, String subject, String body) throws AlgoTradingException{
        mailSenderUtil.sendEmail(fromEmailId, toEmailIds, subject, body);
    }

    @Override
    public Users getByPhoneNumber(String phoneNumber) throws AlgoTradingException {
        Optional<Users> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isEmpty()) {
            String errorMessage = "Phone number provided does not match any entry for any existing user.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.NOT_FOUND, logger);
        }
        return user.get();
    }

    @Override
    public Users getByEmailId(String emailId) throws AlgoTradingException {
        Optional<Users> user = userRepository.findByEmailId(emailId);
        if (user.isEmpty()) {
            String errorMessage = "EmailId provided does not match any entry for any existing user.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.NOT_FOUND, logger);
        }
        return user.get();
    }

    @Override
    public void enableDisableUser(Integer userId, Integer brokerId, Boolean enableOrDisable) throws AlgoTradingException {
        Optional<UsersBrokers> optionalUsersBrokers = usersBrokersRepository.findByUserIdAndBrokerId(userId, brokerId);
        if(optionalUsersBrokers.isEmpty()){
            String errorMessage = "The user with userId : "+userId+" and brokerId : "+brokerId+ " does not exist in the database.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.NOT_FOUND, logger);
        }

        UsersBrokers usersBrokers = optionalUsersBrokers.get();
        usersBrokers.setActive(enableOrDisable);
        usersBrokersRepository.save(usersBrokers);
    }
}
