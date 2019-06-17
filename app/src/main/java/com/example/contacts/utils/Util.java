package com.example.contacts.utils;
// validaciones de los campos
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {

    public static boolean isValidName(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }
    //FALTA
    public static boolean isValidfoto(String foto) {
        return Patterns.PHONE.matcher(foto).matches();
    }
    //FALTA
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        return sdf.format(date);
    }

    public static String formatMin(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", Locale.US);
        return sdf.format(date);
    }
}