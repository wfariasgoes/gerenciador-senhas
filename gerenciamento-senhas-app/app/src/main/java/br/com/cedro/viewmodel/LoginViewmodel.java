package br.com.cedro.viewmodel;

import br.com.cedro.model.UserLogin;

public class LoginViewmodel {

    private LoginListener listener;
    public UserLogin mUserLogin;

    public LoginViewmodel(LoginListener listener, UserLogin mUserLogin) {
        this.listener = listener;
        this.mUserLogin = mUserLogin;
    }

    public void onLoadLogin(){

    }

    public void onClickSignIn(){
        listener.onClickSignIn();
    }

    public void onClickSignUp(){
        listener.onClickSignUp();
    }


    public interface LoginListener{
        void onClickSignIn();
        void onClickSignUp();
        void onSuccess();
        void onError();
    }
}
