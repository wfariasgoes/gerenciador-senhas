package br.com.cedro.di.module;

import br.com.cedro.di.Activity;
import br.com.cedro.model.UserLogin;
import br.com.cedro.view.activity.LoginActivity;
import br.com.cedro.viewmodel.LoginViewmodel;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginViewmodel.LoginListener listener;
    private UserLogin userLogin;

    public LoginModule(LoginViewmodel.LoginListener listener, UserLogin userLogin) {
        this.listener = listener;
        this.userLogin = userLogin;
    }


    @Provides
    @Activity
    LoginViewmodel providerLoginViewmodel(){
        return new LoginViewmodel(listener, userLogin);
    }

}
