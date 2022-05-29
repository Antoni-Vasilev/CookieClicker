package com.example.cookieclickerv2.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cookieclickerv2.Converter.CoinConverter;
import com.example.cookieclickerv2.Converter.MoneyConverter;
import com.example.cookieclickerv2.R;
import com.example.cookieclickerv2.Storage.Storage;
import com.google.android.material.slider.Slider;

import java.math.BigDecimal;

public class TicketsToMoneyActivity extends AppCompatActivity {

    Slider ticketSlider;
    TextView ticketView;
    Button convert;

    Storage storage;
    long sliderValue;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_to_money);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        ticketSlider = findViewById(R.id.ticketSlider);
        ticketView = findViewById(R.id.ticketView);
        convert = findViewById(R.id.convert);

        storage = new Storage(this);

        ticketSlider.setValueTo(Long.parseLong(storage.getCoin()));
        ticketSlider.setValue(Long.parseLong(storage.getCoin()) / 2);
        calculateTickets(Long.parseLong(storage.getCoin()) / 2);

        ticketSlider.addOnChangeListener((slider, value, fromUser) -> calculateTickets((long) ticketSlider.getValue()));

        convert.setOnClickListener(view -> Convert());
    }

    private void Convert() {
        storage.removeCoin(String.valueOf(sliderValue));
        storage.addMoney(calculateTickets(sliderValue));
        super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    private String calculateTickets(long value) {
        BigDecimal Money = new BigDecimal(storage.getMoney());
        for (int i = 0; i < value; i++) {
            Money = Money.multiply(BigDecimal.valueOf(5));
            Money = Money.multiply(BigDecimal.valueOf(.4));
        }
        Money = value == 0 ? BigDecimal.valueOf(0) : Money;
        ticketView.setText(new CoinConverter(String.valueOf(value)).getAllCoin() + "\n=\n" + new MoneyConverter(Money.toString()).getAllMoney());

        sliderValue = value;

        return Money.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}