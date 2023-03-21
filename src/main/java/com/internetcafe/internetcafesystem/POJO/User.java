package com.internetcafe.internetcafesystem.POJO;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
@NamedQuery(name="User.findByEmailId", query ="select u from User u where u.email =:email")
@NamedQuery(name="User.getAllUsers", query ="select new com.internetcafe.internetcafesystem.wrapper.UserWrapper(u.id, u.name, u.contactNumber, u.email, u.password, u.status, u.role) " +
        "from User u where u.role ='user'")
@NamedQuery(name="User.updateStatus", query ="update  User u set u.status=:status where u.id =:id")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID =1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private String role;
}
