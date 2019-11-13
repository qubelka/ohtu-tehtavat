package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        Pattern usernameValidity =
                Pattern.compile("(^(?!.*\\d)(?!.*[A-Z])(?!.*[!\"#$%&'()*+,-.:;<=>?@/^_`{|}~])(?!.*\\s).{3,}$)");
        Matcher usernameMatcher = usernameValidity.matcher(username);

        Pattern passwordValidity = Pattern.compile("(^(?=.*\\d)(?!.*\\s).{8,}$)");
        Matcher passwordMatcher = passwordValidity.matcher(password);

        return (!usernameMatcher.matches() || !passwordMatcher.matches());
    }
}
