/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Message;
import new_individual_project.User;

/**
 *
 * @author user
 */
public class Level2UserMenu extends Level1UserMenu {

    int searchLevel;

    public Level2UserMenu() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        super();
    }

    @Override
    public void userChoices() {
        System.out.println("Select one of the following options:  ");
        System.out.println("1. New message ");
        System.out.println("2. Inbox ");
        System.out.println("3. Outbox");
        System.out.println("4. View messages");
        System.out.println("5. Exit ");

        System.out.println("===============================================================================================");
        System.out.println("Your option is : ");

    }

    @Override
    public void userSelection() throws IOException, Exception {
        userChoices();

        String userChoice;
        boolean notExit = true;
        while (notExit) {
            userChoice = sc.next();

            switch (userChoice) {
                case "1":
                    sendMessage();
                    userChoices();
                    break;
                case "2":
                    inbox();
                    userChoices();
                    break;
                case "3":
                    sent();
                    userChoices();
                    break;
                case "4":
                    searchLevel = 2;
                    viewUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "5":
                    notExit = false;
                    System.out.println("Thank you for using my chat application");
                    System.out.println("===============================================================================================");
                    break;

                default:
                    System.out.println("===============================================================================================");
                    System.out.println("Wrong input!");
                    System.out.println("Select one of the possible choices");
                    System.out.println("===============================================================================================");
                    userChoices();
                    break;

            }
        }

    }

    public List<Message> viewUsersMessages(int lvl) throws Exception {
        int tries = 0;
        String selectedNickname = " ";

        List<User> usersByLevel = userDao.getUsersByLevel(lvl);
        boolean viewActiveUsers = myMethods.askForActiveUsers();

        if (viewActiveUsers) {
            myMethods.viewAllUsers(usersByLevel);
        }

        boolean userExists = false;
        do {
            tries++;
            if (tries == 4) {
                // if tries are greater than 4 the user will be redirected in the app menu !
                System.out.println("Try again later!");
                System.out.println("===============================================================================================");

                break;
            }

            System.out.println("Select a user by typing his nickname: ");
            selectedNickname = sc.next();

            System.out.println("===============================================================================================");

            userExists = userDao.checkIfNicknameExists(selectedNickname, lvl);

            if (!userExists) {
                System.out.println("Invalid user!");
                System.out.println("===============================================================================================");

            }
        } while (!userExists);

        if (userExists) {

            List<Message> viewMessages = messageDao.getMessagesByReceiverLevel(lvl, selectedNickname);

            if (!(viewMessages.isEmpty())) {
                myMethods.viewAllMessages(viewMessages);
                return viewMessages;
            } else {
                System.out.println(selectedNickname + " inbox and outbox is empty ");
                System.out.println("===============================================================================================");

            }

        }

        return null;
    }

}
