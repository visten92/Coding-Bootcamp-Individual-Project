/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Message;

/**
 *
 * @author user
 */
public class Level4UserMenu extends Level3UserMenu {

    public Level4UserMenu() throws ClassNotFoundException, IllegalAccessException, InstantiationException, Exception {
        super();

    }

    @Override
    public void userChoices() {

        System.out.println("Select one of the following options:  ");
        System.out.println("1. New message ");
        System.out.println("2. Inbox ");
        System.out.println("3. Outbox");
        System.out.println("4. View messages");
        System.out.println("5. Edit messages ");
        System.out.println("6. Delete messages ");
        System.out.println("7. Exit");

        System.out.println("===============================================================================================");
        System.out.println("Your option is : ");
    }

    @Override
    public void userSelection() throws Exception {
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
                    searchLevel = 4;
                    viewUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "5":
                    searchLevel = 4;
                    editUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "6":
                    searchLevel = 4;

                    deleteUsersMessages(searchLevel);

                    userChoices();
                    break;
                case "7":
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

    public void deleteUsersMessages(int lvl) throws Exception {
        List viewMessages = viewUsersMessages(lvl);
        boolean MessageContainsID = false;
        int ID = 0;
        int tries = 0;

        if (!(viewMessages == null)) {

            //The loop runs until the user give a valid message ID or pass tries limit
            do {

                if (tries == 4) {
                    System.out.println("Try again later!");
                    System.out.println("============================================================================================");
                    break;
                }
                tries++;
                System.out.println("Select the message to delete by typing it's ID: ");
                ID = myMethods.nextInt(1);
                System.out.println("===============================================================================================");

                MessageContainsID = messageDao.checkIfMessagesContainsID(viewMessages, ID);
                if (!MessageContainsID) {
                    System.out.println("Invalid message ID! ");
                    System.out.println("============================================================================================");

                }
            } while (!MessageContainsID);

// if the chosen id from the do-while loop is valid the user can delete the selected message
            if (MessageContainsID) {
                messageDao.deleteMessage(ID);
                System.out.println("Message was edited succesfuly!");
                System.out.println("===============================================================================================");

            }
        }

    }

}
