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
        var dashboardPage= verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();

        int amount = 2000;
        int expectedBalanceFistCard = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0") - amount;
        int expectedBalanceSecondCard = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d") + amount;

        var transferPage = dashboardPage.cartOneDeposit();
        transferPage.transfer(amount, DataHelper.getCardNumber1());

        int actualBalanceCardOne = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        int actualBalanceCardTwo = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");

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
        var dashboardPage= verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();
        var transferPage = dashboardPage.cartTwoDeposit();
        int amount = 0;
        transferPage.transfer(amount, DataHelper.getCardNumber2());
        transferPage.zeroTransfer();

    }

    @Test
    void transferWhenTheAmountMoreBalance() {
        Configuration.holdBrowserOpen = true;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage=  verificationPage.validVerify(verificationCode);
        var cardInfoOne = DataHelper.getCardNumber1();
        var cardInfoTwo = DataHelper.getCardNumber2();

        var transferPage = dashboardPage.cartTwoDeposit();
        int amount = 20000;
        transferPage.transfer(amount, DataHelper.getCardNumber1());
        transferPage.failTransfer();




    }
}