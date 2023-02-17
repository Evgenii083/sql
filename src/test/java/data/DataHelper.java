package data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo validAuth() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class InvalidAuthInfo {
        private String login;
        private String password;
    }

    public static InvalidAuthInfo invalidAuth() {
        Faker faker = new Faker();
        var password = faker.internet().password();
        return new InvalidAuthInfo("vasya", password);
    }
}

