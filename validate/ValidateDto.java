package com.example.f_masa.proximityareadetection.validate;

/**
 * Created by f-masa on 2017/01/12.
 */
public class ValidateDto {
    private boolean ret;
    private int message;

    public ValidateDto(boolean ret, int message) {
        this.ret = ret;
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public boolean isValid() {
        return ret;
    }
}