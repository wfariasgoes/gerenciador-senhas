package br.com.cedro.di.module;

import br.com.cedro.di.Activity;
import br.com.cedro.model.UserRegister;
import br.com.cedro.viewmodel.RegisterViewmodel;
import dagger.Module;
import dagger.Provides;

@Module
public class RegisteModule {
    private RegisterViewmodel.RegisterListener listener;
    private UserRegister userRegister;

    public RegisteModule(RegisterViewmodel.RegisterListener listener, UserRegister userRegister) {
        this.listener = listener;
        this.userRegister = userRegister;
    }

    @Provides
    @Activity
    RegisterViewmodel providerRegisterViewmodel(){
        return new RegisterViewmodel(listener,userRegister);
    }
}
