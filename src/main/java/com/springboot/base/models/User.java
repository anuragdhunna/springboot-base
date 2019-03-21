package com.springboot.base.models;

import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author anuragdhunna
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(length = 500)
    private String username;

    @Column(length = 200)
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 200)
    @JoinTable(name = "user_role", joinColumns = {
            @javax.persistence.JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @javax.persistence.JoinColumn(name = "role_id")})
    private Set<Role> roles;

    private String token;

}