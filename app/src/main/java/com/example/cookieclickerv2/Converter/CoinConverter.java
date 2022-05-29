package com.example.cookieclickerv2.Converter;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoinConverter {

    BigDecimal coin;

    List<String> coinType;

    public CoinConverter(String money) {
        this.coin = new BigDecimal(money);
    }

    @SuppressLint("DefaultLocale")
    public String getAllCoin() {
        setCoinType();

        byte select = 0;

        while (coin.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            coin = coin.divide(BigDecimal.valueOf(1000));
            select++;
        }

        try {
            return "🎟️" + String.format("%.2f", coin) + coinType.get(select);
        } catch (Exception e) {
            e.printStackTrace();
            return "🎟️∞";
        }
    }

    private void setCoinType() {
        coinType = new ArrayList<>();

        coinType.add("");
        coinType.add("K");
        coinType.add("M");
        coinType.add("B");
        coinType.add("T");
        coinType.add("Q");
//        moneyType.add(" Quintillions");
//        moneyType.add(" Sextillions");
//        moneyType.add(" Septillions");
//        moneyType.add(" Octillions");
//        moneyType.add(" Nonillions");
//        moneyType.add(" Decillions");
//        moneyType.add(" Vigintillions");
//        moneyType.add(" Trigintillions");
//        moneyType.add(" Quadringentillions");
//        moneyType.add(" Quinquagintillions");
//        moneyType.add(" Sexagintillions");
//        moneyType.add(" Septuagintillions");
//        moneyType.add(" Octogintillions");
//        moneyType.add(" Nonagintillions");
//        moneyType.add(" Centillions");
//        moneyType.add(" Trecentillions");
//        moneyType.add(" Quadringentillions");
//        moneyType.add(" Quingentillions");
//        moneyType.add(" Septingentillions");
//        moneyType.add(" Septingentillions");
//        moneyType.add(" Sescentillions");
//        moneyType.add(" Septingentillions");
//        moneyType.add(" Octingentillions");
//        moneyType.add(" Nongentillions");
//        moneyType.add(" Tremillinillions");
//        moneyType.add(" Quattuormillinillions");
//        moneyType.add(" Quinmillinillions");
//        moneyType.add(" Sexmillionillions");
//        moneyType.add(" Septenmillinillions");
//        moneyType.add(" Octomillinillions");
//        moneyType.add(" Nomillinillions");

        String[] alphabet = {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
        };

        for (int first = 0; first < 2; first++) {
            for (String second : alphabet) {
                coinType.add(alphabet[first] + second);
            }
        }
    }
}
