package com.farmerskorner.loginapp;
public class Users {

    private String id;
    private String userName;
    private String userAge;
    private String userGender;
    private String userCountry;
    private String userPassword;


    public Users(){}
    public Users(String id,String userName,String userAge, String userGender, String userCountry,String userPassword) {
       this.id=id;
        this.userName = userName;
        this.userAge=userAge;
        this.userGender = userGender;
        this.userCountry = userCountry;
        this.userPassword=userPassword;
    }

    public String getId() {
        return id;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserCountry() {
        return userCountry;
    }
}

