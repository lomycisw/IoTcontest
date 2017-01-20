package com.example.f_masa.proximityareadetection.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f-masa on 2017/01/12.
 */
public class Validators {
    private List<Validatable> validateLists;

    public Validators() {
        validateLists = new ArrayList<Validatable>();
    }

    public void add(Validatable validatable) {
        validateLists.add(validatable);
    }

    public List<Validatable> as_list() {
        return validateLists;
    }
}