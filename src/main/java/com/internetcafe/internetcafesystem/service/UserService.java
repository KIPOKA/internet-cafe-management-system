package com.internetcafe.internetcafesystem.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {


    public ResponseEntity<String> signUp(Map<String, String> requestMap);
}
