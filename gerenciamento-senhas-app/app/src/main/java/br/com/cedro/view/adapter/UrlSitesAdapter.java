package br.com.cedro.view.adapter;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.InputStream;
import java.util.List;

import br.com.cedro.R;
import br.com.cedro.facade.ManagementBO;
import br.com.cedro.model.User;

public class UrlSitesAdapter extends RecyclerView.Adapter<UrlSitesAdapter.Holder> {

    private List<User> userList;
    private UrlsListener listener;
    private Context context;

    public UrlSitesAdapter(UrlsListener listener, List<User> userList, Context context) {
        this.userList = userList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.urls_item, null, false);
        return new UrlSitesAdapter.Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final User user = userList.get(position);
        if(userList != null){
//            holder.mImagePhoto.
            holder.mName.setText(user.getName());
            holder.mCapital.setText(user.getEmail());
            holder.mDate.setText(user.getUrl());
//            Picasso.with(context)
//                    .load(user.getUrlImageCountry())
//                    .into(holder.mImagePhoto);

//            Uri uri = Uri.parse(user.getUrlImageCountry());



        }

        holder.mRelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDelete(userList.get(position));
                removeItem(user);
            }
        });


    }



    private void removeItem(User user) {

        int currPosition = userList.indexOf(user);
        userList.remove(currPosition);
        ManagementBO.getInstance().deleteUser(user);
        notifyItemRemoved(currPosition);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImagePhoto;
        private TextView mName,mCapital,mDate;
        private RatingBar avaliacao;
        private RelativeLayout mRelDelete;

        public Holder(View itemView) {
            super(itemView);
            mImagePhoto = (ImageView) itemView.findViewById(R.id.item_poster);
            mName = (TextView) itemView.findViewById(R.id.tv_name_coutry);
            mCapital = (TextView) itemView.findViewById(R.id.tv_name_capital);
            mDate = (TextView) itemView.findViewById(R.id.item_data);
            mRelDelete = (RelativeLayout) itemView.findViewById(R.id.delete_button);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public interface UrlsListener{
        void onClickDelete(User user);
    }

}
