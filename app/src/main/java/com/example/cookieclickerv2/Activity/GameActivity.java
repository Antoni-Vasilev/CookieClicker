package com.example.cookieclickerv2.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cookieclickerv2.Adapter.BottomMenuAdapter;
import com.example.cookieclickerv2.BuildConfig;
import com.example.cookieclickerv2.Converter.CoinConverter;
import com.example.cookieclickerv2.Converter.MoneyConverter;
import com.example.cookieclickerv2.Fragment.ShopFragment;
import com.example.cookieclickerv2.Fragment.UpgradeFragment;
import com.example.cookieclickerv2.Model.SystemUpdate;
import com.example.cookieclickerv2.R;
import com.example.cookieclickerv2.Storage.Storage;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameActivity extends AppCompatActivity {

    TextView mView, coinView, addMoneyView;
    ImageView clickImage, systemUpdate;
    TabLayout bottomMenu;
    ViewPager viewPager;

    Storage storage;
    BottomMenuAdapter bottomMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(null);

        // Set View
        mView = findViewById(R.id.mView);
        coinView = findViewById(R.id.coinView);
        addMoneyView = findViewById(R.id.addMoneyView);
        clickImage = findViewById(R.id.clickImage);
        systemUpdate = findViewById(R.id.systemUpdate);
        bottomMenu = findViewById(R.id.bottomMenu);
        viewPager = findViewById(R.id.viewPager);

        storage = new Storage(this);
        bottomMenuAdapter = new BottomMenuAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        // Set Function
        updateMoneyView();
        updateCoinView();
        setAddMoneyView();
        setBottomMenu();
        setViewPager();
        setSystemUpdate();
        clickImage.setOnClickListener(view -> clickImage());
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateAllScreen();
    }

    // Functions
    @SuppressLint("SetTextI18n")
    private void updateMoneyView() {
        this.mView.setText(new MoneyConverter(storage.getMoney()).getAllMoney());
    }

    private void updateCoinView() {
        coinView.setText(new CoinConverter(storage.getCoin()).getAllCoin());
    }

    private void clickImage() {
        // Set clickImage scale down
        clickImage.animate().scaleX(.8f).setDuration(30);
        clickImage.animate().scaleY(.8f).setDuration(30);

        // Set clickImage scale up
        Runnable runnable = () -> {
            storage.setMoney();
            updateMoneyView();

            clickImage.animate().scaleX(1f).setDuration(140);
            clickImage.animate().scaleY(1f).setDuration(140);
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 40);
    }

    private void setBottomMenu() {
        bottomMenu.setTabTextColors(Color.WHITE, Color.WHITE);
        bottomMenu.setSelectedTabIndicatorColor(Color.WHITE);
        bottomMenu.setupWithViewPager(viewPager);
    }

    private void setViewPager() {
        bottomMenuAdapter.addFragment(new UpgradeFragment(), getString(R.string.Upgrade));
        bottomMenuAdapter.addFragment(new ShopFragment(), getString(R.string.Shop));
        viewPager.setAdapter(bottomMenuAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void setAddMoneyView() {
        addMoneyView.setText(new MoneyConverter(storage.getAddMoney()).getAllMoney());
    }

    public void updateAllScreen() {
        updateMoneyView();
        updateCoinView();
        setAddMoneyView();
    }

    private void setSystemUpdate() {
        systemUpdate.animate().x(30).setDuration(0);
        systemUpdate.animate().xBy(-120).setDuration(0);
        FirebaseDatabase.getInstance().getReference("System Update")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SystemUpdate systemUpdateValue = snapshot.getValue(SystemUpdate.class);
                        if (systemUpdateValue != null && systemUpdateValue.getVersionCode() > BuildConfig.VERSION_CODE) {
                            systemUpdate.animate().x(30).setDuration(300);

                            systemUpdate.setOnClickListener(view -> {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(systemUpdateValue.getURL()));
                                startActivity(browserIntent);
                            });
                        } else {
                            systemUpdate.animate().x(-120).setDuration(300);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share) {
            FirebaseDatabase.getInstance().getReference("System Update")
                    .child("URL")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getValue() == null) return;
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            String sub = snapshot.getValue().toString();
                            share.putExtra(Intent.EXTRA_TEXT, sub);
                            startActivity(Intent.createChooser(share, "Share Cookie Clicker"));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }

        return super.onOptionsItemSelected(item);
    }
}