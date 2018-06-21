package br.com.cedro.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.R;
import br.com.cedro.databinding.ActivityLoginBinding;
import br.com.cedro.di.module.DaggerLoginComponent;
import br.com.cedro.di.module.LoginModule;
import br.com.cedro.model.UserLogin;
import br.com.cedro.network.response.LoginResponse;
import br.com.cedro.view.BasicActivity;
import br.com.cedro.viewmodel.LoginViewmodel;

public class LoginActivity extends BasicActivity implements LoginViewmodel.LoginListener{

    ActivityLoginBinding binding;
    AVLoadingIndicatorView avLoadingIndicatorView;
    @Inject
    LoginViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initInjectors();
        avLoadingIndicatorView = new AVLoadingIndicatorView(this);
        binding.avlindicator.setVisibility(View.GONE);
        binding.rltDadosRegister.setVisibility(View.VISIBLE);
        binding.imgLogo.setVisibility(View.VISIBLE);
        binding.btnRegisterUser.setVisibility(View.VISIBLE);
//        if (validateField()) {
//            binding.btAdvance.setEnabled(true);
//        }else{
//            binding.btAdvance.setEnabled(false);
//        }
        binding.setLoginUser(viewmodel);
    }

    private void initInjectors() {
        DaggerLoginComponent.builder()
                .appComponent(((GerenciadorApplication) getApplication()).getAppComponent())
                .loginModule(new LoginModule(this, new UserLogin("","",false)))
                .build().inject(this);

    }

    public boolean validateField() {
        if (!binding.edtUserName.getText().toString().isEmpty() && !binding.edtPassword.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
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
    public void onSuccess(LoginResponse body) {
        Log.v("LOGIN_RESPONSE", body.getToken());

        binding.avlindicator.setVisibility(View.VISIBLE);
        binding.rltDadosRegister.setVisibility(View.GONE);
        binding.imgLogo.setVisibility(View.GONE);
        binding.btnRegisterUser.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        Intent intent=new Intent(this, HomeActivity.class);
        bundle.putString("token",body.getToken());
        bundle.putString("email",binding.edtUserName.getText().toString());
        bundle.putString("password",binding.edtPassword.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    @Override
    public void onError(String responseBody) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ALERTA");
        String errorB= null;
        if (responseBody.contains("Invalid e-mail")) {
            errorB = "Seu e-mail está incorreto.";
        } else if (responseBody.contains("Invalid e-mail or password")) {
            errorB = "E-mail ou senha incorreto.";
        }

        dialog.setMessage(errorB);
        //Opção para cancelar
        dialog.setNegativeButton("VOLTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onFailure(String message) {
        Log.e("LOGIN_RESPONSE", ""+message);
    }

    @Override
    public boolean validateFields(String login, String password) {
        if (login.isEmpty()) {
            binding.edtUserName.setError(getString(R.string.error_required_field));
            binding.edtUserName.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            binding.edtPassword.setError(getString(R.string.errorrequired_pass_not_empty));
            binding.edtPassword.requestFocus();
            binding.inputPassword.setPasswordVisibilityToggleEnabled(false);
            return false;
        }

        binding.inputPassword.setPasswordVisibilityToggleEnabled(true);
        return true;
    }
}
