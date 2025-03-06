package Revature.com.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

        private static final String URL ="jdbc:postgresql://localhost:5432/postgres";
        private static final String User = "postgres";
        private static final String password = "1234";
        private static Connection connection = null;


        public static Connection getConnection(){
            if(connection == null){
                try {
                    connection = DriverManager.getConnection(URL, User, password);
                    System.out.println("OH SI OH SI");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return connection;
        }
        }


