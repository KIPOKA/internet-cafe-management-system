package com.internetcafe.internetcafesystem.service;

import com.internetcafe.internetcafesystem.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {


    public ResponseEntity<String> signUp(Map<String, String> requestMap);
    public ResponseEntity<String> login(Map<String, String> requestMap);
    ResponseEntity<List<UserWrapper>> getAllUsers();

    ResponseEntity<String> update(Map<String, String> requestMap);
}
