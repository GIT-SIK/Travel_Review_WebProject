package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    @Builder
    public User(String id, String password, String email, String tel, String username, String role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.role = role;
    }
}
