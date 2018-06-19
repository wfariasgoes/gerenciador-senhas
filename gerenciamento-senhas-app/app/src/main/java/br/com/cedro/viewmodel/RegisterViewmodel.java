package br.com.cedro.viewmodel;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.model.UserRegister;
import br.com.cedro.network.GerenciadorService;
import br.com.cedro.network.request.RegisterRequest;
import br.com.cedro.network.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewmodel {
    private RegisterListener listener;
    public UserRegister mUserRegister;

    public RegisterViewmodel(RegisterListener listener, UserRegister mUserRegister) {
        this.listener = listener;
        this.mUserRegister = mUserRegister;
    }

    public void userRegister(UserRegister userRegister){

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(userRegister.getEmail().toString());
        registerRequest.setName(userRegister.getName().toString());
        registerRequest.setPassword(userRegister.getPassword().toString());

        GerenciadorApplication.getInstance()
                .getApiClient()
                .getRetrofit().create(GerenciadorService.class)
                .addUser(registerRequest)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if(response.isSuccessful()){
                            listener.onSuccess(response.body());
                        }else{
                            listener.onError(response.message().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        listener.onFailure(t.getMessage());
                    }
                });
    }


    public void onClickSave(){
        listener.onClickSave();
    }
    public void onCliciBack(){
        listener.onClickBack();
    }
    public interface RegisterListener{
        void onClickSave();
        void onSuccess(RegisterResponse body);
        void onError(String responseBody);
        void onFailure(String message);
        void onClickBack();
    }
}
