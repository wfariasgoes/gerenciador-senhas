package br.com.cedro.facade;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.business.AmbienteManager;
import br.com.cedro.business.ObjectAlreadyExistException;
import br.com.cedro.model.User;

public class ManagementBO {


    private static ManagementBO instance;
    private static final Object SYNCOBJECT = new Object();
    private AmbienteManager mAmbienteManager;

//    private Country mCountrieSelection;


    public static ManagementBO getInstance() {
        synchronized (SYNCOBJECT) {
            if (instance == null) {
                instance = new ManagementBO();
            }
        }
        return instance;
    }



    /**          ********************** USER *******************                   **/

     /*
    * Adicionar User
     */
    public void addUser(final User user) {
        mAmbienteManager = (AmbienteManager) GerenciadorApplication.getInstance().get(AmbienteManager.KEY);
        try {
            mAmbienteManager.insertUser(user);
        } catch (SQLException e) {
            Log.d("Erro ao adicionar uma nova pesquisa" , ""+e.getMessage());
        } catch (ObjectAlreadyExistException e) {
            e.getMessage();
        }
    }

    /*
    * Update
     */
    public void updateUser(User user) {
        mAmbienteManager = (AmbienteManager) GerenciadorApplication.getInstance().get(AmbienteManager.KEY);
        try {
            mAmbienteManager.updateUser(user);
        } catch (SQLException e) {
            Log.i("","Erro ao atualizar Url " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        mAmbienteManager = (AmbienteManager) GerenciadorApplication.getInstance().get(AmbienteManager.KEY);
        List<User> questions = new ArrayList<User>();
        try {
            questions = mAmbienteManager.getUsers();
        } catch (SQLException e) {
            Log.i(""," Não foi possível consultar dados cadastrados. " + e.getMessage());
        }
        return questions;
    }

    public void deleteUser(final User user) {
        mAmbienteManager = (AmbienteManager) GerenciadorApplication.getInstance().get(AmbienteManager.KEY);
        try {
            mAmbienteManager.deleteUser(user);
        } catch (SQLException e) {
            Log.i("Erro ao excluir um Item" ,e.getMessage());
        }
    }

}
