package new_individual_project;

import DAO.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Login {

    User user = new User();
    UserDAO userDao = new UserDAO();
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Login() throws InstantiationException, ClassNotFoundException, IllegalAccessException, Exception {

        Database db = new Database();
        boolean passwordMatch = false;
        boolean userExists = false;

        do {
            login();
            passwordMatch = userDao.checkIfPasswordMatch(this.user);
            userExists = userDao.checkIfUsernameExists(this.user.getUsername());

            if (!(passwordMatch && userExists)) {
                System.out.println("Invalid username or password! Try again");
                System.out.println("===============================================================================================");
            }

        } while (!(passwordMatch && userExists));

        String query = "select*from user where username = ? ";
        PreparedStatement pst = db.pst(query);
        pst.setString(1, user.getUsername());
        ResultSet rs = pst.executeQuery();

        if (rs.first()) {
            this.user.setFirstName(rs.getString(1));
            this.user.setLastName(rs.getString(2));
            this.user.setNickname(rs.getString(3));
            this.user.setUsername(rs.getString(4));
            this.user.setLevel(rs.getInt(5));
            this.user.setPassword(rs.getString(6));

        }

        db.myConClose();

    }

    public void login() {
        String username;
        String password;
        System.out.println("username: ");
        username = sc.next();
        this.user.setUsername(username);

        System.out.println("password: ");
        password = sc.next();
        this.user.setPassword(password);

        System.out.println("===============================================================================================");

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
