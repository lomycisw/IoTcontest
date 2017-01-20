package com.example.f_masa.proximityareadetection.validate;

import android.widget.EditText;

/**
 * Created by f-masa on 2017/01/12.
 */

public class MaxLengthValidatable implements Validatable {
    private int minText;

    public MaxLengthValidatable(int minText) {
        this.minText = minText;
    }

    @Override
    public ValidateDto isValid(EditText editText){
        boolean isValid = false;

        return new ValidateDto(isValid ,20);

    }
    @Override
    public String getTailMessage() {
        return String.valueOf(minText);
    }
}
