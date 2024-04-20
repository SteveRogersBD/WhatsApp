package com.example.whatsapp.Models;

public class Users {
    String profPic, userName, mail, password, userId,lastMsg,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(String profPic, String userName, String mail, String password, String userId, String lastMsg, String status) {
        this.profPic = profPic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMsg = lastMsg;
        this.status = status;
    }

    public Users() {
    }

    public Users(String userName, String mail, String password)
    {
        //SignUp constructor
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getProfPic() {
        return profPic;
    }

    public void setProfPic(String profPic) {
        this.profPic = profPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getUserId() {
        return this.userId;
    }
}
