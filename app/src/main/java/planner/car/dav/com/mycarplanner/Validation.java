package planner.car.dav.com.mycarplanner;

import android.util.Log;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Bascule on 14/09/2015.
 */
public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String REGISTRAIONNEW_REGEX ="[A-Z]{2}?[0-9]{3}?[A-Z]{2}?";
    private static final String REGISTRAIONOLD_REGEX ="[0-9]{3}[A-Z]{3}[0-9]{2}";


    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String REGISTARTION_MSG = "invalid registration";
    private static final String PHONE_MSG = "###-#######";

    // call this method when you need to check new register validation
    public static boolean isRegistrationNew(EditText editText, boolean required) {
        return isValid(editText, REGISTRAIONNEW_REGEX, REGISTARTION_MSG, required);
    }

    // call this method when you need to check email validation
    public static boolean isRegistrationOld(EditText editText, boolean required) {
        Log.i(Acceuil.APP_TAG,"Check old validation");
        return isValid(editText, REGISTRAIONOLD_REGEX, REGISTARTION_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    public static boolean hasText(EditText editText) {
        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}
