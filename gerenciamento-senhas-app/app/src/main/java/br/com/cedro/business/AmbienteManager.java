package br.com.cedro.business;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import br.com.cedro.model.User;
import br.com.cedro.persistence.DataBaseHelper;


public class AmbienteManager implements OnGerentialsBusinessLogic {

    public static final String KEY = "ModelBO";
    private DataBaseHelper dataBaseHelper;
    private static AmbienteManager SINGLETON;

    public static AmbienteManager getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new AmbienteManager();
        }
        return SINGLETON;
    }

    public void startSession(Context ctx) {
        dataBaseHelper = new DataBaseHelper(ctx);
        dataBaseHelper.getWritableDatabase();
    }




    //*****************     User   ***********************

    @Override
    public void insertUser(User user) throws SQLException, ObjectAlreadyExistException {
        Dao<User, Long> dao = dataBaseHelper.getDao(User.class);
        dataBaseHelper.getDao(User.class).createOrUpdate(user);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> result = null;
        QueryBuilder queryBuilder = dataBaseHelper.getDao(User.class).queryBuilder();
        result = queryBuilder.query();
        return result;
    }

    @Override
    public List<User> getSearchUsers() throws SQLException {
        Dao<User, Long> dao =  dataBaseHelper.getDao( User.class );
        List<User> result = dao.queryForEq( "name" , true);
        return result;
    }


    @Override
    public void updateUser(User user) throws SQLException {
        UpdateBuilder updateBuilder = dataBaseHelper.getDao(User.class).updateBuilder();
        updateBuilder.where().eq("id",user.getId());
        updateBuilder.updateColumnValue("name",user.getName());
        updateBuilder.updateColumnValue("password",user.getPassword());
        updateBuilder.updateColumnValue("email",user.getEmail());
        updateBuilder.updateColumnValue("url",user.getUrl());
        updateBuilder.update();

    }

    @Override
    public void deleteUser(User user) throws SQLException {
        dataBaseHelper.getDao(User.class).delete(user);
    }


}
