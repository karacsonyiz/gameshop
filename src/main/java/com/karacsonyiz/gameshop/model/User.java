package com.karacsonyiz.gameshop.model;

public class User {

    private long id;
    private String name;
    private String password;
    private String email;
    private UserRole role = UserRole.ROLE_USER;

    public User() {
    }

    public User(long id, String name, String password,String email, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = UserRole.valueOf(role);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public String getRole() {
        return role.name();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = UserRole.valueOf(role);
    }
}

