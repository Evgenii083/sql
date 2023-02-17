package page;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    Faker faker = new Faker();
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    private SelenideElement error = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }


    public void invalidLogin(DataHelper.InvalidAuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();


    }

    public void errorNotifications() {
        error.shouldBe(visible).shouldHave(text("Ошибка!" + "\n" + "Неверно указан логин или пароль"));
    }

    public void blockedNotifications() {
        error.shouldBe(visible).shouldHave(text("Система заблокирована"));
    }

    public void cleanForm() {
        login.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        password.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
    }

    public void reenter(int repeat) {
        var authInfo = DataHelper.invalidAuth();

        for (int i = 0; i <= repeat; i++) {
            cleanForm();
            invalidLogin(authInfo);
            errorNotifications();
        }
    }
}
