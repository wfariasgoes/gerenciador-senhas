package br.com.cedro.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.R;
import br.com.cedro.databinding.ActivityLoginBinding;
import br.com.cedro.di.module.DaggerLoginComponent;
import br.com.cedro.di.module.LoginModule;
import br.com.cedro.model.UserLogin;
import br.com.cedro.view.BasicActivity;
import br.com.cedro.viewmodel.LoginViewmodel;

public class LoginActivity extends BasicActivity implements LoginViewmodel.LoginListener{

    ActivityLoginBinding binding;

    @Inject
    LoginViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initInjectors();
        binding.setLoginUser(viewmodel);
    }

    private void initInjectors() {
        DaggerLoginComponent.builder()
                .appComponent(((GerenciadorApplication) getApplication()).getAppComponent())
                .loginModule(new LoginModule(this, new UserLogin("","",false)))
                .build().inject(this);

    }

    @Override
    public void onClickSignIn() {
        Toast.makeText(this, "SignIn", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickSignUp() {
        startActivity( new Intent( this, RegisterActivity.class));
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
