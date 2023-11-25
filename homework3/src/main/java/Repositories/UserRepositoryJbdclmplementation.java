package Repositories;

import Models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJbdclmplementation implements UserRepository {
    private final Connection connection;
    private final Statement statement;

    private static final String SQL_SELECT_FROM_DRIVER = "select id,first_name, email, password, confirm_password from users";

    public UserRepositoryJbdclmplementation(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

   @Override
    public List findAll() {
        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("password" ))
                        .password(resultSet.getString("password"))
                        .confirm_password(resultSet.getString("confirm_password"))
                        .build();

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
