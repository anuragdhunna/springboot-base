package com.springboot.base.models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author anuragdhunna 
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public Role() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(unique = true, name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;
}