package com.yjx.template.pojo;

import java.io.Serializable;

/**
 * Created by yejx on 2017/3/23.
 */
public class ResetPasswordDTO implements Serializable {
    private static final long serialVersionUID = 6294767725541646884L;

    private String code;

    private String username;

    private String password;

    private String rePassword;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResetPasswordDTO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", rePassword='").append(rePassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
