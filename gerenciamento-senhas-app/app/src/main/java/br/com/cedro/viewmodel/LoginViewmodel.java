package br.com.cedro.viewmodel;

import android.util.Log;

import java.io.IOException;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.model.UserLogin;
import br.com.cedro.network.GerenciadorService;
import br.com.cedro.network.request.LoginRequest;
import br.com.cedro.network.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewmodel {

    private LoginListener listener;
    public UserLogin mUserLogin;

    public LoginViewmodel(LoginListener listener, UserLogin mUserLogin) {
        this.listener = listener;
        this.mUserLogin = mUserLogin;
    }

    public void onEnter(UserLogin userData) {
        LoginRequest userRequest = new LoginRequest();
        userRequest.setEmail(userData.getLogin().toString());
        userRequest.setPassword(userData.getPassword().toString());

        if (listener.validateFields(userData.getLogin(), userData.getPassword())) {
            GerenciadorApplication.getInstance()
                    .getApiClient()
                    .getRetrofit().create(GerenciadorService.class)
                    .getUser(userRequest)
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Log.i("LOG__E", response.message());
                            Log.i("LOG__E 1", response.message().trim());
                            Log.i("LOG__E 2", ""+response.code());
                            if(response.isSuccessful()){
                                listener.onSuccess(response.body());
                            }else{
                                try {
                                    listener.onError(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            listener.onFailure(t.getMessage());
                        }
                    });

        }


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
        void onSuccess(LoginResponse body);
        void onError(String code);
        void onFailure(String message);
        boolean validateFields(String login, String password);
    }
}
