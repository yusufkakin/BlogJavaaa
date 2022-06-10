package com.hustlers.springboot.blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = 2, max = 30, message = "Username size should be in range [2...30]")
    private String username;

    @NotNull
    @Size(min = 1, max = 50, message = "Username size should be in range [2...30]")
    private String password;
}
