package br.com.cedro.viewmodel;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.model.UserLogin;
import br.com.cedro.network.GerenciadorService;
import br.com.cedro.network.request.LoginRequest;
import br.com.cedro.network.response.LoginResponse;
import br.com.cedro.network.response.RegisterResponse;
import okhttp3.ResponseBody;
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

        GerenciadorApplication.getInstance()
                .getApiClient()
                .getRetrofit().create(GerenciadorService.class)
                .getUser(userRequest)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()){
                            listener.onSuccess(response.body());
                        }else{
                            listener.onError(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        listener.onFailure(t.getMessage());
                    }
                });


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
        void onError(ResponseBody code);
        void onFailure(String message);
    }
}
