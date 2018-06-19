package br.com.cedro;

import android.app.Application;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.inject.Inject;

import br.com.cedro.business.AmbienteManager;
import br.com.cedro.di.app.AppComponent;
import br.com.cedro.di.app.AppModule;
import br.com.cedro.di.app.DaggerAppComponent;
import br.com.cedro.network.APIClient;
import br.com.cedro.utils.Utils;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class GerenciadorApplication extends Application {

    private static GerenciadorApplication instance;
    private AppComponent mAppComponent;
    private HashMap<String, Object> attributes = new HashMap<String, Object>();

    @Inject
    public APIClient mApiClient;

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
        initializeAPIService();
    }

    private void initializeAPIService() {
        Format dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern();
        Converter.Factory converter = GsonConverterFactory.create(Utils.getGsonDate(pattern));

        mApiClient = new APIClient(converter,getString(R.string.url_biis_prod), this);
        mApiClient.setContentType(getString(R.string.content_type));

        AmbienteManager ambienteManager = AmbienteManager.getInstance();
        ambienteManager.startSession( getApplicationContext() );
        put(AmbienteManager.KEY, ambienteManager);

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

    public void put(final String key, final Object value) {
        this.attributes.put(key, value);
    }

    public Object get(final String key) {
        return this.attributes.get(key);
    }

    public APIClient getApiClient() {
        return mApiClient;
    }

}
