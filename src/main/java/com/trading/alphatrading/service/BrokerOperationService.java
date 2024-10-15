package com.trading.alphatrading.service;


import com.trading.alphatrading.model.Brokers;

import java.util.List;

public interface BrokerOperationService {
    void addBroker(String brokerName);
    void deleteBroker(String brokerName);

    List<Brokers> findAllBrokers();
}
