package com.example.cookieclickerv2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookieclickerv2.Activity.GameActivity;
import com.example.cookieclickerv2.Adapter.UpgradeAdapter;
import com.example.cookieclickerv2.Model.Upgrade;
import com.example.cookieclickerv2.R;

import java.util.ArrayList;

public class UpgradeFragment extends Fragment {

    RecyclerView recyclerView;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upgrade, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UpgradeAdapter upgradeAdapter = new UpgradeAdapter(makeUpgradeList(), view.getContext(), (GameActivity) getActivity());
        recyclerView.setAdapter(upgradeAdapter);
    }

    private ArrayList<Upgrade> makeUpgradeList() {
        ArrayList<Upgrade> upgradeArrayList = new ArrayList<>();

        upgradeArrayList.add(new Upgrade(R.drawable.stand, "Stand", "f082c5c4-750e-4fda-b47e-734798a38237", 5000, "20", "1", "0.2", true));
        upgradeArrayList.add(new Upgrade(R.drawable.mini_market, "Mini Market", "e8c79b36-6c72-4aa9-9dba-191e420bda31", 5000, "400", "10", "0.7", false));
        upgradeArrayList.add(new Upgrade(R.drawable.shop, "Shop", "641f56b8-bec9-44e7-92a0-aa173f5887e6", 5000, "2500", "40", "5", false));
        upgradeArrayList.add(new Upgrade(R.drawable.super_market, "Super Market", "bd663fce-802c-47fd-86af-33112d8cb35d", 5000, "15000", "100", "10", false));
        upgradeArrayList.add(new Upgrade(R.drawable.city, "City", "1b9ebc50-206e-4ca3-ae08-8eb86db204f2", 5000, "100000", "1000", "70", false));
        upgradeArrayList.add(new Upgrade(R.drawable.country, "Country", "48b58a61-1564-443e-b37c-617d8195ae8b", 5000, "500000", "4000", "500", false));
        upgradeArrayList.add(new Upgrade(R.drawable.moon, "Moon", "a6de835e-8da1-4f82-aa89-2bb3c8248dca", 5000, "1000000", "10000", "900", false));
        upgradeArrayList.add(new Upgrade(R.drawable.mars, "Mars", "c58d5333-564c-4fd8-8cf0-7c711c368597", 5000, "5000000", "35000", "2000", false));
        upgradeArrayList.add(new Upgrade(R.drawable.jupiter, "Jupiter", "2fbf2828-7451-41db-8fa6-0c51dc0cf1aa", 5000, "100000000", "100000", "10000", false));
        upgradeArrayList.add(new Upgrade(R.drawable.saturn, "Saturn", "42ea26b8-aad4-40b0-9de3-255e59d6bf05", 5000, "10000000000", "1000000", "80000", false));
        upgradeArrayList.add(new Upgrade(R.drawable.solar_system, "Solar System", "5f53bf60-2b4b-49ab-96bb-b240d5effe60", 5000, "1000000000000", "20000000", "600000", false));

        return upgradeArrayList;
    }

//        upgradeArrayList.add(new Upgrade(R.drawable.stand,        "Stand",        "f082c5c4-750e-4fda-b47e-734798a38237", 10000, "20",            "1",        "0.2",    true));
//        upgradeArrayList.add(new Upgrade(R.drawable.mini_market,  "Mini Market",  "e8c79b36-6c72-4aa9-9dba-191e420bda31", 15000, "400",           "10",       "0.7",    false));
//        upgradeArrayList.add(new Upgrade(R.drawable.shop,         "Shop",         "641f56b8-bec9-44e7-92a0-aa173f5887e6", 10000, "2500",          "40",       "5",      false));
//        upgradeArrayList.add(new Upgrade(R.drawable.super_market, "Super Market", "bd663fce-802c-47fd-86af-33112d8cb35d", 10000, "15000",         "100",      "10",     false));
//        upgradeArrayList.add(new Upgrade(R.drawable.city,         "City",         "1b9ebc50-206e-4ca3-ae08-8eb86db204f2", 20000, "100000",        "1000",     "70",     false));
//        upgradeArrayList.add(new Upgrade(R.drawable.country,      "Country",      "48b58a61-1564-443e-b37c-617d8195ae8b", 15000, "500000",        "4000",     "500",    false));
//        upgradeArrayList.add(new Upgrade(R.drawable.moon,         "Moon",         "a6de835e-8da1-4f82-aa89-2bb3c8248dca", 12500, "1000000",       "10000",    "900",    false));
//        upgradeArrayList.add(new Upgrade(R.drawable.mars,         "Mars",         "c58d5333-564c-4fd8-8cf0-7c711c368597", 17500, "5000000",       "35000",    "2000",   false));
//        upgradeArrayList.add(new Upgrade(R.drawable.jupiter,      "Jupiter",      "2fbf2828-7451-41db-8fa6-0c51dc0cf1aa", 10000, "100000000",     "100000",   "10000",  false));
//        upgradeArrayList.add(new Upgrade(R.drawable.saturn,       "Saturn",       "42ea26b8-aad4-40b0-9de3-255e59d6bf05", 20000, "10000000000",   "1000000",  "80000",  false));
//        upgradeArrayList.add(new Upgrade(R.drawable.solar_system, "Solar System", "5f53bf60-2b4b-49ab-96bb-b240d5effe60", 15000, "1000000000000", "20000000", "600000", false));
}