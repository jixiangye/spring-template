package com.yjx.template.base;

public class BizException extends RuntimeException{
    private String code;

    private String message;

    public BizException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BizException{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
