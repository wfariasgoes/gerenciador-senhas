package br.com.cedro.network.response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("type")
    private String type;

    @SerializedName("token")
    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
