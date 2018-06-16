package br.com.cedro;

import android.app.Application;

import br.com.cedro.di.app.AppComponent;
import br.com.cedro.di.app.AppModule;
import br.com.cedro.di.app.DaggerAppComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class GerenciadorApplication extends Application {

    private static GerenciadorApplication instance;
    private AppComponent mAppComponent;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public  static GerenciadorApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeInjector();
        initializeCalligraphy();
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault( new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


}
