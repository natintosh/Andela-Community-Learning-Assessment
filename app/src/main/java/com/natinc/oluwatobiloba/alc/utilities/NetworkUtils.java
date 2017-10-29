package com.natinc.oluwatobiloba.alc.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by oluwatobiloba on 10/22/17.
 */

//https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=NGN,USD,GBP,EUR,CHF,JPY,CNY,CAD,AUD,HKD,INR,ZAR,SEK,RUB,BRL,CLP,CZK,ILS,MXN,KRW

public class NetworkUtils {

    private final static String BASE_URL = "https://min-api.cryptocompare.com/data/pricemulti";
    private final static String FROM_SYMBOLS = "fsyms";
    private final static String TO_SYMBOLS = "tsyms";

    public static URL buildUrl(String currencyFrom, String currencyTo) {
        Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FROM_SYMBOLS, currencyFrom)
                .appendQueryParameter(TO_SYMBOLS, currencyTo)
                .build();

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = httpURLConnection.getInputStream();

            Scanner scanner = new Scanner(in);

            scanner.useDelimiter("\\A");

            Boolean hasNext = scanner.hasNext();

            if (hasNext) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            httpURLConnection.disconnect();
        }
    }

}
