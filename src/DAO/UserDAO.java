package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Database;
import new_individual_project.Login;
import new_individual_project.User;

public class UserDAO {

    public UserDAO() {

    }

    //get all users from database
    public List<User> getAllUsers() {
        Database db = null;
        try {
            db = new Database();

            List<User> users = new ArrayList();
            String query = "select*from user order by nickname";

            ResultSet rs = db.sqlSelect(query);
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setNickname(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setLevel(rs.getInt(5));
                user.setPassword(rs.getString(6));

                users.add(user);
            }
            return users;
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }

        return null;
    }

    //create a user 
    public User createUser(String nickname, String username, String password, String firstName, String lastName, int level) {
        Database db = null;
        try {
            db = new Database();
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLevel(level);
            user.setPassword(password);
            user.setUsername(username);
            user.setNickname(nickname);

            String query = "insert into user values(?,?,?,?"
                    + ",?,?,default)";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, nickname);
            pst.setString(4, username);
            pst.setInt(5, level);
            pst.setString(6, password);
            pst.executeUpdate();

            return user;
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return null;
    }

    // update a user
    public void updateUser(String oldNickname, String nickname, String username, String firstName, String lastName, String password, int level) {
        Database db = null;
        try {
            db = new Database();
            String query = "update user "
                    + "set "
                    + "first_name = ?, "
                    + " last_name = ?, "
                    + " nickname= ?, "
                    + " username = ?, "
                    + " level = ?, "
                    + " password = ? "
                    + " where nickname = ? and not(nickname = 'admin')";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, nickname);
            pst.setString(4, username);
            pst.setInt(5, level);
            pst.setString(6, password);
            pst.setString(7, oldNickname);
            pst.executeUpdate();
        } catch (Exception ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public void deleteUser(String nickname) {
        Database db = null;
        try {
            db = new Database();
            String query = "delete from user where nickname = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            pst.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    // check if a user with a specific nickname exists in database

    public boolean checkIfNicknameExists(String nickname) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from user where nickname = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            ResultSet rs = pst.executeQuery();
            return rs.first();

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return false;
    }
    //check if a user with a specific nickname AND under a certain level, exists in database

    public boolean checkIfNicknameExists(String nickname, int lvl) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from user where nickname = ? and level < ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            pst.setInt(2, lvl);
            ResultSet rs = pst.executeQuery();
            return rs.first();

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return false;
    }

    //check if a user with a specific username exists in database
    public boolean checkIfUsernameExists(String username) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from user where username = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            return rs.first();

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return false;
    }

    //get all users under a certain level
    public List<User> getUsersByLevel(int level) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from user where level < ? order by nickname";
            PreparedStatement pst = db.pst(query);
            pst.setInt(1, level);
            ResultSet rs = pst.executeQuery();
            List<User> usersByLevel = new ArrayList();
            while (rs.next()) {
                User user = new User();
                if (rs.getInt("level") < level) {
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setLevel(rs.getInt("level"));
                    user.setNickname(rs.getString("nickname"));
                    user.setPassword(rs.getString("password"));
                    user.setUserid(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    usersByLevel.add(user);
                }
            }
            if (usersByLevel.isEmpty()) {
                System.out.println("No results available!");
                System.out.println("===============================================================================================");
            }

            return usersByLevel;
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return null;
    }

    //view user's personal information except password
    public User viewUser(String nickname) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from user where nickname = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                if (rs.getString("nickname").equals(nickname)) {
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setLevel(rs.getInt("level"));
                    user.setNickname(rs.getString("nickname"));
                    user.setPassword(rs.getString("password"));
                    user.setUserid(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    return user;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return null;
    }

    public boolean checkIfPasswordMatch(User user) throws Exception {
        String attemptedPassword = "";
        Database db = null;
        try {
            db = new Database();
            String query = "select password from user where username = ? ";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, user.getUsername());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                attemptedPassword = rs.getString("password");
            }

            return user.getPassword().equals(attemptedPassword);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (!(db == null)) {
                db.myConClose();
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        Database db = null;
        User user;
        try {
            db = new Database();
            user = new User();
            String query = "select*from user where username = ? ";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, user.getUsername());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setNickname(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setLevel(rs.getInt(5));
                user.setPassword(rs.getString(6));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!(db == null)) {
                try {
                    db.myConClose();
                } catch (Exception ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            return null;
        }
    }
}
