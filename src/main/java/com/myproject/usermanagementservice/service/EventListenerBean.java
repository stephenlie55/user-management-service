package com.myproject.usermanagementservice.service;

import com.myproject.usermanagementservice.domain.entity.UserData;
import com.myproject.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j
public class EventListenerBean {
    @Autowired
    private UserRepository userRepository;

    /**
    * @param event
    * @Stephen
    * 27 Maret 2022, Add Loader For Mocking User Data
    */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("======== START INSERTING USER DATA ========");
        try {
            userRepository.save(UserData.builder()
                    .accountBalance(BigDecimal.valueOf(1000000))
                    .createDate(new Date())
                    .email("usertest1@email.com")
                    .phoneNumber("081234567890")
                    .userFullName("User Test 1")
                    .build());

            userRepository.save(UserData.builder()
                    .accountBalance(BigDecimal.valueOf(500000))
                    .createDate(new Date())
                    .email("usertest2@email.com")
                    .phoneNumber("081234567891")
                    .userFullName("User Test 2")
                    .build());
        } catch (Exception ex) {
            log.error("Exception happened when mocking user data, detail: {}", ex);
        }
        log.info("======== END EVENT ========");
    }
}
