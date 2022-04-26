package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement button = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public DashboardPage transfer (int amountTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(String.valueOf(amountTransfer));
        from.setValue(cardInfo.getNumber());
        button.click();
        return new DashboardPage();
    }




}
