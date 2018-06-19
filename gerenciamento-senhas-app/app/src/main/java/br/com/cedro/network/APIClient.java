package br.com.cedro.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class APIClient {

    static final Object TAG_CALL = new Object();
    private static APIClient mInstance;
    private OkHttpClient mOKOkHttpClient;

    public synchronized static APIClient getInstance() {
        return mInstance;
    }

    private Retrofit mRetrofit;
    private OkHttpClient mClient;


    public APIClient(@Nullable Converter.Factory converterFactory, @NonNull String baseUrl, @NonNull Context context) {
        mInstance = this;


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(getLoggingCapableHttpClient())
                .addInterceptor(new AddHeaderInterceptor()).build();

        if (converterFactory != null) {
            mRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(converterFactory)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } else {
            mRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public static HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return mLogging;
    }

    public class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Content-Type", "application/json");

            return chain.proceed(builder.build());
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }


    public void setContentType(String contentType) {
        NetWorkSetup.setContentType(contentType);
    }

}
