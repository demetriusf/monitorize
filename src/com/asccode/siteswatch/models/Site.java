package com.asccode.siteswatch.models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 08/04/13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class Site implements Serializable {

    private User user;
    private String name;
    private String url;
    private Boolean receiveNotification;
    private Boolean optPing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getReceiveNotification() {
        return receiveNotification;
    }

    public void setReceiveNotification(Boolean receiveNotification) {
        this.receiveNotification = receiveNotification;
    }

    public Boolean getOptPing() {
        return optPing;
    }

    public void setOptPing(Boolean optPing) {
        this.optPing = optPing;
    }
}
