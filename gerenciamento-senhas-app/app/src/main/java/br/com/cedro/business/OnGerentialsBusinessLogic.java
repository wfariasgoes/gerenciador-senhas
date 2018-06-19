package br.com.cedro.business;

import java.sql.SQLException;
import java.util.List;

import br.com.cedro.model.User;


public interface OnGerentialsBusinessLogic {

    void insertUser(User user) throws SQLException, ObjectAlreadyExistException;
    List<User> getUsers() throws SQLException;
    public List<User> getSearchUsers() throws SQLException;
    public void updateUser(User user) throws SQLException;
    public void deleteUser(User user) throws SQLException;
}
