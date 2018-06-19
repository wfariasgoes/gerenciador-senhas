package br.com.cedro.view.fragment;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.R;
import br.com.cedro.databinding.FragmentSitesBinding;
import br.com.cedro.databinding.LayoutAddSitesBinding;
import br.com.cedro.facade.ManagementBO;
import br.com.cedro.helper.SaveImageHelper;
import br.com.cedro.model.User;
import br.com.cedro.network.GerenciadorService;
import br.com.cedro.network.request.UrlResponse;
import br.com.cedro.network.response.RegisterResponse;
import br.com.cedro.view.adapter.UrlSitesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SitesFragment extends Fragment implements UrlSitesAdapter.UrlsListener{

    FragmentSitesBinding binding;
    private List<User> userList;
    private UrlSitesAdapter adapter;

    LayoutAddSitesBinding addSitesBinding;
    private String token;
    private static final String TOKEN = "token";

    public SitesFragment() {
        // Required empty public constructor
    }

    public static SitesFragment newInstance(String token) {
        Bundle args = new Bundle();
        args.putString(TOKEN, token);
        SitesFragment fragment = new SitesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sites, container, false);
        getDatas();
        binding.fabWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.token = getArguments().getString(TOKEN);
        }
    }

    public void getDatas() {
        userList = new ArrayList<>();
        userList = ManagementBO.getInstance().getUsers();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerSites.setHasFixedSize(true);
        binding.recyclerSites.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        binding.recyclerSites.setLayoutManager(layoutManager);

        adapter = new UrlSitesAdapter(this, userList, getContext());
        binding.recyclerSites.setAdapter(adapter);

//        if (!userList.isEmpty() && userList != null) {
//            binding.recyclerSites.setVisibility(View.VISIBLE);
//            binding.tvNotFound.setVisibility(View.GONE);
//        } else {
//            binding.recyclerSites.setVisibility(View.GONE);
//            binding.tvNotFound.setVisibility(View.VISIBLE);
//        }

    }
    private void showLoginDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("SALVAR URL");

        dialog.setMessage("Preencha os campos para salvar no disponitivo.");

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View registerLayout = inflater.inflate(R.layout.layout_add_sites, null);

        dialog.setView(registerLayout);

        TextView tvSearchUrlImage =  registerLayout.findViewById(R.id.tvSearchUrlImage);
        final EditText edtUrl = registerLayout.findViewById(R.id.edtUrl);
        final EditText edtPassword = registerLayout.findViewById(R.id.edtPassword);
        final EditText edtEmail = registerLayout.findViewById(R.id.edtEmail);
        final EditText edtName = registerLayout.findViewById(R.id.edtName);


        tvSearchUrlImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageUrl(dialog,edtUrl.getText().toString());
            }
        });

        dialog.setPositiveButton("SALVAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                User user = new User();
                user.setName(edtName.getText().toString());
                user.setEmail(edtEmail.getText().toString());
                user.setUrl(edtUrl.getText().toString());
                user.setPassword(edtPassword.getText().toString());

                ManagementBO.getInstance().addUser(user);
                getDatas();
                Toast.makeText(getActivity(), "Dados salvos!", Toast.LENGTH_SHORT).show();
            }
        });

        //Opção para cancelar
        dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }

    private void loadImageUrl(final AlertDialog.Builder dialog, String text) {

        GerenciadorApplication.getInstance()
                .getApiClient()
                .getRetrofit().create(GerenciadorService.class)
                .getLogo(this.token, text)
                .enqueue(new Callback<UrlResponse>() {
                    @Override
                    public void onResponse(Call<UrlResponse> call, Response<UrlResponse> response) {

                        if (response.isSuccessful()){
                            Log.d("FALHA", "");
                        }else {
                            Log.d("FALHA 2", response.message());
                        }
                        Picasso.with(getActivity())
                                .load(response.body().getImage().getPath())
                                .into(binding.imgWallpaperItem);
//                        Picasso.with(getActivity().getBaseContext())
//                                .load(response.body().getImage())
//                                .into(new SaveImageHelper(getActivity().getBaseContext(),
//                                        dialog,
//                                        getActivity().getApplicationContext().getContentResolver(),
//                                        fileName,
//                                        "WFGDev Live Wallpaper Image"));


                    }

                    @Override
                    public void onFailure(Call<UrlResponse> call, Throwable t) {
                        Log.d("FALHA", t.getMessage());
                    }
                });
    }

    @Override
    public void onClickDelete(User user) {
        ManagementBO.getInstance().deleteUser(user);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Deletado com sucesso: ", Toast.LENGTH_SHORT).show();
    }
}
