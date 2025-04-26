package H34576;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/bank";  // Replace with your DB URL
        String username = "root";  // Replace with your MySQL username
        String password = "admin";  // Replace with your MySQL password

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to MySQL database!");

            // Prepare the SQL INSERT statement
            String sql = "INSERT INTO EBACCOUNT (E_NO, E_NAME, E_MAIL, E_ADDRESS, E_DESIGNATION, E_MOBILENO, E_PASS) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the values for each column
            statement.setInt(1, 12345);          // E_NO
            statement.setString(2, "John Doe");    // E_NAME
            statement.setString(3, "admin.doe@mail.com");  // E_MAIL
            statement.setString(4, "123 Street");  // E_ADDRESS
            statement.setString(5, "Administrator");     // E_DESIGNATION
            statement.setString(6, "9876543210");  // E_MOBILENO
            statement.setString(7, "admin"); // E_PASS

            // Execute the INSERT statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
            }

            // Close the connection
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database");
            e.printStackTrace();
        }
    }
}
