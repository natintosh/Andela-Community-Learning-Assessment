package com.natinc.oluwatobiloba.alc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.natinc.oluwatobiloba.alc.utilities.CryptoJsonUtils;
import com.natinc.oluwatobiloba.alc.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView mCryptoRecyclerView;
    private CrytoCurrencyAdapter mAdapter;

    private TextView mTvErrorMessage;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvErrorMessage = (TextView) findViewById(R.id.tv_error_message);

        mProgressbar = (ProgressBar) findViewById(R.id.pd_progress_bar);

        mCryptoRecyclerView = (RecyclerView) findViewById(R.id.rv_crypto_list);
        mCryptoRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCryptoRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new CrytoCurrencyAdapter();
        mCryptoRecyclerView.setAdapter(mAdapter);

        loadData();

    }

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String fromSymbols = sharedPreferences.getString(getString(R.string.cryto_key), getString(R.string.cryto_value));
        String toSymbols = sharedPreferences.getString(getString(R.string.currencies_key), getString(R.string.currencies_value));
        new FetchUrlTask().execute(fromSymbols, toSymbols);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void showResult() {
        mTvErrorMessage.setVisibility(View.INVISIBLE);
        mCryptoRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mCryptoRecyclerView.setVisibility(View.INVISIBLE);
        mTvErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.cryto_key))) {
            String fromSymbols = sharedPreferences.getString(getString(R.string.cryto_key), getString(R.string.cryto_value));
            String toSymbols = sharedPreferences.getString(getString(R.string.currencies_key), getString(R.string.currencies_value));
            new FetchUrlTask().execute(fromSymbols, toSymbols);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else if (s.equals(getString(R.string.currencies_key))) {
            String toSymbols = sharedPreferences.getString(getString(R.string.currencies_key), getString(R.string.cryto_value));
            String fromSymbols = sharedPreferences.getString(getString(R.string.cryto_key), getString(R.string.currencies_value));
            new FetchUrlTask().execute(fromSymbols, toSymbols);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItem = item.getItemId();
        Intent intent;
        switch (selectedItem) {
            case R.id.action_refresh:
                intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    private class FetchUrlTask extends AsyncTask<String, Void, LinkedHashMap<String, LinkedHashMap<String, Double>>> {

        @Override
        protected void onPreExecute() {
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected LinkedHashMap<String, LinkedHashMap<String, Double>> doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }
            String fromCurrency = strings[0];
            String toCurrency = strings[1];

            URL requestUrl;
            requestUrl = NetworkUtils.buildUrl(fromCurrency, toCurrency);

            String responceFromUrl;
            LinkedHashMap<String, LinkedHashMap<String, Double>> resultFromJson;

            try {
                responceFromUrl = NetworkUtils
                        .getResponseFromHttpUrl(requestUrl);


                resultFromJson
                        = CryptoJsonUtils.getSimpleDataFromJson(responceFromUrl);
                return resultFromJson;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(LinkedHashMap<String, LinkedHashMap<String, Double>> hashMapData) {
            mProgressbar.setVisibility(View.INVISIBLE);
            if (hashMapData != null) {
                Log.d(LOG_TAG, hashMapData.toString());
                showResult();
                mAdapter.setMappedData(hashMapData);
                new CrytoCurrencyAdapter();
            } else {
                showErrorMessage();
            }
        }
    }
}

