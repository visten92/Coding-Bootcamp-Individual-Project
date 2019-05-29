package new_individual_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private Connection myCon = null;

    public Database() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String url = "jdbc:mysql://localhost:3306/individual?characterEncoding=utf-8&autoReconnect=true&serverTimezone=UTC";
        String username = "admin";
        String password = "admin";
        try {
            myCon = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet sqlSelect(String query) throws Exception {

        PreparedStatement pst = myCon.prepareStatement(query);
        return pst.executeQuery();

    }

    public int sqlCrud(String query) throws Exception {

        PreparedStatement pst = myCon.prepareStatement(query);
        return pst.executeUpdate();
    }

    public PreparedStatement pst(String query) throws Exception {
        return myCon.prepareStatement(query);
    }

    public void myConClose() throws Exception {

        this.myCon.close();

    }
}
