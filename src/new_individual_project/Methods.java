package new_individual_project;

import Menu.Level1UserMenu;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Methods {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Methods() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    }

// this method run until user enters a number
    public int nextInt(int i) {
        boolean badNumber;

        do {

            try {
                i = sc.nextInt();

                badNumber = false;
            } catch (Exception e) {
                System.out.println("Please enter a number!");
                sc.next();
                System.out.println("===============================================================================================");

                badNumber = true;
            }
        } while (badNumber);
        return i;
    }
// this method returns a string less than 250 characters

    public String validMessageLength() {
        String msg = " ";
        do {
            if (msg.length() >= 250) {
                System.out.println("Message is over 250 characters! Please try again! ");
            }
            msg = sc.next();
        } while (msg.length() >= 250);
        return msg;
    }
//this method returns and integer beetween 1-4 that represents the valid level

    public int correctLevel(int level) {
        while (level < 1 || level > 4) {
            System.out.println("Level must be beetwen 1-4!  ");
            System.out.println("Level:");
            level = nextInt(1);

        }
        return level;
    }

//this method asks  for active users
    public boolean askForActiveUsers() throws Exception {
        System.out.println("Do you want to see active users? (y/n): ");
        System.out.println("===============================================================================================");

        String answer = sc.next();
        System.out.println("===============================================================================================");

        return answer.equals("y");
    }

    // this method prints a list of users
    public void viewAllUsers(List<User> allUsers) {

        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println(allUsers.get(i));
        }

    }

    // this method prints a list of messages
    public void viewAllMessages(List<Message> allMessages) {

        for (int i = 0; i < allMessages.size(); i++) {
            System.out.println(allMessages.get(i));
        }

    }

}
