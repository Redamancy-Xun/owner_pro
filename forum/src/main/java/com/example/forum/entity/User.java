package com.example.forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "studentid", nullable = false)
    private String studentid;

    @Column(name = "studentname", nullable = false)
    private String studentname;

    @Column(name = "birthday", nullable = true)
    private String birthday;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "headportrait", nullable = true)
    private String headportrait;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
