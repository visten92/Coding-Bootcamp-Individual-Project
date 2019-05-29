package new_individual_project;

import Menu.MenuSelection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class New_Individual_Project {

    public static void main(String[] args) {
        try {
            MenuSelection ms = new MenuSelection();
            ms.select();
        } catch (Exception ex) {
            Logger.getLogger(New_Individual_Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
