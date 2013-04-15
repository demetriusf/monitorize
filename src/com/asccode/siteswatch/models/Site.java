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

    private int id;
    private String name;
    private String endereco;
    private Boolean receiveAndroidNotification;
    private Boolean optPing;

    @Override
    public String toString(){

        return String.format(this.getName());

    }

    @Override
    public boolean equals(Object o) {

        return ((Site) o).getId() == this.getId() && ((Site) o).getId() != 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String url) {
        this.endereco = url;
    }

    public Boolean getReceiveAndroidNotification() {
        return receiveAndroidNotification;
    }

    public void setReceiveAndroidNotification(Boolean receiveAndroidNotification) {
        this.receiveAndroidNotification = receiveAndroidNotification;
    }

    public Boolean getOptPing() {
        return optPing;
    }

    public void setOptPing(Boolean optPing) {
        this.optPing = optPing;
    }
}
