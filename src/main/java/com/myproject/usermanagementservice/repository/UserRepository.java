package com.myproject.usermanagementservice.repository;

import com.myproject.usermanagementservice.domain.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByPhoneNumber(String phoneNumber);
}
