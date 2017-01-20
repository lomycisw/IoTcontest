package com.example.f_masa.proximityareadetection.validate;

import android.widget.EditText;

import com.example.f_masa.proximityareadetection.R;

/**
 * Created by f-masa on 2017/01/12.
 */
public class MinLengthValidatable implements Validatable {
    // default length
    private int minText;

    public MinLengthValidatable(int minText) {
        this.minText = minText;
    }

    @Override
    public ValidateDto isValid(EditText editText) {
        boolean isValid = true;
        if (editText.getText().length() < minText) {
            isValid = false;
        }

        ValidateDto ret = new ValidateDto(isValid, 10);
        return ret;
    }

    @Override
    public String getTailMessage() {
        return String.valueOf(minText);
    }

}