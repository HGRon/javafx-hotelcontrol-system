package dataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/TooNearSolutions"
                    , "root",
                    "Hh74290689@"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("ERRO NA CONEXÃO: ", e);
        }

    }

    public  static void closeConnection(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {

        closeConnection(con);


        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public  static void closeConnection(Connection con, PreparedStatement stmt, ResultSet resultSet) throws SQLException {

        closeConnection(con, stmt);

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
