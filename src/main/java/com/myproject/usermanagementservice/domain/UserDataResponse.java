package com.myproject.usermanagementservice.domain;

import com.myproject.usermanagementservice.domain.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDataResponse implements Serializable {
    private static final long serialVersionUID = 1042105580934077074L;

    private String activityName;
    private String status;
    private LocalDateTime createdDate;
    private UserData userData;
    private String description;
}
