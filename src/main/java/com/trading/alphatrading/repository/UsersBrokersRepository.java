package com.trading.alphatrading.repository;

import com.trading.alphatrading.model.Brokers;
import com.trading.alphatrading.model.Users;
import com.trading.alphatrading.model.UsersBrokers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersBrokersRepository extends JpaRepository<UsersBrokers, Integer> {
}
