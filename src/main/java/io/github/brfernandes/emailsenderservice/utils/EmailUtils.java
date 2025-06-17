package io.github.brfernandes.emailsenderservice.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token) {
        return "Hello, " + name + "\n\nYour new account has" +
                " been created. Please click the link belo to" +
                " veirify you account \n\n"
                + getVerificationUrl(host, token)
                + "\n\n The support team";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/v1/users/activate?token=" + token;
    }
}
