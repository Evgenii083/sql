package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutils {
    private Dbutils(){
    }

    public static void clean() {
        var runner = new QueryRunner();
        var cleanCards = "DELETE FROM cards;";
        var cleanAuthCodes = "DELETE FROM auth_codes;";
        var cleanCardTransactions = "DELETE FROM card_transactions;";
        var cleanUsers = "DELETE FROM users";

        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            runner.update(conn, cleanCards);
            runner.update(conn, cleanAuthCodes);
            runner.update(conn, cleanCardTransactions);
            runner.update(conn, cleanUsers);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public static String getVerificationCode() {
        var runner = new QueryRunner();
        var codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";
        String verificationCode = "";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val code = runner.query(conn, codeSQL, new ScalarHandler<>());
            System.out.println(code);
            verificationCode = (String) code;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return verificationCode;
    }
}

