package com.trading.alphatrading.service.impl;

import com.trading.alphatrading.api.request.UpdateEmailIdPayload;
import com.trading.alphatrading.api.request.UpdateNamePayload;
import com.trading.alphatrading.api.request.UpdatePasswordPayload;
import com.trading.alphatrading.api.request.UpdatePhoneNumberPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.repository.UsersRepository;
import com.trading.alphatrading.service.UserDetailsUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsUpdateServiceImpl implements UserDetailsUpdateService {
    private final Logger logger = LogManager.getLogger(UserDetailsUpdateServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users updateName(UpdateNamePayload updateNamePayload) throws AlgoTradingException {
        Optional<Users> optionalUser = usersRepository.findByPhoneNumberAndPassword(updateNamePayload.getPhoneNumber(), updateNamePayload.getPassword());
        if (optionalUser.isEmpty()) {
            String errorMessage = "User with phoneNumber : " + updateNamePayload.getPhoneNumber() + " is not present in the system.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
        Users user = optionalUser.get();
        user.setFirstName(updateNamePayload.getFirstName());
        user.setLastName(updateNamePayload.getLastName());
        usersRepository.save(user);
        return user;
    }

    @Override
    public Users updateEmailId(UpdateEmailIdPayload updateEmailIdPayload) throws AlgoTradingException {
        Optional<Users> optionalUser = usersRepository.findByEmailIdAndPassword(updateEmailIdPayload.getOldEmailId(), updateEmailIdPayload.getPassword());
        if (optionalUser.isEmpty()) {
            String errorMessage = "User with old emailId : " + updateEmailIdPayload.getOldEmailId() + " is not present in the system.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
        Users user = optionalUser.get();
        user.setEmailId(updateEmailIdPayload.getNewEmailId());
        usersRepository.save(user);
        return user;

    }

    @Override
    public Users updatePhoneNumber(UpdatePhoneNumberPayload updatePhoneNumberPayload) throws AlgoTradingException {
        Optional<Users> optionalUser = usersRepository.findByPhoneNumberAndPassword(updatePhoneNumberPayload.getOldPhoneNumber(), updatePhoneNumberPayload.getPassword());
        if (optionalUser.isEmpty()) {
            String errorMessage = "User with old phoneNumber : " + updatePhoneNumberPayload.getOldPhoneNumber() + " is not present in the system.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
        Users user = optionalUser.get();
        user.setPhoneNumber(updatePhoneNumberPayload.getNewPhoneNumber());
        usersRepository.save(user);
        return user;
    }

    @Override
    public Users updatePassword(UpdatePasswordPayload updatePasswordPayload) throws AlgoTradingException {
        Optional<Users> optionalUser = usersRepository.findByPhoneNumber(updatePasswordPayload.getPhoneNumber());
        if (optionalUser.isEmpty()) {
            String errorMessage = "User with phoneNumber : " + updatePasswordPayload.getPhoneNumber() + " is not present in the system.";
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.BAD_REQUEST, logger);
        }
        Users user = optionalUser.get();
        user.setPassword(updatePasswordPayload.getNewPassword());
        usersRepository.save(user);
        return user;
    }
}
