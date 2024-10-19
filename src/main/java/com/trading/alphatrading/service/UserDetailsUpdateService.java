package com.trading.alphatrading.service;

import com.trading.alphatrading.api.request.UpdateEmailIdPayload;
import com.trading.alphatrading.api.request.UpdateNamePayload;
import com.trading.alphatrading.api.request.UpdatePhoneNumberPayload;
import com.trading.alphatrading.exception.AlgoTradingException;
import com.trading.alphatrading.model.Users;

public interface UserDetailsUpdateService {

    Users updateName(UpdateNamePayload updateNamePayload) throws AlgoTradingException;

    Users updateEmailId(UpdateEmailIdPayload updateEmailIdPayload) throws AlgoTradingException;

    Users updatePhoneNumber(UpdatePhoneNumberPayload updatePhoneNumberPayload) throws AlgoTradingException;
}
