package com.internetcafe.internetcafesystem.serviceImpl;

import com.internetcafe.internetcafesystem.JWT.CustomerUserDetailsService;
import com.internetcafe.internetcafesystem.JWT.JwtUtil;
import com.internetcafe.internetcafesystem.POJO.User;
import com.internetcafe.internetcafesystem.constents.CafeConstants;
import com.internetcafe.internetcafesystem.dao.UserDao;
import com.internetcafe.internetcafesystem.service.UserService;
import com.internetcafe.internetcafesystem.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserDao daoUser;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {


            if (valideSignUpMap(requestMap)) {
                User user = daoUser.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    daoUser.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()){
                if (customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                        return new ResponseEntity<String>("\"user\":\""+
                                jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
                                        customerUserDetailsService.getUserDetail().getRole())+ "\"}", HttpStatus.OK);
                }else{
                    return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception ex){
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\""+"Wrong credentials try again!."+"\"}", HttpStatus.BAD_REQUEST);
    }

    private boolean valideSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }
    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
