package com.trading.alphatrading.repository;

import com.trading.alphatrading.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByPhoneNumber(String phoneNumber);

    Optional<Users> findByEmailId(String emailId);

    Optional<Users> findByEmailIdAndPassword(String emailId, String password);

    Optional<Users> findByPhoneNumberAndPassword(String phoneNumber, String password);

    Optional<Users> findByEmailIdOrPhoneNumber(String emailId, String phoneNumber);
}
