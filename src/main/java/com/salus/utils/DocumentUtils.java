package com.salus.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class DocumentUtils {

    public static boolean isCPF(String CPF) {

        if (CPF == null || CPF.isEmpty()) {
            return false;
        }

        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) {

            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
            num = (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else
            dig10 = (char) (r + 48);

        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else
            dig11 = (char) (r + 48);

        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
            return true;
        } else {
            return false;
        }
    }

    private String formatCPF(String CPF) {
        if (StringUtils.isBlank(CPF)) {
            return null;
        }
        try {
            MaskFormatter mf = new MaskFormatter("AAA.AAA.AAA-AA");
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(CPF);
        } catch (ParseException e) {
            return null;
        }
    }

    private String removeSpecialCharacters(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        if (doc.contains(" ")) {
            doc = doc.replace(" ", "");
        }
        return doc;
    }
}
