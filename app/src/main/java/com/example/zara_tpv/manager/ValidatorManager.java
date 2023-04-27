package com.example.zara_tpv.manager;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zara_tpv.R;

public class ValidatorManager {
    public static boolean passValidation(EditText[] textToValidate, Context context) {
        boolean correctId = passValidationID(textToValidate[0], context);
        boolean existsText = passValidationContent(textToValidate[1], context) && passValidationContent(textToValidate[2], context);
        boolean correctPassword = false;
        if(passValidationContent(textToValidate[3], context) && passValidationContent(textToValidate[4], context)) {
            correctPassword = correctPassword(textToValidate[3], textToValidate[4], context);
        }
        return correctId && correctPassword && existsText;
    }

    public static boolean passValidationContent(EditText text, Context context) {
        String contentText = text.getText().toString();
        if(contentText.length() == 0) {
            DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_empty_field));
        }
        return contentText.length() > 0;
    }

    public static boolean passValidationID(EditText textIdentification, Context context) {
        String textId = textIdentification.getText().toString();
        boolean correctLength = (textId.length() == 8);
        boolean correctType = false;
        if(correctLength) {
            correctType = (Character.isDigit(textId.charAt(textId.length() - 1)));
            if(!correctType) {
                DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_not_letters));
            }
        } else {
            DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_not_long));
        }
        return correctLength && correctType;
    }

    public static boolean correctPassword(EditText textPassword, EditText textRepeatPassword, Context context) {
        boolean areSame = textPassword.getText().toString().equals(textRepeatPassword.getText().toString());
        if(!areSame) {
            DialogManager.openDialogError(context, context.getResources().getString(R.string.message_error_different_passwords));
        }
        return areSame;
    }
}
