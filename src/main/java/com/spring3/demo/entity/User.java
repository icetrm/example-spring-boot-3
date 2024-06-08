package com.spring3.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String mobile;
    private String email;
    private String gender;
    private Integer age;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String mobile, String email, String gender, Integer age, Date createDate, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.createDate = createDate;
        this.role = role;
    }

    public User(String firstName) {
        this.firstName = firstName;
    }
}
