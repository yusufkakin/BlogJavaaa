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

    //   This tells the user that their username shoud be at least 4 letters and password is at least 8 characters.
    @Size(min = 4, max = 30, message = "Username size should be 4 to 30 characters")
    private String username;

    @NotNull
    @Size(min = 8, max = 50, message = "Username size should be 8 to 50 characters")
    private String password;
}
