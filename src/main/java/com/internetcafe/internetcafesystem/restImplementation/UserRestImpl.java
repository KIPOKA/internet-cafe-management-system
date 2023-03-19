package com.internetcafe.internetcafesystem.restImplementation;

import com.internetcafe.internetcafesystem.constents.CafeConstents;
import com.internetcafe.internetcafesystem.rest.UserRest;
import com.internetcafe.internetcafesystem.service.UserService;
import com.internetcafe.internetcafesystem.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //return new ResponseEntity<String>("{\"message\": \"Something went wrong\}", HttpStatus.);
        return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
