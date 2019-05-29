/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Message;

/**
 *
 * @author user
 */
public class Level3UserMenu extends Level2UserMenu {

    public Level3UserMenu() throws ClassNotFoundException, IllegalAccessException, InstantiationException, Exception {
        super();

    }

    @Override
    public void userChoices() {

        System.out.println("Select one of the following options:  ");
        System.out.println("1. New message ");
        System.out.println("2. Inbox ");
        System.out.println("3. Sent");
        System.out.println("4. View messages");
        System.out.println("5. Edit messages ");
        System.out.println("6. Exit ");

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
                    searchLevel = 3;
                    viewUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "5":
                    searchLevel = 3;
                    editUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "6":
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

    public void editUsersMessages(int lvl) throws Exception {

        List viewMessages = viewUsersMessages(lvl);
        boolean MessageContainsID = false;
        int ID = 0;
        int tries = 0;

        if (!(viewMessages == null)) {
            do {
                //The loop runs until the user give a valid message ID or pass tries limit
                if (tries == 4) {
                    System.out.println("Try again later!");
                    System.out.println("===============================================================================================");
                    break;
                }
                tries++;
                System.out.println("Select the message to edit by typing it's ID: ");
                ID = myMethods.nextInt(1);
                System.out.println("===============================================================================================");

                MessageContainsID = messageDao.checkIfMessagesContainsID(viewMessages, ID);
                if (!MessageContainsID) {
                    System.out.println("Invalid message ID! ");
                    System.out.println("===============================================================================================");

                }
            } while (!MessageContainsID);

            // if the chosen id from the do-while loop is valid the user can edit the selected message
            if (MessageContainsID) {
                System.out.println("Message: ");
                String msg = myMethods.validMessageLength();
                System.out.println("===============================================================================================");

                messageDao.editMessage(ID, msg);
                System.out.println("Message was edited succesfuly!");
                System.out.println("===============================================================================================");

            }
        }

    }

}
