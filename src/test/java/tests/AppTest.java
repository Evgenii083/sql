package tests;

import data.DataHelper;
import data.Dbutils;
import org.junit.jupiter.api.*;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class AppTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @AfterAll
    static void cleanDb() {
        Dbutils.clean();
    }

    @Test
    @DisplayName("Should successfully login, password and code")
    public void happyLoginCase() {

        var login = new LoginPage();
        var authInfo = DataHelper.validAuth();
        var verificationPage = login.validLogin(authInfo);
        verificationPage.verificationCode(Dbutils.getVerificationCode());
        var dashboard = new DashboardPage();
        dashboard.check();

    }

    @Test
    @DisplayName("System should be block after third input with invalid password")
    public void shouldBlockSutInWrongPasswordCase() {

        var authInfo = DataHelper.invalidAuth();
        var login = new LoginPage();
        login.invalidLogin(authInfo);
        login.reenter(4);
        login.blockedNotifications();
    }
}
