package com.example.cookieclickerv2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.cookieclickerv2.Activity.TicketsToMoneyActivity;
import com.example.cookieclickerv2.Activity.PromoCodeActivity;
import com.example.cookieclickerv2.R;

public class ShopFragment extends Fragment {

    View view;

    Button showTicketsToMoney, showPromoCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);

        showTicketsToMoney = view.findViewById(R.id.showTicketsToMoney);
        showPromoCode = view.findViewById(R.id.showPromoCode);

        showTicketsToMoney.setOnClickListener(view -> showTicketsToMoney());
        showPromoCode.setOnClickListener(view -> showPromoCode());

        return view;
    }

    private void showPromoCode() {
        Intent intent = new Intent(view.getContext(), PromoCodeActivity.class);
        view.getContext().startActivity(intent);
    }

    private void showTicketsToMoney() {
        Intent intent = new Intent(view.getContext(), TicketsToMoneyActivity.class);
        view.getContext().startActivity(intent);
    }
}