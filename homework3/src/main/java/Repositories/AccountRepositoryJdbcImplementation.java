package Repositories;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepositoryJdbcImplementation implements AccountRepository {
   private final Connection connection;
    public AccountRepositoryJdbcImplementation(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(User user) throws SQLException {

        String addUsersSQL = "INSERT INTO users(first_name,last_name,email,password,confirm_password)VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addUsersSQL);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4,user.getPassword());
        preparedStatement.setString(5,user.getConfirm_password());

        preparedStatement.executeUpdate();
        System.out.println("Executed");
    }

}
