package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement button = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private ElementsCollection rechargeButton = $$(".list__item div");


    public void cardSelection(String inCard) {
        rechargeButton.find(attribute("data-test-id", inCard)).find(".button__content").click();
    }

    public DashboardPage transfer (int amountTransfer, String numberCardOut) {
        amount.setValue(String.valueOf(amountTransfer));
        from.setValue(numberCardOut);
        button.click();
        return new DashboardPage();
    }




}
