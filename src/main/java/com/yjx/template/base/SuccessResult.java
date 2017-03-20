package com.yjx.template.base;

public class SuccessResult implements Result{
    private Object data;

    public SuccessResult(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return true;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
