package br.com.cedro.di.app;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    SharedPreferences sharedPreferences();
}
