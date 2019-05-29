package new_individual_project;

import java.util.Objects;

public class User {

    private String username;
    private String password;
    private String nickname;
    private int level;
    private String firstName;
    private String lastName;
    private int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserid() {
        return id;
    }

    public void setUserid(int Userid) {
        this.id = Userid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;

        return user.id == id;

    }

    @Override
    public String toString() {
        return "Nickname: " + nickname + "\n"
                + //                +"\n"+"Username:  "+ username+"\n"+"First name: "+firstName+"\n"
                //                +"Last name: "+lastName+"\n"+"Level: "+level +"\n" +
                "============================================================================================";

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
