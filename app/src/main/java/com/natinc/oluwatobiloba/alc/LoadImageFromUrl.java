package com.natinc.oluwatobiloba.alc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.natinc.oluwatobiloba.alc.utilities.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by oluwatobiloba on 10/26/17.
 */

class LoadImageFromUrl extends AsyncTask<String, Void, Bitmap> {

    ImageView mImage;
    ProgressBar mImageProgressBar;

    URL url = null;

    LoadImageFromUrl(ImageView imageView, ProgressBar progressBar) throws MalformedURLException {
        url = new URL("https://www.cryptocompare.com/api/data/coinlist");
        mImageProgressBar = progressBar;
        mImage = imageView;
    }

    @Override
    protected void onPreExecute() {
        mImageProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String cryptoCurrency = strings[0];

        String imageUrlWithBaseUrl = "";

        try {
            String responseFromDataUrl = NetworkUtils.getResponseFromHttpUrl(url);

            JSONObject jsonRoot = new JSONObject(responseFromDataUrl);
            String baseImageUrl = jsonRoot.getString("BaseImageUrl");
            JSONObject data = jsonRoot.getJSONObject("Data");
            JSONObject dataRoot = new JSONObject(data.toString());
            JSONObject crypto = dataRoot.getJSONObject(cryptoCurrency);
            JSONObject cryptoRoot = new JSONObject(crypto.toString());
            String imageUrl = cryptoRoot.getString("ImageUrl");

            imageUrlWithBaseUrl = baseImageUrl + imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(imageUrlWithBaseUrl).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        mImageProgressBar.setVisibility(View.INVISIBLE);
        mImage.setImageBitmap(result);
    }
}
