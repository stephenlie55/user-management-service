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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
    @GetMapping(value = "/{userDataId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDataResponse getUserData(@PathVariable Long userDataId) {
        log.info("In method getUserData with parameter: userDataId: {}", userDataId);
        return userDataService.getUserData(userDataId);
    }

    @ApiOperation(value = "Service to update UserProfile")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{idToUpdate}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDataResponse updateUserData(@RequestBody(required = false) UserData userData, @PathVariable Long idToUpdate,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("In method updateUserData with parameters: UserData {} and user ID: {}", userData, idToUpdate);

        UserDataResponse userDataResponse = userDataService.getUserData(idToUpdate);

        if ("Failed".equalsIgnoreCase(userDataResponse.getStatus())) {
            throw new Exception();
        }

        return userDataService.saveOrUpdateUserData(userData);

    }
}
