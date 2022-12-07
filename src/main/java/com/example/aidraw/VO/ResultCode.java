package com.example.aidraw.VO;

import lombok.Getter;

@Getter
public enum ResultCode {
    /**
     * errors
     */
    SUCCESS(0),
    ERROR(500);

    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }
}