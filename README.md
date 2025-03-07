My Application

Prerequisites
Before running the application, make sure you have installed the following:

Java 17+
Maven
PostgreSQL (or the database engine you are using)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Database Configuration

The database configuration is located in the utilities package within the project. Make sure to update the credentials in ConnectionUtil.java:
    public class ConnectionUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USER = "myuser";
    private static final String PASSWORD = "mypassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
How to Run the Application

You can run the application in two ways:
1. Using Maven
   
mvn clean install
mvn exec:java
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2. Using Java and the Generated JAR

mvn clean package
java -jar target/myapp-1.0.jar
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Running Unit Tests
The application uses JUnit and Mockito for unit testing. To run the tests:
mvn test

