package new_individual_project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Message {

    private int messageID;
    private String message;
    private String receiverNickname;
    private String senderNickname;
    private Date time;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public Date getDate() {
        return time;
    }

    public void setDate(Date time) {

        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message msg = (Message) obj;

        return msg.message.equals(message)
                && msg.receiverNickname.equals(receiverNickname)
                && msg.senderNickname.equals(senderNickname)
                && msg.messageID == messageID;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageID + "\r\n"
                + "Message: " + message + "\r\n"
                + "sent by: " + senderNickname + ", sent to: " + receiverNickname + ", at: " + time + "\r\n"
                + "============================================================================================" + "\r";
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

}
