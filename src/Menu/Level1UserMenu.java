/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import DAO.MessageDAO;
import DAO.UserDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Database;
import new_individual_project.Message;
import new_individual_project.Methods;
import new_individual_project.User;

/**
 *
 * @author user
 */
public class Level1UserMenu {

    User user = new User();
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    Methods myMethods = new Methods();
    UserDAO userDao = new UserDAO();
    MessageDAO messageDao = new MessageDAO();

    public Level1UserMenu() throws InstantiationException, ClassNotFoundException, IllegalAccessException, Exception {

    }

    public void userChoices() {

        System.out.println("Select one of the following options:  ");
        System.out.println("1. New message ");
        System.out.println("2. Inbox  ");
        System.out.println("3. Outbox ");
        System.out.println("4. Exit ");

        System.out.println("===============================================================================================");
        System.out.println("Your option is : ");

    }

    public void userSelection() throws IOException, Exception {
        userChoices();

        String userChoice;
        boolean notExit = true;
        while (notExit) {
            userChoice = sc.next();
            System.out.println("===============================================================================================");

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

    public void sendMessage() throws IOException, Exception {

        int tries = 0;
        boolean viewActiveUsers = myMethods.askForActiveUsers();
        if (viewActiveUsers) {
            List<User> allUsers = userDao.getAllUsers();
            for (int i = 0; i < allUsers.size(); i++) {
                System.out.println(allUsers.get(i));
            }
        }

        String receiverNickname = " ";
        boolean receiverExists = false;
        while (!receiverExists & tries < 2) {
            System.out.println("Type receiver's nickname:  ");
            receiverNickname = sc.next();
            System.out.println("===============================================================================================");
            receiverExists = userDao.checkIfNicknameExists(receiverNickname);

            if (receiverExists) {
                System.out.println("Message: ");
                String msg = myMethods.validMessageLength();
                System.out.println("===============================================================================================");

                messageDao.sendMessage(msg, receiverNickname, user.getNickname());
                System.out.println("Message was sent successfuly!");
                System.out.println("===============================================================================================");

            } else {

                tries++;
                if (tries == 2) {
                    // if tries are greater than 2 the user will be redirected in the app menu !
                    System.out.println("Try again later!");
                    System.out.println("===============================================================================================");

                    break;
                }

                System.out.println("Receiver doesn't exists! Type the correct nickname: ");
                receiverNickname = sc.next();

            }
        }

    }

    public void inbox() {

        List<Message> inbox = messageDao.inbox(user.getNickname());
        for (int i = 0; i < inbox.size(); i++) {
            System.out.println(inbox.get(i));
        }

    }

    public void sent() {

        List<Message> sent = messageDao.sent(user.getNickname());
        for (int i = 0; i < sent.size(); i++) {
            System.out.println(sent.get(i));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
