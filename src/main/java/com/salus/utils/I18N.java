package com.salus.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

public class I18N {

    static {
        Locale.setDefault(new Locale("pt", "BR"));
    }

    final public static java.util.ResourceBundle resourceBundle = java.util.ResourceBundle.getBundle("language", java.util.Locale.getDefault());

    public static String getString(String key, Object... args) {
        try {
            String msg = resourceBundle.getString(key);
            if (args != null && args.length > 0) {
                msg = MessageFormat.format(msg, args);
            }
            return msg;
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return key;
        }
    }
}
