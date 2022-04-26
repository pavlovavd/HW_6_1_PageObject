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

    private ElementsCollection cardsSelector = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement cartSelector1 = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] button");
    private SelenideElement cartSelector2 = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d] button");
    private SelenideElement buttonSelector = $("button");
    private SelenideElement updateSelector = $("[data-test-id=\"action-reload\"]");


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

    public TransferPage cardOneDeposit() {
        cartSelector1.click();
        return new TransferPage();
    }

    public TransferPage cardTwoDeposit() {
        cartSelector2.click();
        return new TransferPage();
    }

}
