package br.com.cedro.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.com.cedro.BR;

public class UserLogin extends BaseObservable implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("token")
    private String token;

    @SerializedName("saved")
    private String saved;


    private String loginError;

    private String passwordError;

    private boolean enterButton;

    public UserLogin(String login, String password, boolean enterButton) {
        this.login = login;
        this.password = password;
        this.enterButton = enterButton;
    }

    public UserLogin() {
    }

    protected UserLogin(Parcel in) {
        login = in.readString();
        loginError = in.readString();
        password = in.readString();
        token = in.readString();
        saved = in.readString();
        passwordError = in.readString();
        enterButton = in.readByte() != 0;
    }

    public static final Creator<UserLogin> CREATOR = new Creator<UserLogin>() {
        @Override
        public UserLogin createFromParcel(Parcel in) {
            return new UserLogin(in);
        }

        @Override
        public UserLogin[] newArray(int size) {
            return new UserLogin[size];
        }
    };

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String cpf) {
        this.login = cpf;
        notifyPropertyChanged(BR.login);
//        if(!Validator.isCPFValid(this.cpf)){
//            setLoginError(null);
//            if(cardNumber.length() >= 2){
//                setEnterButton(true);
//            }else{
//                setEnterButton(false);
//            }
//        }else{
//            setEnterButton(false);
//        }

    }

    @Bindable
    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
        notifyPropertyChanged(BR.loginError);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String cardNumber) {
        this.password = cardNumber;
        notifyPropertyChanged(BR.password);
//        if (Validator.isEmailValid(this.cardNumber)) {
//            setPasswordError(null);
//            if(cardNumber.length() >= 16)
//                setEnterButton(true);
//            else
//                setEnterButton(false);
//        } else {
//            setEnterButton(false);
//        }
    }

    @Bindable
    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    @Bindable
    public boolean isEnterButton() {
        return enterButton;
    }

    public void setEnterButton(boolean enterButton) {
        notifyPropertyChanged(BR.enterButton);
        this.enterButton = enterButton;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(loginError);
        dest.writeString(password);
        dest.writeString(token);
        dest.writeString(saved);
        dest.writeString(passwordError);
        dest.writeByte((byte) (enterButton ? 1 : 0));
    }
}
