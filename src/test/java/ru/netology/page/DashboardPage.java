package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cardsSelector = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private  SelenideElement firstButton = $$("[data-test-id=action-deposit]").first();
    private  SelenideElement secondButton = $$("[data-test-id=action-deposit]").last();

//    private SelenideElement buttonSelector = $("button");
//    private SelenideElement updateSelector = $("[data-test-id=\"action-reload\"]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(String id) {
        val text = cardsSelector.find(attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage cartOneDeposit() {
        firstButton.click();
        return new TransferPage();
    }

    public TransferPage cartTwoDeposit() {
        secondButton.click();
        return new TransferPage();
    }
}
