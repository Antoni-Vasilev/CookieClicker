package com.example.cookieclickerv2.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cookieclickerv2.Converter.CoinConverter;
import com.example.cookieclickerv2.Converter.MoneyConverter;
import com.example.cookieclickerv2.Model.PromoCode;
import com.example.cookieclickerv2.R;
import com.example.cookieclickerv2.Storage.Storage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PromoCodeActivity extends AppCompatActivity {

    TextInputLayout promoCodeField;
    TextView moneyView, ticketView;
    Button viewButton, useButton;

    Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_code);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(getString(R.string.Promo_Code));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        promoCodeField = findViewById(R.id.promoCodeField);
        moneyView = findViewById(R.id.moneyView);
        ticketView = findViewById(R.id.ticketView);
        viewButton = findViewById(R.id.viewButton);
        useButton = findViewById(R.id.useButton);

        storage = new Storage(this);

        viewButton.setOnClickListener(view -> viewPromoCode());
        useButton.setOnClickListener(view -> usePromoCode());
    }

    private void viewPromoCode() {
        FirebaseDatabase.getInstance().getReference("PromoCode")
                .child(getPromoCode())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PromoCode promoCode = snapshot.getValue(PromoCode.class);

                        if (promoCode != null) {
                            moneyView.setText(String.format("Money: %s", new MoneyConverter(promoCode.getMoney()).getAllMoney()));
                            ticketView.setText(String.format("Tickets: %s", new CoinConverter(promoCode.getTicket()).getAllCoin()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void usePromoCode() {
        FirebaseDatabase.getInstance().getReference("PromoCode")
                .child(getPromoCode())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PromoCode promoCode = snapshot.getValue(PromoCode.class);

                        if (promoCode != null) {
                            moneyView.setText(String.format("Money: %s", new MoneyConverter(promoCode.getMoney()).getAllMoney()));
                            ticketView.setText(String.format("Tickets: %s", new CoinConverter(promoCode.getTicket()).getAllCoin()));

                            storage.addMoney(promoCode.getMoney());
                            storage.addCoin(promoCode.getTicket());

                            if (!getPromoCode().equals("anvv05")) {
                                FirebaseDatabase.getInstance().getReference("PromoCode")
                                        .child(getPromoCode()).removeValue();
                            }

                            PromoCodeActivity.super.onBackPressed();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private String getPromoCode() {
        if (promoCodeField.getEditText() == null) return "";
        return promoCodeField.getEditText().getText().toString().trim();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}