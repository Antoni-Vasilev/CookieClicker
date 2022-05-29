package com.example.cookieclickerv2.Storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class Storage {

    final Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DecimalFormat decimalFormat;

    public Storage(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("Game", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        decimalFormat = new DecimalFormat("#0.00");
    }

    public boolean getIsFirstOpen() {
        return sharedPreferences.getBoolean("isFirstOpen", true);
    }

    public void setOpenApp() {
        editor.putBoolean("isFirstOpen", false);
        editor.apply();
    }

    public String generateUrl() {
        String key = sharedPreferences.getString("key", UUID.randomUUID().toString());
        editor.putString("key", key);
        editor.apply();
        return key;
    }

    // Money
    public String getMoney() {
        return sharedPreferences.getString("Money", "0");
    }

    @SuppressLint("DefaultLocale")
    public void setMoney() {
        BigDecimal AddMoney = new BigDecimal(getAddMoney());
        BigDecimal Money = new BigDecimal(getMoney());

        Money = Money.add(AddMoney);

        editor.putString("Money", String.valueOf(Money.setScale(2, RoundingMode.UP)));
        editor.apply();
    }

    public void addMoney(String Add) {
        BigDecimal AddMoney = new BigDecimal(Add);
        BigDecimal Money = new BigDecimal(getMoney());

        Money = Money.add(AddMoney);

        editor.putString("Money", String.valueOf(Money.setScale(2, RoundingMode.UP)));
        editor.apply();
    }

    public void removeMoney(String money) {
        BigDecimal Money = new BigDecimal(getMoney());
        BigDecimal RemoveMoney = new BigDecimal(money);

        Money = Money.subtract(RemoveMoney);

        editor.putString("Money", String.valueOf(Money.setScale(2, RoundingMode.UP)));
        editor.apply();
    }

    public String getAddMoney() {
        return sharedPreferences.getString("AddMoney", "1");
    }

    public void setAddMoney(String addMoney) {
        editor.putString("AddMoney", String.valueOf(new BigDecimal(getAddMoney()).add(new BigDecimal(addMoney)).setScale(2, RoundingMode.UP)));
        editor.apply();
    }

    // Coin
    public String getCoin() {
        return sharedPreferences.getString("Coin", "5");
    }

    public void addCoin(String add) {
        BigDecimal Coin = new BigDecimal(getCoin());
        BigDecimal AddCoin = new BigDecimal(add);

        Coin = Coin.add(AddCoin);

        editor.putString("Coin", Coin.toString());
        editor.apply();
    }

    public void removeCoin(String value) {
        BigDecimal Coin = new BigDecimal(getCoin());
        BigDecimal RemoveCoin = new BigDecimal(value);
        Coin = Coin.subtract(RemoveCoin);

        editor.putString("Coin", Coin.toString());
        editor.apply();
    }

    // Item
    public int getItemLevel(String id, boolean isFirst) {
        return sharedPreferences.getInt(id + "-level", isFirst ? 1 : 0);
    }

    public void setItemLevel(String id, boolean isFirst) {
        editor.putInt(id + "-level", getItemLevel(id, isFirst) + 1);
        editor.apply();
    }

    public String getItemPrice(String id, String startPrice) {
        return sharedPreferences.getString(id + "-price", startPrice);
    }

    public void setItemPrice(String id, String startPrice) {
        // startPrice + startPrice * 25%
        float random = new Random().nextInt(40);
        random /= 100;
        BigDecimal Price = new BigDecimal(getItemPrice(id, startPrice)).add(new BigDecimal(getItemPrice(id, startPrice)).multiply(new BigDecimal(random)));
        editor.putString(id + "-price", String.valueOf(Price.setScale(2, RoundingMode.UP)));
        editor.apply();
    }

    public String getItemAddMoney(String id, String addMoney) {
        return sharedPreferences.getString(id + "-addMoney", addMoney);
    }

    public void setItemAddMoney(String id, String addMoney) {
        BigDecimal AddMoney = new BigDecimal(getItemAddMoney(id, addMoney));
        float random = new Random().nextInt(25);
        random = random < 5 ? 5 : random;
        AddMoney = AddMoney.add(AddMoney.multiply(new BigDecimal(random / 100)));
        editor.putString(id + "-addMoney", String.valueOf(AddMoney.setScale(2, RoundingMode.UP)));
    }

    public void setItemAddMoneyBonus(String id, String addMoney) {
        BigDecimal AddMoney = new BigDecimal(getItemAddMoney(id, addMoney));
        if (getItemLevel(id, false) <= 100) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(2));
        } else if (getItemLevel(id, false) <= 500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(6));
        } else if (getItemLevel(id, false) <= 1000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(10));
        } else if (getItemLevel(id, false) <= 1500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(14));
        } else if (getItemLevel(id, false) <= 2000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(18));
        } else if (getItemLevel(id, false) <= 2500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(22));
        } else if (getItemLevel(id, false) <= 3000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(26));
        } else if (getItemLevel(id, false) <= 3500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(30));
        } else if (getItemLevel(id, false) <= 4000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(34));
        } else if (getItemLevel(id, false) <= 4500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(38));
        } else if (getItemLevel(id, false) <= 5000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(42));
        } else if (getItemLevel(id, false) <= 5500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(46));
        } else if (getItemLevel(id, false) <= 6000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(50));
        } else if (getItemLevel(id, false) <= 6500) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(54));
        } else if (getItemLevel(id, false) <= 7000) {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(58));
        } else {
            AddMoney = AddMoney.multiply(BigDecimal.valueOf(68));
        }
        editor.putString(id + "-addMoney", String.valueOf(AddMoney.setScale(2, RoundingMode.UP)));
        editor.apply();

        if (getItemLevel(id, false) > 100) {
            addCoin("1");
        }

        setItemAddMoney(id, addMoney);
    }
}
