package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }

        if (invalidUsername(username,password)) {
            status.addError("username should have at least 3 characters");
        }

        if (invalidPassword(username, password)) {
            status.addError("password should have at least 8 characters");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

    private boolean invalidUsername(String username, String password) {
        // No numbers, no letters A-Z, no special symbols, no whitespace characters; Length at least 3.
        Pattern usernameValidity =
                Pattern.compile("(^(?!.*\\d)(?!.*[A-Z])(?!.*[!\"#$%&'()*+,-.:;<=>?@/^_`{|}~])(?!.*\\s).{3,}$)");
        Matcher usernameMatcher = usernameValidity.matcher(username);
        return (!usernameMatcher.matches());
    }

    private boolean invalidPassword(String username, String password) {
        // Should contain at least one number; length at least 8; no whitespace characters.
        Pattern passwordValidity = Pattern.compile("(^(?=.*\\d)(?!.*\\s).{8,}$)");
        Matcher passwordMatcher = passwordValidity.matcher(password);

        return (!passwordMatcher.matches());
    }

}
