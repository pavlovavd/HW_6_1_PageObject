package ru.netology.test;


import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        Configuration.holdBrowserOpen = true;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var balanceCardOne = dashboardPage.getFirstCardBalance();
        var balanceCardTwo = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.cardTwoDeposit();
        int amount = 2000;
        transferPage.transfer(amount, DataHelper.getCardNumber1());

//        var expectedBalanceCardOne = balanceCardOne;
//        var expectedBalanceCardTwo = balanceCardTwo;
//        var actualBalanceCardOne = dashboardPage.getFirstCardBalance();
//        var actualBalanceCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals((balanceCardOne - amount), dashboardPage.getFirstCardBalance());
        assertEquals((balanceCardTwo + amount), dashboardPage.getSecondCardBalance());
    }
}