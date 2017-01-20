package com.example.f_masa.proximityareadetection.validate;

import android.content.Context;
import android.opengl.EGLDisplay;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.validation.Validator;

/**
 * Created by f-masa on 2017/01/12.
 */

public class ValidateManager {
    private Map<EditText,Validators> validateMap;
    private Context context;

    public ValidateManager(Context context){
        this.context = context;
        validateMap = new HashMap<>();
    }

    public void add(EditText editText , Validators validators){
        validateMap.put(editText,validators);
    }

    public List<String> validate(){
        List<String> errorMassages = new ArrayList<>();

        for(Map.Entry<EditText,Validators> keyValue : validateMap.entrySet()){
            Validators validators = keyValue.getValue();
            EditText editText = keyValue.getKey();

            for(Validatable validatable : validators.as_list()) {
                ValidateDto validateResult = validatable .isValid(editText);
                if (validateResult.isValid()) {
                    continue;
                }

                StringBuffer errStr = new StringBuffer();
                errStr.append(editText.getContentDescription().toString()).append(context.getString(validateResult.getMessage()));
                if (validatable.getTailMessage() != null){
                    errStr.append(validatable.getTailMessage());
                }
                errorMassages.add(errStr.toString());

            }
        }
        return errorMassages;
    }

}

