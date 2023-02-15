package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import data.DataHelper;
import org.openqa.selenium.Keys;

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

    public void invalidLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(faker.internet().password());
        button.click();
        error.shouldBe(Condition.visible).shouldHave(Condition.text("Ошибка!" + "\n" + "Неверно указан логин или пароль" ));

    }

    public void cleanForm(){
        login.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        password.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
    }
}
