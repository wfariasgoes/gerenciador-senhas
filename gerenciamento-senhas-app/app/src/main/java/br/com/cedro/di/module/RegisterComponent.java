package br.com.cedro.di.module;

import br.com.cedro.di.Activity;
import br.com.cedro.di.app.AppComponent;
import br.com.cedro.view.activity.RegisterActivity;
import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = RegisteModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
