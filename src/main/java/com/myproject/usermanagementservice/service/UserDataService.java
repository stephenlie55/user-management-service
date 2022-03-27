package com.myproject.usermanagementservice.service;

import com.myproject.usermanagementservice.domain.UserDataResponse;
import com.myproject.usermanagementservice.domain.entity.UserData;
import com.myproject.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class UserDataService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RemoteCacheManager remoteCacheManager;

    private static final String CACHE_NAME_USER_DATA = "user_data";

    public UserDataResponse saveOrUpdateUserData(UserData userData) {
        UserDataResponse userDataResponse = new UserDataResponse();
        String activityName = "Create New User Data";
        String status = "Success";
        Date timeStamp = new Date();
        try {
            userData.setCreateDate(timeStamp);
            if (userData.getId() != null) {
                userData.setCreateDate(null);
                userData.setModifiedDate(timeStamp);
                activityName = "Update User Data";
            }
            UserData updatedUserData = userRepository.save(userData);
            if (ObjectUtils.isEmpty(updatedUserData)) {
                status = "Failed";
            }

            // Update or store user data to cache server
            remoteCacheManager.getCache(CACHE_NAME_USER_DATA).put(userData.getId(), updatedUserData);

            userDataResponse.setActivityName(activityName);
            userDataResponse.setStatus(status);
            userDataResponse.setCreatedDate(LocalDateTime.now());
        } catch (Exception ex) {
            log.info("Exception happened when perform [{}], detail: {}", activityName, ex);
            throw ex;
        }

        return userDataResponse;
    }

    public UserDataResponse getUserData(String phoneNumber) {
        UserData userData = new UserData();
        UserDataResponse userDataResponse = UserDataResponse.builder()
                .createdDate(LocalDateTime.now())
                .activityName("Get User Data")
                .status("Success")
                .build();

        try {
            userData = (UserData) remoteCacheManager.getCache(CACHE_NAME_USER_DATA).get(phoneNumber);

            if (ObjectUtils.isEmpty(userData)) {
                userData = userRepository.findByPhoneNumber(phoneNumber).orElse(new UserData());
                if (ObjectUtils.isEmpty(userData)) {
                    userDataResponse.setStatus("Failed");
                    userDataResponse.setDescription("User not found");
                } else {
                    remoteCacheManager.getCache(CACHE_NAME_USER_DATA).put(phoneNumber, userData);
                }
            }

            userDataResponse.setUserData(userData);
        } catch (Exception ex) {
            log.info("Exception happened when get user data with user id: {}, detail: {}", phoneNumber, ex);
            throw ex;
        }

        return userDataResponse;
    }
}
