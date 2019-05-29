package Menu;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.User;

public class AdminMenu extends Level4UserMenu {

    public AdminMenu() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        super();

    }

    @Override
    public void userChoices() {

        System.out.println("Select one of the following options:  ");
        System.out.println("1.  New message ");
        System.out.println("2.  Inbox ");
        System.out.println("3.  Outbox");
        System.out.println("4.  View messages");
        System.out.println("5.  Edit messages ");
        System.out.println("6.  Delete messages ");
        System.out.println("7.  Create user");
        System.out.println("8.  View user");
        System.out.println("9.  Edit user ");
        System.out.println("10. Delete user");
        System.out.println("11. Exit");

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
                    searchLevel = 6;
                    viewUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "5":
                    searchLevel = 6;
                    editUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "6":
                    searchLevel = 6;
                    deleteUsersMessages(searchLevel);
                    userChoices();
                    break;
                case "7":
                    createUser();
                    userChoices();
                    break;
                case "8":
                    viewUser();
                    userChoices();
                    break;
                case "9":
                    UpdateUser();
                    userChoices();
                    break;
                case "10":
                    deleteUser();
                    userChoices();
                    break;
                case "11":
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

    public void createUser() {
        System.out.println("First name: ");
        String firstName = sc.next();
        System.out.println("===============================================================================================");

        System.out.println("Last name: ");
        String lastName = sc.next();
        System.out.println("===============================================================================================");

        String nickname = " ";
        boolean nicknameExists = false;
        int tries = 0;
        do {
            tries++;
            if (tries == 4) {
                System.out.println("Try again later! ");
                System.out.println("===============================================================================================");
                nicknameExists = true;
                break;
            }
            System.out.println("Nickname: ");
            nickname = sc.next();
            System.out.println("===============================================================================================");

            nicknameExists = userDao.checkIfNicknameExists(nickname);
            if (nicknameExists) {
                System.out.println("Nickname already exists! Try a different one!");
                System.out.println("===============================================================================================");

            }
        } while (nicknameExists);

        if (!nicknameExists) {
            String username = " ";
            boolean usernameExists = false;
            tries = 0;
            do {
                tries++;
                if (tries == 4) {
                    System.out.println("Try again later!");
                    System.out.println("===============================================================================================");

                    usernameExists = true;
                    break;
                }
                System.out.println("Username: ");
                username = sc.next();
                usernameExists = userDao.checkIfUsernameExists(username);
                if (usernameExists) {
                    System.out.println("Username already exists! ");
                    System.out.println("===============================================================================================");

                }

            } while (usernameExists);

            if (!usernameExists) {
                System.out.println("Password: ");
                String password = sc.next();
                System.out.println("===============================================================================================");

                System.out.println("Level:");
                int level = myMethods.nextInt(0);
                level = myMethods.correctLevel(level);
                System.out.println("===============================================================================================");

                userDao.createUser(nickname, username, password, firstName, lastName, level);

                System.out.println("New user has been created successfully!");
            }
        }
    }

    public boolean viewUser() throws Exception {
        int tries = 0;
        boolean viewActiveUsers = myMethods.askForActiveUsers();

        if (viewActiveUsers) {
            List<User> allUsers = userDao.getAllUsers();
            myMethods.viewAllUsers(allUsers);
        }
        User viewUser;
        do {
            tries++;

            if (tries == 4) {
                System.out.println("Try again later!");
                System.out.println("===============================================================================================");
                break;
            }

            System.out.println("Nickname: ");
            String nickname = sc.next();
            System.out.println("===============================================================================================");
            viewUser = userDao.viewUser(nickname);

            if (viewUser == null) {
                System.out.println("Invalid user! ");
                System.out.println("===============================================================================================");
            }
            if (!(viewUser == null)) {
                System.out.println("Nickname: " + viewUser.getNickname());
                System.out.println("User ID: " + viewUser.getUserid());
                System.out.println("First name: " + viewUser.getFirstName());
                System.out.println("Last name: " + viewUser.getLastName());
                System.out.println("Username: " + viewUser.getUsername());
                System.out.println("Level: " + viewUser.getLevel());
                System.out.println("===============================================================================================");

            }

        } while (viewUser == null);

        return true;

    }

    public void UpdateUser() throws Exception {
        String updatedUserNickname = " ";
        int tries = 0;
        boolean viewActiveUsers = myMethods.askForActiveUsers();

        if (viewActiveUsers) {
            List<User> allUsers = userDao.getAllUsers();
            myMethods.viewAllUsers(allUsers);
        }
        boolean userExists = false;
        do {
            tries++;
            if (tries == 4) {
                System.out.println("Try again later!");
                System.out.println("===============================================================================================");

                break;
            }
            System.out.println("Type the nickname of the user that you want to update: ");
            updatedUserNickname = sc.next();
            System.out.println("===============================================================================================");

            userExists = userDao.checkIfNicknameExists(updatedUserNickname);
            if (!userExists) {
                System.out.println("Invalid user! Try again!");
                System.out.println("===============================================================================================");

            }
            if (updatedUserNickname.equals("admin")) {
                System.out.println("You can't update admin!");
                System.out.println("===============================================================================================");

                userExists = false;
            }

        } while ((!userExists) || updatedUserNickname.equals("admin")); // the loop runs until the user select until admin select a valid user

        if (userExists) {
            System.out.println("First name: ");
            String firstName = sc.next();
            System.out.println("===============================================================================================");

            System.out.println("Last name: ");
            String lastName = sc.next();
            System.out.println("===============================================================================================");

            String nickname = " ";
            boolean nicknameExists = false;
            tries = 0;
            do {
                tries++;
                if (tries == 4) {
                    System.out.println("Try again later!");
                    System.out.println("===============================================================================================");

                    nicknameExists = true;
                    break;
                }
                System.out.println("Nickname: ");
                nickname = sc.next();
                System.out.println("===============================================================================================");

                nicknameExists = userDao.checkIfNicknameExists(nickname);
                if (nicknameExists) {
                    System.out.println("Nickname already exists! ");
                    System.out.println("===============================================================================================");

                }
            } while (nicknameExists);

            String username = " ";
            boolean usernameExists = false;
            tries = 0;
            if (!nicknameExists) {
                do {
                    tries++;
                    if (tries == 4) {
                        System.out.println("Try again later!");
                        System.out.println("===============================================================================================");

                        usernameExists = true;
                        break;
                    }
                    System.out.println("Username: ");
                    username = sc.next();
                    System.out.println("===============================================================================================");

                    usernameExists = userDao.checkIfUsernameExists(username);
                    if (usernameExists) {
                        System.out.println("Username already exists! ");
                        System.out.println("===============================================================================================");

                    }
                } while (usernameExists);

                if (!usernameExists) {
                    System.out.println("Password: ");
                    String password = sc.next();
                    System.out.println("===============================================================================================");

                    System.out.println("Level:");
                    int level = myMethods.nextInt(0);
                    level = myMethods.correctLevel(level);
                    System.out.println("===============================================================================================");

                    userDao.updateUser(updatedUserNickname, nickname, username, firstName, lastName, password, level);

                    System.out.println(updatedUserNickname + " has been updated successfuly!");
                    System.out.println("================================================================================================");
                }
            }
        }

    }

    public void deleteUser() throws Exception {
        int tries = 0;
        String deletedUserNickname = " ";
        boolean viewActiveUsers = myMethods.askForActiveUsers();

        if (viewActiveUsers) {
            List<User> allUsers = userDao.getAllUsers();
            myMethods.viewAllUsers(allUsers);
        }

        boolean userExists = false;
        do {
            tries++;
            if (tries == 4) {
                System.out.println("Try again later!");
                System.out.println("===============================================================================================");
                break;
            }
            System.out.println("Type the nickname of the user that you want to delete: ");
            deletedUserNickname = sc.next();
            System.out.println("===============================================================================================");

            userExists = userDao.checkIfNicknameExists(deletedUserNickname);

            if (!userExists) {
                System.out.println("Invalid user! Try again!");
                System.out.println("===============================================================================================");

            }
            if (deletedUserNickname.equals("admin")) {
                System.out.println("You can't delete admin!");
                System.out.println("===============================================================================================");
                userExists = false;
            }

        } while ((!userExists) || deletedUserNickname.equals("admin"));//the loop runs until admin select a valid user

        if (userExists) {
            userDao.deleteUser(deletedUserNickname);
            System.out.println(deletedUserNickname + " has been deleted successfuly");
            System.out.println("===============================================================================================");
        }

    }

}
