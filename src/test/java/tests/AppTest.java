package tests;

import data.DataHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;


public class AppTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyLoginCase() {

        var authInfo = DataHelper.getAuthInfo();
        var login = new LoginPage();
        login.validLogin(authInfo);
        var verification = new VerificationPage();
        verification.verificationCode();
        var dashboard = new DashboardPage();
        dashboard.check();
    }

    @Test
    public void shouldBlockSutInWrongPasswordCase() {

        var authInfo = DataHelper.getAuthInfo();
        var login = new LoginPage();
        login.invalidLogin(authInfo);
        login.cleanForm();

        login.invalidLogin(authInfo);
        login.cleanForm();

        login.invalidLogin(authInfo);
        login.cleanForm();

        login.invalidLogin(authInfo);
        login.cleanForm();
    }
}
