package com.internetcafe.internetcafesystem.restImplementation;

import com.internetcafe.internetcafesystem.constents.CafeConstants;
import com.internetcafe.internetcafesystem.rest.UserRest;
import com.internetcafe.internetcafesystem.service.UserService;
import com.internetcafe.internetcafesystem.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
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
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
