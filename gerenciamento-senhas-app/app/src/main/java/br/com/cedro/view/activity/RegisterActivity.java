package br.com.cedro.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import javax.inject.Inject;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.R;
import br.com.cedro.databinding.ActivityRegisterBinding;
import br.com.cedro.di.module.DaggerRegisterComponent;
import br.com.cedro.di.module.RegisteModule;
import br.com.cedro.model.UserRegister;
import br.com.cedro.network.response.RegisterResponse;
import br.com.cedro.view.BasicActivity;
import br.com.cedro.viewmodel.RegisterViewmodel;

import static br.com.cedro.utils.Utils.regexCharactersSpecial;
import static br.com.cedro.utils.Utils.regexNumber;
import static br.com.cedro.utils.Utils.regexNumberCharactersSpecial;
import static br.com.cedro.utils.Utils.regexNumberString;
import static br.com.cedro.utils.Utils.regexNumberStringCharactersSpecial;
import static br.com.cedro.utils.Utils.regexStringCharactersSpecial;
import static br.com.cedro.utils.Utils.regexStrings;

public class RegisterActivity extends BasicActivity implements RegisterViewmodel.RegisterListener{

    ActivityRegisterBinding binding;
    private String name;
    private String email;
    private String password;
    private UserRegister userRegister;

    /**
     * nome: Wesley Dois
     * email:wesley2@gmail.com
     * senha:G10v#nnagoes
     * **/

    @Inject
    RegisterViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initIjection();
        userRegister = new UserRegister();
        initBinding();
        binding.setRegisteUser(viewmodel);
    }

    private void initBinding() {
        binding.btAdvance.setEnabled(false);
        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    getPasswordInvalid();
                } else {
                    if (regexNumber(s) || regexStrings(s) || regexCharactersSpecial(s))
                        getForceWeak();
                    if (regexNumberString(s) || regexNumberCharactersSpecial(s) || regexStringCharactersSpecial(s))
                        getForceAvarage();
                    if (regexNumberStringCharactersSpecial(s))
                        getForceStrong();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initIjection() {
        DaggerRegisterComponent.builder()
                .appComponent(((GerenciadorApplication) getApplication()).getAppComponent())
                .registeModule(new RegisteModule(this, new UserRegister("","","", false)))
                .build().inject(this);
    }

    @Override
    public void onClickSave() {
        userRegister.setName(binding.etNameFull.getText().toString());
        userRegister.setEmail(binding.etYourEmail.getText().toString());
        userRegister.setPassword(binding.etPassword.getText().toString());
        viewmodel.userRegister(userRegister);


    }

    @Override
    public void onSuccess(RegisterResponse body) {
        if(body.getToken() != null){
            Bundle bundle = new Bundle();
            Intent intent=new Intent(this, HomeActivity.class);
            bundle.putString("name",binding.etNameFull.getText().toString());
            bundle.putString("token",body.getToken());
            bundle.putString("email",binding.etYourEmail.getText().toString());
            bundle.putString("password",binding.etPassword.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    public void getForceWeak() {
        binding.vLineOne.setBackground(getResources().getDrawable(R.drawable.bg_view_line_red));
        binding.vLineTwo.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.vLineThree.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.tvForcePassword.setTextColor(getResources().getColor(R.color.colorRed));
        binding.tvForcePassword.setText(GerenciadorApplication.getInstance().getResources().getString(R.string.act_register_tv_pw_weak));
        binding.btAdvance.setEnabled(false);
    }

    public void getForceAvarage() {
        binding.vLineOne.setBackground(getResources().getDrawable(R.drawable.bg_view_line_yellow));
        binding.vLineTwo.setBackground(getResources().getDrawable(R.drawable.bg_view_line_yellow));
        binding.vLineThree.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.tvForcePassword.setTextColor(getResources().getColor(R.color.colorYellow));
        binding.tvForcePassword.setText(GerenciadorApplication.getInstance().getResources().getString(R.string.act_register_tv_pw_avarage));
        binding.btAdvance.setEnabled(false);
    }

    public void getForceStrong() {
        binding.vLineOne.setBackground(getResources().getDrawable(R.drawable.bg_view_line_green));
        binding.vLineTwo.setBackground(getResources().getDrawable(R.drawable.bg_view_line_green));
        binding.vLineThree.setBackground(getResources().getDrawable(R.drawable.bg_view_line_green));
        binding.tvForcePassword.setTextColor(getResources().getColor(R.color.colorDarkGreen));
        binding.tvForcePassword.setText(GerenciadorApplication.getInstance().getResources().getString(R.string.act_register_tv_pw_strong));
        binding.btAdvance.setEnabled(true);
    }

    public void getPasswordInvalid() {
        int count = binding.etPassword.getText().length();
        Log.v("REGISTER_LENTH", ""+count);
        binding.vLineOne.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.vLineTwo.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.vLineThree.setBackground(getResources().getDrawable(R.drawable.bg_view_line_gray));
        binding.tvForcePassword.setTextColor(getResources().getColor(R.color.colorTextTitle));
        binding.tvForcePassword.setText("");
        binding.btAdvance.setEnabled(false);


    }

    @Override
    public void onError(String responseBody) {
        Log.v("REGISTER_RESPONSE 1", ""+responseBody);
    }

    @Override
    public void onFailure(String message) {
        Log.v("REGISTER_RESPONSE 2", ""+message);
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }
}
