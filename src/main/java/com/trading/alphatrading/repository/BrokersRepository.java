package com.trading.alphatrading.repository;

import com.trading.alphatrading.model.Brokers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokersRepository extends JpaRepository<Brokers, Integer> {
}
