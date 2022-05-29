package com.example.cookieclickerv2.Converter;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyConverter {

    BigDecimal money;

    List<String> moneyType;

    public MoneyConverter(String money) {
        this.money = new BigDecimal(money);
    }

    @SuppressLint("DefaultLocale")
    public String getAllMoney() {
        setMoneyType();

        byte select = 0;

        while (money.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            money = money.divide(BigDecimal.valueOf(1000));
            select++;
        }

        try {
            return "\uD83D\uDCB5" + String.format("%.2f", money).replace(".00", "") + moneyType.get(select);
        } catch (Exception e) {
            e.printStackTrace();
            return "\uD83D\uDCB5∞";
        }
    }

    private void setMoneyType() {
        moneyType = new ArrayList<>();

        moneyType.add("");
        moneyType.add("K");
        moneyType.add("M");
        moneyType.add("B");
        moneyType.add("T");
        moneyType.add("Q");
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

        for (int first = 0; first < 7; first++) {
            for (String second : alphabet) {
                moneyType.add(alphabet[first] + second);
            }
        }
    }
}
