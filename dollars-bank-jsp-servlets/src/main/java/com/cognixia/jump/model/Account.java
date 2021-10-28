package com.cognixia.jump.model;

public class Account {

    private String name;
    private String email;
    private String address;
    private int accountId;
    private String password;

    public Account(String name, String email, String address, int accountId, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.accountId = accountId;
        this.password = password;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
