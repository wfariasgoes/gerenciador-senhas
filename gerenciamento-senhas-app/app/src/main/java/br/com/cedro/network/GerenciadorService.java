package br.com.cedro.network;

import br.com.cedro.network.request.LoginRequest;
import br.com.cedro.network.request.RegisterRequest;
import br.com.cedro.network.request.UrlResponse;
import br.com.cedro.network.response.LoginResponse;
import br.com.cedro.network.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GerenciadorService {

    @POST("login/")
    Call<LoginResponse> getUser(@Body LoginRequest loginRequest);

    @POST("register/")
    Call<RegisterResponse> addUser(@Body RegisterRequest loginRequest);

    @GET("logo/{nomedosite}/")
    Call<UrlResponse> getLogo(@Header("Authorization")  String token, @Path("nomedosite") String nomedosite);
}
