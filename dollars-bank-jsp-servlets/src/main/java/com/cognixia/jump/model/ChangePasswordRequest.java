package com.cognixia.jump.model;

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String newPasswordMatch;

    public ChangePasswordRequest() {
        this.oldPassword = "";
        this.newPassword = "";
        this.newPasswordMatch = "";
    }

    public ChangePasswordRequest(String oldPassword, String newPassword, String newPasswordMatch) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordMatch = newPasswordMatch;
    }

    public boolean passwordMatch() {
        if(newPasswordMatch == "") {
            return false;
        }
        return newPassword.equals(newPasswordMatch);
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordMatch() {
        return this.newPasswordMatch;
    }

    public void setNewPasswordMatch(String newPasswordMatch) {
        this.newPasswordMatch = newPasswordMatch;
    }

    @Override
    public String toString() {
        return "{" +
            " oldPassword='" + getOldPassword() + "'" +
            ", newPassword='" + getNewPassword() + "'" +
            ", newPasswordMatch='" + getNewPasswordMatch() + "'" +
            "}";
    }



}
