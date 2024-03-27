package com.mokkachocolata.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtillity {
    public Object type;
    private List<Object> array = new ArrayList<>();
    public ArrayUtillity(Object arrayType) {
        type = arrayType;
    }
    public void addToArrayList(Object value) {
        if (value == type) {
            array.add(value);
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void deleteFromArrayList(Object value) {
        if (value == type) {
            array.remove(value);
        } else {
            throw new IllegalArgumentException();
        }
    }
    public Object[] toArray() {
        return array.toArray();
    }
}
