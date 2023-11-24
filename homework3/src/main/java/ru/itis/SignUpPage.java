package ru.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/")
public class SignUpPage extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "reallyStrongPwd123";

    @Override
    public void init() throws ServletException {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the signUP.html page
        RequestDispatcher dispatcher = request.getRequestDispatcher("userRegister.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get connection to the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

                // Disable auto-commit to manage transactions manually
                connection.setAutoCommit(false);

                // Prepare SQL statement for insertion
                String sqlInsertUser = "INSERT INTO users (first_name, last_name, email, password, confirm_password) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser)) {

                    // Get parameters from the request
                    String firstName = request.getParameter("first_name");
                    String lastName = request.getParameter("last_name");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirm_password");

                    // Set the values in the prepared statement
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, password);
                    preparedStatement.setString(5, confirmPassword);

                    // Execute the query
                    int affectedRows = preparedStatement.executeUpdate();
                    System.out.printf("Rows added: %d\n", affectedRows);

                    // Commit the transaction
                    connection.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception details
            throw new ServletException("Database access error", e);
        }
    }
}
