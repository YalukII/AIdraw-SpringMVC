package com.example.aidraw.VO;

import lombok.Data;

@Data
public class R {
    private Boolean success;
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 把构造方法私有
     */
    private R() {
    }

    /**
     * 成功静态方法
     *
     * @return
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMsg("成功");
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.getCode());
        r.setMsg(msg);
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMsg(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    public R data( Object value) {
        this.data = value;
        return this;
    }
}
