package com.example.zara_tpv.manager;

import android.content.Context;
import android.widget.EditText;
import com.example.zara_tpv.R;

public class ValidatorManager {
    public static boolean passValidation(EditText[] textToValidate, Context context) {
        boolean existsText = passValidationContent(textToValidate[0], context) && passValidationContent(textToValidate[1], context);
        boolean correctPassword = false;
        if(passValidationContent(textToValidate[2], context) && passValidationContent(textToValidate[3], context)) {
            correctPassword = correctPassword(textToValidate[2], textToValidate[3], context);
        }
        return correctPassword && existsText;
    }

    public static boolean passValidationContent(EditText text, Context context) {
        String contentText = text.getText().toString();
        if(contentText.length() == 0) {
            DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_empty_field));
        }
        return contentText.length() > 0;
    }

    public static boolean correctPassword(EditText textPassword, EditText textRepeatPassword, Context context) {
        boolean areSame = textPassword.getText().toString().equals(textRepeatPassword.getText().toString());
        if(!areSame) {
            DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_different_passwords));
        }
        return areSame;
    }
}
