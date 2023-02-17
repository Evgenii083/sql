package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement header = $("[data-test-id=dashboard]");

    public void check() {
        header.shouldBe(Condition.visible).shouldHave();
    }
}
