package br.com.cedro.business;

import java.sql.SQLException;
import java.util.List;

import br.com.cedro.model.User;


public interface OnGerentialsBusinessLogic {

    void insertUser(User user) throws SQLException, ObjectAlreadyExistException;
    List<User> getUsers() throws SQLException;
    List<User> getSearchUsers() throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;
}
