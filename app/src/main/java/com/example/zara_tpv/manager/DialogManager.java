package com.example.zara_tpv.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListClothesAdapter;
import com.example.zara_tpv.pojo.ListClothes;
import com.example.zara_tpv.windows.ResumeShopWindow;

import org.w3c.dom.Text;

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

    }

    public static void openDialogClothe(Context context, ListClothesAdapter adapter, ListClothes clothe, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe);

        final TextView textName = dialog.findViewById(R.id.textView_title_name_clothe);
        final Button buttonModifyClothe = dialog.findViewById(R.id.button_modify_clothe);
        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe);

        textName.setText(clothe.getName());

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

    public static void openDialogLogin(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_in);

        final EditText usernameSignIn = dialog.findViewById(R.id.editText_username_sign_in);
        final EditText passwordSignIn = dialog.findViewById(R.id.editText_password_sign_in);
        final Button buttonSignIn = dialog.findViewById(R.id.button_sign_in);
        final TextView textViewSignUp = dialog.findViewById(R.id.textView_sign_up);

        setSpannableString(textViewSignUp, context);

        buttonSignIn.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            dialog.dismiss();
        });
        dialog.show();
    }

    private static void setSpannableString(TextView textView, Context context) {
        SpannableString registerString = new SpannableString(context.getString(R.string.textView_sign_up));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
        ClickableSpan registerSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                openDialogCode(context);
            }
        };

        registerString.setSpan(registerSpan, 0, registerString.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerString.setSpan(colorSpan, 0, registerString.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(registerString);
    }
}
