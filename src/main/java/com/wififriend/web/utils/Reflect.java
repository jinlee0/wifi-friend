package com.wififriend.web.utils;

import java.lang.reflect.Field;
import java.util.*;

public class Reflect {
    public static <T> Deque<Field> getAllFields(Class<T> type) {
        return getAllFieldsRecur(new LinkedList<>(), type);
    }

    private static Deque<Field> getAllFieldsRecur(Deque<Field> fields, Class<?> type) {
        Field[] newFields = type.getDeclaredFields();
        for(int i = newFields.length-1; i >= 0; i--) {
            fields.addFirst(newFields[i]);
        }
        if(type.getSuperclass() != null)
            getAllFieldsRecur(fields, type.getSuperclass());
        return fields;
    }
}
