package Menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Database;
import new_individual_project.Login;
import new_individual_project.User;

public class MenuSelection {

    Login login;

    public MenuSelection() {
        try {
            login = new Login();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MenuSelection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// this method direct the user to the appropriate menu according to his level

    public void select() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, Exception {

        switch (login.getUser().getLevel()) {
            case 1:
                System.out.println("Welcome to my chat application \n");
                Level1UserMenu menu1 = new Level1UserMenu();
                menu1.setUser(login.getUser());
                menu1.userSelection();
                break;
            case 2:
                System.out.println("Welcome to my chat application \n");
                Level2UserMenu menu2 = new Level2UserMenu();
                menu2.setUser(login.getUser());
                menu2.userSelection();
                break;
            case 3:
                System.out.println("Welcome to my chat application \n");
                Level3UserMenu menu3 = new Level3UserMenu();
                menu3.setUser(login.getUser());
                menu3.userSelection();
                break;
            case 4:
                System.out.println("Welcome to my chat application \n");
                Level4UserMenu menu4 = new Level4UserMenu();
                menu4.setUser(login.getUser());
                menu4.userSelection();
                break;
            case 5:
                System.out.println("Welcome to my chat application \n");
                AdminMenu menu5 = new AdminMenu();
                menu5.setUser(login.getUser());
                menu5.userSelection();
                break;
            default:
                break;
        }
    }

}
