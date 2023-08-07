package com.salus.utils;

public class EnumUtils {

    public static <E extends Enum<E>> boolean isEnum(String str, Class<E> enumType) {
        E[] listEnum = enumType.getEnumConstants();
        for (Enum<E> enumClass : listEnum) {
            if (enumClass.name().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
