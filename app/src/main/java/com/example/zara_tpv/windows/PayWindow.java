package com.example.zara_tpv.windows;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.zara_tpv.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PayWindow extends AppCompatActivity {

//    private CardForm cardForm;
//    private Button buttonPay;
//    AlertDialog.Builder alerBuilder;

    EditText editAmount;
    Button buttonPay;

    String client = "ASXBae5jVymPA6q5_8xOKaEEnRKM_zGqnRMrNxAK4HQcXePp0s6CH-koC3zCgInskFJLcXfqDWcon5hk";

    int PAYPAL_REQUEST_CODE = 123;

    public static PayPalConfiguration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_window);

        editAmount = findViewById(R.id.editText_amount_pay);
        buttonPay = findViewById(R.id.button_pay_paypal);

        configuration = new PayPalConfiguration()
                        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                        .clientId(client);

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPayment();
            }
        });
//
//        cardForm = findViewById(R.id.cardform_credit_card);
//        buttonPay = findViewById(R.id.button_pay_cart);
//
//        cardForm.cardRequired(true)
//                .expirationRequired(true)
//                .cvvRequired(true)
//                .postalCodeRequired(true)
//                .mobileNumberRequired(true)
//                .mobileNumberExplanation("SMS is required on this number")
//                .setup(PayWindow.this);
//        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//        buttonPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(cardForm.isValid()) {
//                    alerBuilder = new AlertDialog.Builder(PayWindow.this);
//                    alerBuilder.setTitle("Confirm before purchase");
//                    alerBuilder.setMessage("Card number" + cardForm.getCardNumber() + "\n"
//                        + "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n"
//                        + "Card CVV: " + cardForm.getCvv() + "\n"
//                        + "Postal number: " + cardForm.getPostalCode() + "\n"
//                        + "Phone number: " + cardForm.getMobileNumber());
//                    alerBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            Toast.makeText(PayWindow.this, "Confirmed purchase", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    alerBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    AlertDialog alertDialog = alerBuilder.create();
//                    alertDialog.show();
//                } else {
//                    Toast.makeText(PayWindow.this, "Please complete the form", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void getPayment() {
        String amount = editAmount.getText().toString();
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD", "Code with Arvind", PayPalPayment.PAYMENT_INTENT_SALE);
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
}