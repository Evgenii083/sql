package page;

import com.codeborne.selenide.SelenideElement;
import data.Dbutils;
import data.User;
import lombok.SneakyThrows;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement inputCode = $("[data-test-id=code] input");
    private SelenideElement button = $("[data-test-id=action-verify]");

    public DashboardPage verificationCode(String code) {
        inputCode.setValue(code);
        button.click();
        return new DashboardPage();
    }
}
