package Repositories;

import Models.User;

import Models.User;

import java.sql.SQLException;

public interface AccountRepository {
    void save(User user) throws SQLException;
}
