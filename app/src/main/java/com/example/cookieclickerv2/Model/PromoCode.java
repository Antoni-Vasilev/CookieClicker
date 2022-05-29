package com.example.cookieclickerv2.Model;

public class PromoCode {

    private final String Money;
    private final String Ticket;

    public PromoCode() {
        Money = "0";
        Ticket = "0";
    }

    public PromoCode(String money, String ticket) {
        Money = money;
        Ticket = ticket;
    }

    public String getMoney() {
        return Money;
    }

    public String getTicket() {
        return Ticket;
    }
}
