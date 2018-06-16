package br.com.cedro.di.module;

import br.com.cedro.di.Activity;
import br.com.cedro.di.app.AppComponent;
import br.com.cedro.view.activity.LoginActivity;
import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}

