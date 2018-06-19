package br.com.cedro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static Gson getGsonDate(String format) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer(format));
        return gsonBuilder.create();
    }

    public static Date getDateFromString(String str, String format) {
        try {
            if (!TextUtils.isEmpty(str))
                return new SimpleDateFormat(format, Locale.getDefault()).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static class DateTypeDeserializer implements JsonDeserializer<Date> {

        private String formatDate;

        public DateTypeDeserializer(String formatDate) {
            this.formatDate = formatDate;
        }

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            return Utils.getDateFromString(json.getAsString(), formatDate);
        }
    }

    /**
     * @param context Activity/Context
     * @param view    Field that holds the keyboard focus
     */
    public static void closeKeyboard(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ex) {
            Log.e("AndroidUtils", "Error occurred trying to hide the keyboard. Exception: " + ex);
        }
    }


    /**
     * @param email Email
     * @return True if is a valid type of email
     */
    public static boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidLogin(String login) {
        if (login.length() > 5 && login.length() < 15)
            return true;

        return false;
    }

    public static boolean regexNumber(CharSequence password) {
        return password.toString().matches("^(?=.*[0-9]).{1,}$");
    }

    public static boolean regexStrings(CharSequence password) {
        return password.toString().matches("^(?=.*[A-z]).{1,}$");
    }

    public static boolean regexCharactersSpecial(CharSequence password) {
        return password.toString().matches("^(?=.*[\\W]).{1,}$");
    }

    public static boolean regexNumberString(CharSequence password) {
        return password.toString().matches("^(?=.*[0-9])(?=.*[A-z]).{1,}$");
    }

    public static boolean regexNumberCharactersSpecial(CharSequence password) {
        return password.toString().matches("^(?=.*[0-9])(?=.*[\\W]).{1,}$");
    }

    public static boolean regexStringCharactersSpecial(CharSequence password) {
        return password.toString().matches("^(?=.*[A-z])(?=.*[\\W]).{1,}$");
    }

    public static boolean regexNumberStringCharactersSpecial(CharSequence password) {
        return password.toString().matches("^(?=.*[0-9])(?=.*[A-z])(?=.*[\\W]).{1,}$");
    }
}
