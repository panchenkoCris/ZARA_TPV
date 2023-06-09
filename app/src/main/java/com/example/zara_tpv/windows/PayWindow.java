package com.example.zara_tpv.windows;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.DiscountAdapter;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.manager.CuentaManager;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.manager.TicketManager;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Producto;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PayWindow extends AppCompatActivity {

    private static EditText editAmount;
    private Button buttonPay;
    private TextView amountDiscounts;
    private TextView percentTax;
    private ListProductsAdapter adapter;
    private DiscountAdapter discountAdapter;
    private static List<Producto> productoList;
    private static List<Discount> discountList;
    private static double totalDiscounts = 0.0;
    private static double totalAmount;

    private String client = "ASXBae5jVymPA6q5_8xOKaEEnRKM_zGqnRMrNxAK4HQcXePp0s6CH-koC3zCgInskFJLcXfqDWcon5hk";
    private final static int PAYPAL_REQUEST_CODE = 123;
    private static final double PERCENT_TAX = 0.21;

    public static PayPalConfiguration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_window);

        totalAmount = 0.0;

        productoList = ListProductsAdapter.getProductos();
        if(discountList == null) {
            discountList = new ArrayList<>();
        }

        setToolbar((Toolbar) findViewById(R.id.toolbar_menu));
        percentTax = findViewById(R.id.textView_total_percent_tax);
        amountDiscounts = findViewById(R.id.textView_total_percent_discounts);
        editAmount = findViewById(R.id.editText_amount_pay);
        editAmount.setFocusable(false);
        setAdapters();
        setRecyclerViews((RecyclerView) findViewById(R.id.recyclerView_clothes_final_pay),
                (RecyclerView) findViewById(R.id.recyclerview_discounts_user));
        buttonPay = findViewById(R.id.button_final_pay);

        percentTax.setText(PERCENT_TAX*100+"%");
        setValueAmount();

        configuration = new PayPalConfiguration()
                        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                        .clientId(client);

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicketManager.updateTicket();
                getPayment();
            }
        });
    }

    private void setAmountDiscounts(double cantidad_descuento) {
        totalDiscounts = totalDiscounts + cantidad_descuento;
        amountDiscounts.setText((totalDiscounts*100)+"%");
    }

    private static void setValueAmount() {
        for (Producto p : productoList) {
            totalAmount += p.getPrecio();
        }

        totalAmount = totalAmount+(totalAmount*PERCENT_TAX);
        totalAmount = totalAmount-(totalAmount*totalDiscounts);

        editAmount.setText(String.format("%.2f", totalAmount));
    }

    public static void setDiscountAmount(double totalDiscount) {
        totalAmount -= totalDiscount;
        editAmount.setText(String.format("%.2f", totalAmount));
    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.title_final_shop);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_menu);
        toolbar.setOverflowIcon(drawable);
        toolbar.setContentInsetsAbsolute(40,0);
        setSupportActionBar(toolbar);
    }

    private void setRecyclerViews(RecyclerView recyclerViewProduct, RecyclerView recyclerViewDiscount) {
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProduct.setAdapter(adapter);

        recyclerViewDiscount.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDiscount.setAdapter(discountAdapter);
    }

    private void setAdapters() {
        adapter =  new ListProductsAdapter(productoList,  new ListProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto clothe) {
                DialogManager.openDialogClotheResumeCart(PayWindow.this, adapter, clothe, productoList.indexOf(clothe));
            }
        });

        discountAdapter = new DiscountAdapter(discountList, new DiscountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Discount item) {
                CuentaManager.actualizarDescuentoUsuario(item, PayWindow.this);
                setAmountDiscounts(item.getCantidad_descuento());
                discountAdapter.removeItem(item);
                setValueAmount();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_options_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dresser:
                startActivity(new Intent(this, ShopWindow.class));
                break;
            case R.id.action_account:
                DialogManager.openDialogLogin(this);
                break;
            case R.id.action_shopping:
                Toast.makeText(this, this.getString(R.string.already_purchase), Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void getPayment() {
        String amount = editAmount.getText().toString();
        BigDecimal result = new BigDecimal(amount.replaceAll(",", "."));
        PayPalPayment payment = new PayPalPayment(result, "EUR", "Total a pagar", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYPAL_REQUEST_CODE) {
            PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

            if(paymentConfirmation != null) {
                try {
                    String paymentDetails = paymentConfirmation.toJSONObject().toString();
                    JSONObject jsonObject = new JSONObject(paymentDetails);

                } catch (JSONException e) {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if(requestCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else if(requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid payment", Toast.LENGTH_SHORT).show();
        }
    }

    public static void setTotalDiscounts(double totalDiscountsParameter) {
        totalDiscounts += totalDiscountsParameter;
    }

    public static void setDiscountList(List<Discount> listDiscount) {
       discountList = listDiscount;
    }
}