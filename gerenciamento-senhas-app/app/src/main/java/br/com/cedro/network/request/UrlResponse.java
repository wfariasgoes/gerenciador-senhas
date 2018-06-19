package br.com.cedro.network.request;

import android.media.Image;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class UrlResponse {
    @SerializedName("image/png")
    private Uri image;

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
