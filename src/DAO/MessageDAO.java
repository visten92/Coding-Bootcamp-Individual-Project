package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import new_individual_project.Database;
import new_individual_project.LogHistory;
import new_individual_project.Login;
import new_individual_project.Message;
import new_individual_project.User;

public class MessageDAO {

    public MessageDAO() {

    }

    // Get all Messages from database
    public List<Message> getAllMessages() {
        Database db = null;
        try {
            db = new Database();

            List<Message> messages = new ArrayList();
            String query = "select id,from message,user_message where message.id = user_message.message_id";

            ResultSet rs = db.sqlSelect(query);
            while (rs.next()) {
                Message message = new Message();
                message.setSenderNickname(rs.getString(1));
                message.setReceiverNickname(rs.getString(2));
                message.setMessage(rs.getString(3));
                message.setDate(rs.getTimestamp(4));
                message.setMessageID(rs.getInt(5));

                messages.add(message);
            }
            return messages;
        } catch (Exception ex) {
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
        return null;
    }
    //Set a message from a ResultSet

    public Message setMessageFromResultSet(ResultSet rs) {
        Database db = null;
        try {
            db = new Database();
            Message msg = new Message();
            msg.setMessage(rs.getString("content"));
            msg.setMessageID(rs.getInt("id"));
            msg.setReceiverNickname(rs.getString("receiver"));
            msg.setSenderNickname(rs.getString("sender"));
            msg.setDate(rs.getTimestamp("time"));
            return msg;
        } catch (Exception ex) {
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
        return null;
    }

    //get the messages from a spesific sender limiting the level of the receiver
    public List<Message> getMessagesByReceiverLevel(int lvl, String nickname) {
        Database db = null;
        try {
            db = new Database();
            List<Message> messagesByLevel = new ArrayList();

            String query2 = "select message.id, content, sender , receiver, message.time from message,user \n"
                    + "where (sender=? and  "
                    + " user.nickname = message.receiver \n"
                    + "and user.level<?) or (receiver=? and  "
                    + " user.nickname = message.sender \n"
                    + "and user.level<?)";

            PreparedStatement pst = db.pst(query2);
            pst.setString(1, nickname);
            pst.setInt(2, lvl);
            pst.setString(3, nickname);
            pst.setInt(4, lvl);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Message msg = setMessageFromResultSet(rs);
                messagesByLevel.add(msg);

            }
            return messagesByLevel;
        } catch (Exception ex) {
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
        return null;
    }

    // get the most recent message from database. 
    public Message getMostRecentMessage() {
        Database db = null;
        try {
            db = new Database();
            String query = "select content,sender,receiver,time,id from message WHERE id=(SELECT MAX(id)  from message);";
            ResultSet rs = db.sqlSelect(query);
            while (rs.next()) {
                Message msg = setMessageFromResultSet(rs);
                return msg;
            }
        } catch (Exception ex) {
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
        return null;
    }
    //send a message

    public Message sendMessage(String message, String receiver, String sender) {
        Database db = null;
        try {
            db = new Database();
            String query = "insert into message values(?,?,?,default,default)";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, receiver);
            pst.setString(2, sender);
            pst.setString(3, message);
            pst.executeUpdate();

            LogHistory log = new LogHistory();
            log.printToFile(getMostRecentMessage());
            return getMostRecentMessage();

        } catch (Exception ex) {
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
        return null;
    }

    // get the received messages of a specific user
    public List<Message> inbox(String nickname) {
        Database db = null;
        try {
            db = new Database();
            List<Message> inbox = new ArrayList();
            String query = "select*from message where receiver = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Message msg = setMessageFromResultSet(rs);
                inbox.add(msg);

            }
            if (inbox.isEmpty()) {
                System.out.println("Your inbox is empty!");
                System.out.println("===============================================================================================");

            }
            return inbox;
        } catch (Exception ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    // get sent messages of a specific user

    public List<Message> sent(String nickname) {
        Database db = null;
        try {
            db = new Database();
            List<Message> sent = new ArrayList();
            String query = "select*from message where sender = ? ";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Message msg = new Message();
                msg.setMessage(rs.getString("content"));
                msg.setMessageID(rs.getInt("id"));
                msg.setReceiverNickname(rs.getString("receiver"));
                msg.setSenderNickname(rs.getString("sender"));
                msg.setDate(rs.getTimestamp("time"));

                sent.add(msg);

            }
            if (sent.isEmpty()) {
                System.out.println("You haven't sent a message yet!");
                System.out.println("===============================================================================================");

            }
            return sent;
        } catch (Exception ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    //gets the messages that a specific user is involved either as sender or as a receiver
    public List<Message> viewMessages(String nickname) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from message where sender = ? or receiver = ?";
            PreparedStatement pst = db.pst(query);
            pst.setString(1, nickname);
            pst.setString(2, nickname);

            ResultSet rs = pst.executeQuery();
            List<Message> messages = new ArrayList();

            while (rs.next()) {
                Message msg = new Message();
                msg.setMessage(rs.getString("content"));
                msg.setMessageID(rs.getInt("id"));
                msg.setReceiverNickname(rs.getString("receiver"));
                msg.setSenderNickname(rs.getString("sender"));
                msg.setDate(rs.getTimestamp("time"));

                messages.add(msg);
            }

            if (messages.isEmpty()) {
                System.out.println(nickname + " has not interacted with any user!");
                System.out.println("===============================================================================================");

            }
            return messages;
        } catch (Exception ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    //edit a message

    public boolean editMessage(int messageID, String message) {
        Database db = null;
        try {
            db = new Database();
            String query = "select*from message where id = ? ";
            PreparedStatement pst = db.pst(query);
            pst.setInt(1, messageID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String query1 = "update message set content ='" + message + "' where id =" + messageID;
                db.sqlCrud(query1);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean checkIfMessagesContainsID(List<Message> messages, int ID) {

        Iterator iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message msg = (Message) iterator.next();
            if (msg.getMessageID() == ID) {
                return true;
            }

        }
        return false;
    }

    //delete a message
    public boolean deleteMessage(int messageID) {
        Database db = null;
        try {
            db = new Database();
            String query = "delete from message where id = ? ";
            PreparedStatement pst = db.pst(query);
            pst.setInt(1, messageID);
            pst.executeUpdate();
            return true;

        } catch (Exception ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
