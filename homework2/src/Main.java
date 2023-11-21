import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "reallyStrongPwd123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);

            // Search for a user by name
            System.out.println("Enter name to search:");
            String searchName = scanner.next();
            searchUserByName(searchName);

            // Display all users
            System.out.println("All users:");
            displayAllUsers();

            System.out.println("all the names on our site");
            getAllUsernames();

            System.out.println("please enter your data");
            addUsers();

    }

    public static void searchUserByName(String name) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE first_name = ?");

        preparedStatement.setString(1, name);

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            System.out.println(result.getInt("id") + " - " + result.getString("first_name") +
                    " - " + result.getString("email") + " - " + result.getInt("age"));
        }else {
            System.out.println("----------------------------------------------------------------------\n user not found\n---------------------------------------------------------------------------------");
        }

    }

    public static void getAllUsernames() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement() ;

        ResultSet result = statement.executeQuery("SELECT * FROM accounts");

        while (result.next()) {
            System.out.println(result.getInt("id") + " - " + result.getString("first_name") +
                    " - " + result.getString("email") + " - " + result.getInt("age"));
        }
    }
    public static void displayAllUsers() throws Exception{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();

        // Fetch data from the database
        ResultSet result = statement.executeQuery("SELECT * FROM accounts");

        // Display existing data
        while (result.next()) {
            System.out.println(result.getInt("id") + " - " + result.getString("first_name") +
                    " - " + result.getString("email") + " - " + result.getInt("age"));
        }
    }

    public static void addUsers() throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        // Get new user data from the user through the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        String firstName = scanner.nextLine();
        System.out.println("email:");
        String email = scanner.nextLine();
        System.out.println("Enter age:");
        int age = scanner.nextInt();

        // Use PreparedStatement to insert new user (prevents SQL injection)
        String sqlInsertUser = "INSERT INTO accounts (first_name, email, age) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, age);

        // Execute the query
        int affectedRows = preparedStatement.executeUpdate();
        System.out.printf("Rows added: %d\n", affectedRows);

    }
}



