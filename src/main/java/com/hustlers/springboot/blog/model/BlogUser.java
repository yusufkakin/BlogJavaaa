package com.hustlers.springboot.blog.model;

import com.hustlers.springboot.blog.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Prints message during registration if password doesnt match, password is missing
// or if the username and full name is missing.
@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class BlogUser {

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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BlogUser() {
    }

//    Error message will pop us stating that the Full Name, Username, and Password must be a certain length
    @NotNull(message = "is required")
    @Size(min = 4, max = 30, message = "Username should be at least 4 characters")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 8, max = 50,message = "Password minimum of 8 character")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 8, max = 50, message = "Password minimum of 8 character")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 4, max = 50, message = "Full Name size should be at least 4 characters")
    private String fullName;


}
