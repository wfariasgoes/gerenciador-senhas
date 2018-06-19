package br.com.cedro.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.cedro.GerenciadorApplication;
import br.com.cedro.R;
import br.com.cedro.facade.ManagementBO;
import br.com.cedro.model.User;
import br.com.cedro.network.GerenciadorService;
import br.com.cedro.network.request.UrlResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrlSitesAdapter extends RecyclerView.Adapter<UrlSitesAdapter.Holder> {

    private List<User> userList;
    private UrlsListener listener;
    private Context context;
    private String token;

    public UrlSitesAdapter(UrlsListener listener, List<User> userList, Context context, String token) {
        this.userList = userList;
        this.listener = listener;
        this.context = context;
        this.token = token;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.urls_item, null, false);
        return new UrlSitesAdapter.Holder(row);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final User user = userList.get(position);
        if(userList != null){
//            holder.mImagePhoto.
            holder.mName.setText(user.getName());
            holder.mEmail.setText(user.getEmail());
            holder.mUrl.setText(user.getUrl());

            GerenciadorApplication.getInstance()
                    .getApiClient()
                    .getRetrofit().create(GerenciadorService.class)
                    .getLogo(this.token, user.getUrl())
                    .enqueue(new Callback<UrlResponse>() {
                        @Override
                        public void onResponse(Call<UrlResponse> call, Response<UrlResponse> response) {

                            if (response.isSuccessful()){
                                Log.d("FALHA", "");
                            }else {
                                Log.d("FALHA 2", response.message());
                            }
                            Picasso.with(context)
                                    .load(response.body().getImage())
                                    .into(holder.mImagePhoto);

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

        holder.mRelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDelete(userList.get(position));
                removeItem(user);
            }
        });

        holder.mRelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickEdit(userList.get(position));
            }
        });

    }



    private void removeItem(User user) {

        int currPosition = userList.indexOf(user);
        userList.remove(currPosition);
//        ManagementBO.getInstance().deleteUser(user);
//        notifyItemRemoved(currPosition);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImagePhoto;
        private TextView mName,mEmail,mUrl;
        private RatingBar avaliacao;
        private RelativeLayout mRelDelete;
        private RelativeLayout mRelEdit;

        public Holder(View itemView) {
            super(itemView);
            mImagePhoto = (ImageView) itemView.findViewById(R.id.item_poster);
            mName = (TextView) itemView.findViewById(R.id.tv_name_coutry);
            mEmail = (TextView) itemView.findViewById(R.id.tv_name_capital);
            mUrl = (TextView) itemView.findViewById(R.id.item_data);
            mRelDelete = (RelativeLayout) itemView.findViewById(R.id.delete_button);
            mRelEdit = (RelativeLayout) itemView.findViewById(R.id.edit_button);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public interface UrlsListener{
        void onClickDelete(User user);
        void onClickEdit(User user);
    }

}
