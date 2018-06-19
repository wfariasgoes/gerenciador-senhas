package br.com.cedro.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import br.com.cedro.BR;

public class UserRegister extends BaseObservable implements Parcelable{
    private String name;
    private String email;
    private String password;
    private String nomeError;
    private String emailError;
    private String senhaError;
    private boolean enterButton;

    public UserRegister(String nome, String email, String senha, boolean enterButton) {
        this.name = nome;
        this.email = email;
        this.password = senha;
        this.enterButton = enterButton;
    }

    public UserRegister() {
    }

    protected UserRegister(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        nomeError = in.readString();
        emailError = in.readString();
        senhaError = in.readString();
        enterButton = in.readByte() != 0;
    }

    public static final Creator<UserRegister> CREATOR = new Creator<UserRegister>() {
        @Override
        public UserRegister createFromParcel(Parcel in) {
            return new UserRegister(in);
        }

        @Override
        public UserRegister[] newArray(int size) {
            return new UserRegister[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }


    @Bindable
    public boolean isEnterButton() {
        return enterButton;
    }

    public void setEnterButton(boolean enterButton) {
        notifyPropertyChanged(BR.enterButton);
        this.enterButton = enterButton;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(nomeError);
        dest.writeString(emailError);
        dest.writeString(senhaError);
        dest.writeByte((byte) (enterButton ? 1 : 0));
    }
}
