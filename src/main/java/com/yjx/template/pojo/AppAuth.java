package com.yjx.template.pojo;

import java.io.Serializable;

public class AppAuth implements Serializable{
    private static final long serialVersionUID = 1891992277272050741L;

    private String appId;

    private String appName;

    private String type;

    private String appSecret;

    private String appDesc;

    private String appProd;

    private String securityLevel;

    private String callbackDomain;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppProd() {
        return appProd;
    }

    public void setAppProd(String appProd) {
        this.appProd = appProd;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getCallbackDomain() {
        return callbackDomain;
    }

    public void setCallbackDomain(String callbackDomain) {
        this.callbackDomain = callbackDomain;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppAuth{");
        sb.append("appId='").append(appId).append('\'');
        sb.append(", appName='").append(appName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", appSecret='").append(appSecret).append('\'');
        sb.append(", appDesc='").append(appDesc).append('\'');
        sb.append(", appProd='").append(appProd).append('\'');
        sb.append(", securityLevel='").append(securityLevel).append('\'');
        sb.append(", callbackDomain='").append(callbackDomain).append('\'');
        sb.append('}');
        return sb.toString();
    }
}