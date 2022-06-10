package com.hustlers.springboot.blog.services;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);

}
