package com.example.zara_tpv.windows;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.zara_tpv.R;

public class PayWindow extends AppCompatActivity {

    private CardForm cardForm;
    private Button buttonPay;
    AlertDialog.Builder alerBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_window);

        cardForm = findViewById(R.id.cardform_credit_card);
        buttonPay = findViewById(R.id.button_pay_cart);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(PayWindow.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardForm.isValid()) {
                    alerBuilder = new AlertDialog.Builder(PayWindow.this);
                    alerBuilder.setTitle("Confirm before purchase");
                    alerBuilder.setMessage("Card number" + cardForm.getCardNumber() + "\n"
                        + "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n"
                        + "Card CVV: " + cardForm.getCvv() + "\n"
                        + "Postal number: " + cardForm.getPostalCode() + "\n"
                        + "Phone number: " + cardForm.getMobileNumber());
                    alerBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(PayWindow.this, "Confirmed purchase", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alerBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alerBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(PayWindow.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}