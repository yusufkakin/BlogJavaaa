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

    //   This tells the user that their username and password must be greater than 2 letters.
    @Size(min = 4, max = 30, message = "Username size should be in range [4...30]")
    private String username;

    @NotNull
    @Size(min = 8, max = 50, message = "Password size should be in range [8...30]")
    private String password;
}
