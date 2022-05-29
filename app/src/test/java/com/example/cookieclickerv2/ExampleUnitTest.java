package com.example.cookieclickerv2;

import static org.junit.Assert.assertEquals;

import com.example.cookieclickerv2.Converter.CoinConverter;
import com.example.cookieclickerv2.Converter.MoneyConverter;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testValidateMoneyConverter() {
        assertEquals("💵100K", new MoneyConverter("100000").getAllMoney());
        assertEquals("💵100.20K", new MoneyConverter("100200").getAllMoney());
    }

    @Test
    public void testValidateCoinConverter() {
        assertEquals("🎟️100K", new CoinConverter("100000").getAllCoin());
        assertEquals("🎟️100.20K", new CoinConverter("100200").getAllCoin());
    }
}