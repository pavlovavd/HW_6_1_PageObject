package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {

    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement button = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");

    public DashboardPage transfer (int amountTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(String.valueOf(amountTransfer));
        from.setValue(cardInfo.getNumber());
        button.doubleClick();
        return new DashboardPage();
    }

    public void failTransfer() {
        $(withText("Недостаточно средств для перевода")).shouldBe(Condition.visible);
    }


    public void zeroTransfer() {
        $(withText("Неверно введена сумма, минимальная сумма перевода 1 руб")).shouldBe(Condition.visible);
    }
}
