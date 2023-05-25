package com.example.zara_tpv.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.DiscountAdapter;
import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.windows.DiscountWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class DialogManager {
    private Resources res;

    public static void openDialogCode(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_code);

        final EditText numBarcode = dialog.findViewById(R.id.editText_num_barcode);
        final Button buttonInsertBarcode = dialog.findViewById(R.id.button_insert_barcode);

        buttonInsertBarcode.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            dialog.dismiss();
        });

        dialog.show();
    }

    private static void openDialogSignUp(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_up);

        final EditText textIdentification = dialog.findViewById(R.id.editText_username_sign_up);
        final EditText textNumber = dialog.findViewById(R.id.editText_telephone_sign_up);
        final EditText textEmail = dialog.findViewById(R.id.editText_email_sign_up);
        final EditText textPassword = dialog.findViewById(R.id.editText_password_sign_up);
        final EditText textRepeatPassword = dialog.findViewById(R.id.editText_repeat_password_sign_up);
        final Button buttonSignUp = dialog.findViewById(R.id.button_sign_up);

        EditText[] textToValidate = {textIdentification, textEmail, textNumber, textPassword, textRepeatPassword};

        buttonSignUp.setOnClickListener((v) -> {
            if(ValidatorManager.passValidation(textToValidate, context)) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void openDialogError(Context context, String textError) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_error_dialog);

        final TextView textErr = dialog.findViewById(R.id.errorDesc);
        final Button buttonClose = dialog.findViewById(R.id.errorButton);
        textErr.setText(textError);

        buttonClose.setOnClickListener((v) -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogClothe(Context context, ListClothesAdapter adapter, Producto clothe, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe);

        final TextView textName = dialog.findViewById(R.id.textView_title_name_clothe);
        final Button buttonModifyClothe = dialog.findViewById(R.id.button_modify_clothe);
        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe);

        textName.setText(clothe.getDescripcion());

        buttonModifyClothe.setOnClickListener((v) -> {
            dialog.dismiss();
        });

        buttonDeleteClothe.setOnClickListener((v) -> {
            adapter.removeItem(position);
            Toast.makeText(context, context.getString(R.string.message_delete_clothe), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogClothe(Context context, Producto clothe) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe);

        final TextView textName = dialog.findViewById(R.id.textView_title_name_clothe);
        final Button buttonModifyClothe = dialog.findViewById(R.id.button_modify_clothe);
        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe);

        textName.setText(clothe.getDescripcion());

        buttonModifyClothe.setOnClickListener((v) -> {
            dialog.dismiss();
        });

        buttonDeleteClothe.setOnClickListener((v) -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogLogin(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_in);

        final EditText usernameSignIn = dialog.findViewById(R.id.editText_username_sign_in);
        final EditText passwordSignUp = dialog.findViewById(R.id.editText_password_sign_in);
        final Button buttonSignIn = dialog.findViewById(R.id.button_sign_in);
        final TextView textViewSignUp = dialog.findViewById(R.id.textView_sign_up);

        setSpannableString(textViewSignUp, context);

        buttonSignIn.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            if(!ValidatorManager.passValidationContent(usernameSignIn, context)
                    || !ValidatorManager.passValidationContent(usernameSignIn, context)) {
                openDialogError(context, context.getResources().getString(R.string.message_error_empty_field));
            } else {
                Intent intent = new Intent(context, DiscountWindow.class);
                context.startActivity(intent);
            }
        });

        textViewSignUp.setOnClickListener((v) -> {
            openDialogSignUp(context);
            dialog.dismiss();
        });

        dialog.show();
    }

    private static void setSpannableString(TextView textView, Context context) {
        final SpannableString registerString = new SpannableString(context.getString(R.string.textView_sign_up));
        applySpan(registerString, context.getString(R.string.textView_sign_up), new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
            }
        });

        textView.setText(registerString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static void applySpan(SpannableString spannable, String target, ClickableSpan span) {
        final String spannableString = spannable.toString();
        final int start = spannableString.indexOf(target);
        final int end = start + target.length();
        spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static void openDialogDiscounts(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_discounts);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1, "Descripcion 50%", 0.5));
        discounts.add(new Discount(2, "Descripcion 25%", 0.25));

        DiscountAdapter adapter = new DiscountAdapter(discounts);

        final Button buttonApply = dialog.findViewById(R.id.button_apply_discount);

        buttonApply.setOnClickListener((v) -> {
            dialog.dismiss();
        });
    }
}
