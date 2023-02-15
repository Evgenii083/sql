package data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dbutils {

    public void clean() throws SQLException {
        var runner = new QueryRunner();
        var cleanCards = "DELETE FROM cards;";
        var cleanAuthCodes = "DELETE FROM auth_codes;";
        var cleanCardTransactions = "DELETE FROM card_transactions;";
        var cleanUsers = "DELETE FROM users";

        try (
                var conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            runner.update(conn, cleanCards);
            runner.update(conn, cleanAuthCodes);
            runner.update(conn, cleanCardTransactions);
            runner.update(conn, cleanUsers);
        }
    }

    @SneakyThrows
    public void setUp() throws SQLException {
        var faker = new Faker();
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(login,password) VALUES (?,?);";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");

        ) {
            runner.update(conn, dataSQL, faker.name().username(), "pass");
            runner.update(conn, dataSQL, faker.name().username(), "pass");

        }
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var runner = new QueryRunner();
        var codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";
        String verificationCode = "";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
//                PreparedStatement preparedStatement = conn.prepareStatement(codeSQL);
        ) {
            val code = runner.query(conn, codeSQL, new ScalarHandler<>());
            System.out.println(code);
            verificationCode = (String) code;
        }
        return verificationCode;
    }
}
//
////    @SneakyThrows
////    public void showData() {
////        var countSQL = "SELECT COUNT(*) FROM users;";
////        var usersSQl = "SELECT (*) FROM users;";
////        var runner = new QueryRunner();
////
//////        try (
//////                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
//////
//////        ) {
////        var all = runner.query(getConnection(), usersSQl, new BeanListHandler<>(User.class));
////        System.out.println(all);
////    }
//}
////    @SneakyThrows
////    @BeforeAll
////    public void cleanData(){
////
////        var runner = new QueryRunner();
////        var cleanCards = "DELETE FROM cards;";
////        var cleanAuthCodes = "DELETE FROM auth_codes;";
////        var cleanCardTransactions = "DELETE FROM card_transactions;";
////        var cleanUsers = "DELETE FROM users";
////
////        try (
////                var conn = DriverManager.getConnection(
////                        "jdbc:mysql://localhost:3306/app", "app", "pass"
//                );
//                PreparedStatement preparedStatement = conn.prepareStatement(cleanCards);
//                PreparedStatement preparedStatement1 = conn.prepareStatement(cleanAuthCodes);
//                PreparedStatement preparedStatement2 = conn.prepareStatement(cleanCardTransactions);
//                PreparedStatement preparedStatement3 = conn.prepareStatement(cleanUsers);
//        ) {
//            runner.update(conn, cleanCards);
//            runner.update(conn, cleanAuthCodes);
//            runner.update(conn, cleanCardTransactions);
//            runner.update(conn, cleanUsers);
//        }
//    }

