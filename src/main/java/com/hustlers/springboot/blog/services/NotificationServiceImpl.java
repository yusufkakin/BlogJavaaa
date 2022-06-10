package com.hustlers.springboot.blog.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    //special place where you can store objects (key : value) and they persist for a long time.
    //even survives redirection
    private HttpSession httpSession;

    @Autowired
    public NotificationServiceImpl(HttpSession theHttpSession) {
        httpSession = theHttpSession;
    }

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.ERROR, msg);
    }

    private void addNotificationMessage(NotificationMessageType type, String msg){
        List<NotificationMessage> notifyMessages =
                (List<NotificationMessage>)httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        if(notifyMessages == null){
            notifyMessages = new ArrayList<>();
        }
        notifyMessages.add(new NotificationMessage(type, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }

    public enum NotificationMessageType{
        INFO,
        ERROR
    }

    @AllArgsConstructor @Getter
    public class NotificationMessage{
        NotificationMessageType type;
        String text;
    }
}
