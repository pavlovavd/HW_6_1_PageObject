package ru.netology.test;


import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void transferToTheFirstCard() {
        Configuration.holdBrowserOpen = true;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();
        int amount = 2000;

        int expectedBalanceFistCard = cardInfoOne.getBalance() + amount;
        int expectedBalanceSecondCard = cardInfoTwo.getBalance() - amount;
        int actualBalanceCardOne = DataHelper.getCardNumber1().getBalance();
        int actualBalanceCardTwo = DataHelper.getCardNumber2().getBalance();

        var moneyTransferPage = new TransferPage();
        moneyTransferPage.cardSelection(cardInfoOne.getIdCard());
        moneyTransferPage.transfer(2000, cardInfoTwo.getNumber());

        assertEquals(expectedBalanceFistCard, actualBalanceCardOne);
        assertEquals(expectedBalanceSecondCard, actualBalanceCardTwo);
    }

    @Test
    void transferAmountZero() {
        Configuration.holdBrowserOpen = true;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();
        int amount = 0;

        int expectedBalanceFistCard = cardInfoOne.getBalance() + amount;
        int expectedBalanceSecondCard = cardInfoTwo.getBalance() - amount;
        int actualBalanceCardOne = DataHelper.getCardNumber1().getBalance();
        int actualBalanceCardTwo = DataHelper.getCardNumber2().getBalance();

        var moneyTransferPage = new TransferPage();
        moneyTransferPage.cardSelection(cardInfoOne.getIdCard());
        moneyTransferPage.transfer(0, cardInfoTwo.getNumber());

        assertEquals(expectedBalanceFistCard, actualBalanceCardOne);
        assertEquals(expectedBalanceSecondCard, actualBalanceCardTwo);
    }

    @Test
    void transferWhenTheAmountMoreBalance() {
        Configuration.holdBrowserOpen = true;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();
        int amount = 20000;

        int expectedBalanceFistCard = cardInfoOne.getBalance() + amount;
        int expectedBalanceSecondCard = cardInfoTwo.getBalance() - amount;
        int actualBalanceCardOne = DataHelper.getCardNumber1().getBalance();
        int actualBalanceCardTwo = DataHelper.getCardNumber2().getBalance();

        var moneyTransferPage = new TransferPage();
        moneyTransferPage.cardSelection(cardInfoOne.getIdCard());
        moneyTransferPage.transfer(20000, cardInfoTwo.getNumber());

        assertEquals(expectedBalanceFistCard, actualBalanceCardOne);
        assertEquals(expectedBalanceSecondCard, actualBalanceCardTwo);
    }
}