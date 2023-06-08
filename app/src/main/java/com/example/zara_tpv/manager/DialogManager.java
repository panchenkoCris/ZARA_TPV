package com.example.zara_tpv.manager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.adapter.ListProductsShopAdapter;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Usuarios;
import com.example.zara_tpv.windows.FirstWindow;
import com.example.zara_tpv.windows.PayWindow;
import com.example.zara_tpv.windows.ResumeShopWindow;

public class DialogManager {
    private static ListProductsShopAdapter adapterAdmin;

    public static void openDialogCode(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_code);

        final EditText numBarcode = dialog.findViewById(R.id.editText_num_barcode);
        final Button buttonInsertBarcode = dialog.findViewById(R.id.button_insert_barcode);

        buttonInsertBarcode.setOnClickListener((v) -> {
            try {
                int id = Integer.parseInt(numBarcode.getText().toString());
                ProductsManager.getProducto(id, ResumeShopWindow.getAdapter());
                dialog.dismiss();
            } catch (NumberFormatException ex) {
                Toast.makeText(context, "Solo se pueden insertar números", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private static void openDialogSignUp(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_up);

        final EditText textIdentification = dialog.findViewById(R.id.editText_username_sign_up);
        final EditText textEmail = dialog.findViewById(R.id.editText_email_sign_up);
        final EditText textPassword = dialog.findViewById(R.id.editText_password_sign_up);
        final EditText textRepeatPassword = dialog.findViewById(R.id.editText_repeat_password_sign_up);
        final Button buttonSignUp = dialog.findViewById(R.id.button_sign_up);

        EditText[] textToValidate = {textIdentification, textEmail, textPassword, textRepeatPassword};

        buttonSignUp.setOnClickListener((v) -> {
            if(ValidatorManager.passValidation(textToValidate, context)) {
                UsuariosManager.createUsuarios(new Usuarios(textIdentification.getText().toString(),
                        textEmail.getText().toString(),
                        textPassword.getText().toString(),
                        "cliente"), context);
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

    public static void openDialogClotheTakeOut(Context context, Producto clothe) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe_warehouse);

        final TextView textTitleName = dialog.findViewById(R.id.textView_title_name_clothe_warehouse);
        final TextView textTitleSize = dialog.findViewById(R.id.textView_title_size_clothe_warehouse);
        final TextView textTitleColor = dialog.findViewById(R.id.textView_title_color_clothe_warehouse);
        final TextView textTitlePrice = dialog.findViewById(R.id.textView_title_price_clothe_warehouse);

        final TextView textPrice = dialog.findViewById(R.id.textView_price_clothe_warehouse);
        final TextView textSize = dialog.findViewById(R.id.textView_size_clothe_warehouse);
        final TextView textColor = dialog.findViewById(R.id.textView_color_clothe_warehouse);
        final ImageView imageClothe = dialog.findViewById(R.id.imageView_view_clothe_warehouse);

        final Button buttonAddCart = dialog.findViewById(R.id.button_add_cart_warehouse);
        final Button buttonTakeOutClothe = dialog.findViewById(R.id.button_take_out_clothe_warehouse);

        if(clothe.getImagen() != null) {
            byte[] decodedBytes = Base64.decode(clothe.getImagen(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            imageClothe.setImageBitmap(bitmap);
        }

        textTitleName.setText(ClotheNameManager.setName(clothe, context));
        textTitleSize.setText(context.getString(R.string.textView_title_size_clothe));
        textTitleColor.setText(context.getString(R.string.textView_title_color_clothe));
        textTitlePrice.setText(R.string.textView_title_price_clothe);

        String sizeModified = String.valueOf(clothe.getTalla());
        if(sizeModified.equals("0")) {
            if(FirstWindow.getLanguage().equals("español")) {
                sizeModified = "Talla única";
            } else {
                sizeModified = "Unique size";
            }
        }

        textSize.setText(sizeModified);
        textColor.setText(ColorManager.searchColor(clothe.getColor(), context));
        textPrice.setText(String.valueOf(clothe.getPrecio()));

        buttonAddCart.setOnClickListener((v) -> {
            ListProductsAdapter adapter = ResumeShopWindow.getAdapter();
            adapter.addProducto(clothe);
            Toast.makeText(context, R.string.message_warehouse_cart, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        buttonTakeOutClothe.setOnClickListener((v) -> {
            Toast.makeText(context, R.string.message_warehouse_take_out, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogClotheResumeCart(Context context, ListProductsAdapter adapter, Producto clothe, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe_resume);

        final TextView textTitleName = dialog.findViewById(R.id.textView_title_name_clothe_cart);
        final TextView textTitleSize = dialog.findViewById(R.id.textView_title_size_clothe_cart);
        final TextView textTitleColor = dialog.findViewById(R.id.textView_title_color_clothe_cart);
        final TextView textTitlePrice = dialog.findViewById(R.id.textView_title_price_clothe_cart);

        final TextView textPrice = dialog.findViewById(R.id.textView_price_clothe_cart);
        final TextView textSize = dialog.findViewById(R.id.textView_size_clothe_cart);
        final TextView textColor = dialog.findViewById(R.id.textView_color_clothe_cart);
        final ImageView imageClothe = dialog.findViewById(R.id.imageView_view_clothe_cart);

        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe_cart);

        if(clothe.getImagen() != null) {
            byte[] decodedBytes = Base64.decode(clothe.getImagen(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            imageClothe.setImageBitmap(bitmap);
        }

        textTitleName.setText(ClotheNameManager.setName(clothe, context));
        textTitleSize.setText(context.getString(R.string.textView_title_size_clothe));
        textTitleColor.setText(context.getString(R.string.textView_title_color_clothe));
        textTitlePrice.setText(R.string.textView_title_price_clothe);

        String sizeModified = String.valueOf(clothe.getTalla());
        if(sizeModified.equals("0")) {
            if(FirstWindow.getLanguage().equals("español")) {
                sizeModified = "Talla única";
            } else {
                sizeModified = "Unique size";
            }
        }

        textSize.setText(sizeModified);
        textColor.setText(ColorManager.searchColor(clothe.getColor(), context));
        textPrice.setText(String.valueOf(clothe.getPrecio()));

        buttonDeleteClothe.setOnClickListener((v) -> {
            adapter.removeItem(clothe);
            PayWindow.setDiscountAmount(clothe.getPrecio());
            Toast.makeText(context, context.getString(R.string.message_delete_clothe), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogAdmin(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_admin);

        final EditText editTextFilter = dialog.findViewById(R.id.editText_filter_name);

        ListProductsShopAdapter adapter = new ListProductsShopAdapter(ProductsManager.getClothesAdmin(), new ListProductsShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto item) {
                openDialogClothesAdmin(item, context);
            }
        });

        adapterAdmin = adapter;
        setSearchView(editTextFilter);

        final RecyclerView recyclerView = dialog.findViewById(R.id.recyclerview_clothes_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        dialog.show();
    }

    private static void setSearchView(EditText editTextFilter) {
        editTextFilter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!s.toString().isEmpty()) {
                        FilterManager.setFilterReferenceAdmin(s.toString(), adapterAdmin);
                    }
                }
            });

    }

    private static void openDialogClothesAdmin(Producto producto, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothes_admin);

        final TextView textTitleName = dialog.findViewById(R.id.textView_title_name_clothe_admin);
        final TextView textTitleSize = dialog.findViewById(R.id.textView_title_size_clothe_admin);
        final TextView textTitleColor = dialog.findViewById(R.id.textView_title_color_clothe_admin);
        final TextView textTitlePrice = dialog.findViewById(R.id.textView_title_price_clothe_admin);

        final EditText editTextPrice = dialog.findViewById(R.id.editText_price_clothe_admin);
        final Spinner spinnerSizes = dialog.findViewById(R.id.spinner_size_admin);
        final TextView textColor = dialog.findViewById(R.id.textView_color_clothe_admin);
        final ImageView imageClothe = dialog.findViewById(R.id.imageView_view_clothe_admin);

        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe_admin);
        final Button buttonModifyClothe = dialog.findViewById(R.id.button_modify_clothe_admin);

        if(producto.getImagen() != null) {
            byte[] decodedBytes = Base64.decode(producto.getImagen(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            imageClothe.setImageBitmap(bitmap);
        }

        textTitleName.setText(ClotheNameManager.setName(producto, context));
        textTitleSize.setText(context.getString(R.string.textView_title_size_clothe));
        textTitleColor.setText(context.getString(R.string.textView_title_color_clothe));
        textTitlePrice.setText(R.string.textView_title_price_clothe);

        textColor.setText(ColorManager.searchColor(producto.getColor(), context));
        editTextPrice.setText(String.valueOf(producto.getPrecio()));

        String[] allSizes = context.getResources().getStringArray(R.array.attributes_sizes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, allSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSizes.setAdapter(adapter);

        spinnerSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(String.valueOf(parent.getSelectedItem()).equals(context.getString(R.string.textView_title_size_clothe))) {
                    Toast.makeText(context, "Debe insertar una talla", Toast.LENGTH_SHORT).show();
                } else if (String.valueOf(parent.getSelectedItem()).equals(context.getString(R.string.noSize))) {
                    producto.setTalla(0);
                } else {
                    producto.setTalla(Integer.valueOf((String) parent.getSelectedItem()));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonDeleteClothe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                adapterAdmin.removeItemAdmin(producto);
                ProductsManager.deleteClothe(producto);
            }
        });

        buttonModifyClothe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setPrecio(Double.parseDouble(editTextPrice.getText().toString()));
                dialog.dismiss();
                adapterAdmin.updateItemAdmin(producto);
                ProductsManager.updateProducto(producto);
            }
        });

        dialog.show();
    }

    public static void openDialogLogin(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_in);

        final EditText userEmailSignIn = dialog.findViewById(R.id.editText_email_sign_in);
        final EditText passwordSignUp = dialog.findViewById(R.id.editText_password_sign_in);
        final Button buttonSignIn = dialog.findViewById(R.id.button_sign_in);
        final TextView textViewSignUp = dialog.findViewById(R.id.textView_sign_up);

        setSpannableString(textViewSignUp, context);

        buttonSignIn.setOnClickListener((v) -> {
            if(!ValidatorManager.passValidationContent(userEmailSignIn, context)
                    || !ValidatorManager.passValidationContent(passwordSignUp, context)) {
                openDialogError(context, context.getResources().getString(R.string.message_error_empty_field));
            } else {
                LoginManager.accreditAccount(userEmailSignIn.getText().toString(), passwordSignUp.getText().toString(), context);
                dialog.dismiss();
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
}
