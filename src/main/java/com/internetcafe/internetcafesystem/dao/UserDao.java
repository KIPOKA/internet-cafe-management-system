package com.internetcafe.internetcafesystem.dao;

import com.internetcafe.internetcafesystem.POJO.User;
import com.internetcafe.internetcafesystem.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository <User, Integer>{
    User findByEmailId(@Param("email") String email);
    List<UserWrapper> getAllUsers();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
}
