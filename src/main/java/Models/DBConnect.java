package Models;

import java.sql.*;

public class DBConnect {
    Connection connect = null;
    static final private String dbURL = "jdbc:sqlite:student.db";


    public Connection openDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            if(connect == null) {
                connect = DriverManager.getConnection(dbURL);
            }
            if (connect != null) {
                DatabaseMetaData dm = (DatabaseMetaData) connect.getMetaData();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.connect;
    }

    public void closeDatabase() {
        try {
            this.connect.close();
            connect = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllConfigure(ResultSet resultSet, Statement stmt, Connection connection){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBControl openDB(){
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl DBControls = new DBControl(connection);
        return DBControls;
    }
}
