package com.example.f_masa.proximityareadetection.validate;

import android.widget.EditText;

/**
 * Created by f-masa on 2017/01/12.
 */

public interface Validatable {
    ValidateDto isValid(EditText editText);
    String getTailMessage();
}