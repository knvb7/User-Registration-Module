package com.trading.alphatrading.repository;

import com.trading.alphatrading.model.UsersBrokers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersBrokersRepository extends JpaRepository<UsersBrokers, Integer> {
    List<UsersBrokers> findByActive(Boolean active);

    Optional<UsersBrokers> findByUserIdAndBrokerIdAndActive(Integer userId, Integer brokerId, Boolean active);

    Optional<UsersBrokers> findByUserIdAndBrokerId(Integer userId, Integer brokerId);
}
