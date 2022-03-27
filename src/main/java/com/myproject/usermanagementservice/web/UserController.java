package com.myproject.usermanagementservice.web;

import com.myproject.usermanagementservice.domain.UserDataResponse;
import com.myproject.usermanagementservice.domain.entity.UserData;
import com.myproject.usermanagementservice.service.UserDataService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserDataService userDataService;

    /**
     * this controller is used to get UserProfile by ID
     *
     * @param userDataId
     * @return ResponseEntity
     */
    @ApiOperation(value = "Service to get UserProfile")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userprofileid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDataResponse getUserData(@PathVariable Long userDataId) {
        log.info("In method getUserData with parameter: userDataId: {}", userDataId);
        return userDataService.getUserData(userDataId);
    }
}
