package com.trading.alphatrading.service.impl;

import com.trading.alphatrading.model.Brokers;
import com.trading.alphatrading.repository.BrokersRepository;
import com.trading.alphatrading.service.BrokerOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerOperationServiceImpl implements BrokerOperationService {
    @Autowired
    private BrokersRepository brokersRepository;

    @Override
    public void addBroker(String brokerName) {
        Brokers broker = new Brokers();
        broker.setName(brokerName);
        brokersRepository.save(broker);
    }

    @Override
    public void deleteBroker(String brokerName) {
        brokersRepository.deleteByName(brokerName);
    }

    @Override
    public List<Brokers> findAllBrokers() {
        return brokersRepository.findAll();
    }
}
