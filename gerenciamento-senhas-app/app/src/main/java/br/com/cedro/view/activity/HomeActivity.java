package br.com.cedro.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cedro.R;
import br.com.cedro.databinding.ActivityHomeBinding;
import br.com.cedro.facade.ManagementBO;
import br.com.cedro.model.User;
import br.com.cedro.view.adapter.MyFragmentAdapter;
import br.com.cedro.view.fragment.SecondFragment;
import br.com.cedro.view.fragment.SitesFragment;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private MyFragmentAdapter adapter;
    private String name, email, password,token;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initView();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, SitesFragment.newInstance(token))
                    .commit();

        }
        setSupportActionBar(binding.toolbar);
    }

    private void initView() {
        user = new User();

        email = getIntent().getExtras().getString("email");
        password = getIntent().getExtras().getString("password");
        token = getIntent().getExtras().getString("token");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        if (getIntent() != null && getIntent().hasExtra("name")){
            name = getIntent().getExtras().getString("name");
            binding.toolbar.setTitle("Olá "+name);
//            user.setName(name);
        }else{
            binding.toolbar.setTitle("Olá "+email);
        }
//        user.setEmail(email);
//        user.setPassword(password);
//        ManagementBO.getInstance().addUser(user);

    }

    private void initBinds() {
//        adapter = new MyFragmentAdapter(getSupportFragmentManager(), this);
//        binding.viewPager.setAdapter(adapter);
//        binding.tabLayout.setupWithViewPager(binding.viewPager);
//        setupViewPager(binding.viewPager);
    }

    public void setupViewPager(ViewPager viewPager){
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), this);
        adapter.addFrag(SitesFragment.newInstance(token),"Sites");
        adapter.addFrag(SecondFragment.newInstance("", ""), "Recentes");
        viewPager.setAdapter(adapter);
    }
}
