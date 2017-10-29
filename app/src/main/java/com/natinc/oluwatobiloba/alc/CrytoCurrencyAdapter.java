package com.natinc.oluwatobiloba.alc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by oluwatobiloba on 10/23/17.
 */

public class CrytoCurrencyAdapter extends RecyclerView.Adapter<CrytoCurrencyAdapter.CyptoCurrencyViewHolder> {

    private static final String LOG_TAG = CrytoCurrencyAdapter.class.getSimpleName();
    Context context;
    private LinkedHashMap<String, LinkedHashMap<String, Double>> mappedData = null;
    private List<String> mKeyList = new ArrayList<>();
    private ArrayList<ArrayList<String>> mInnerList = new ArrayList<>();
    private ArrayList<ArrayList<Double>> mInnervalue = new ArrayList<>();
    private String mKey;

    @Override
    public CrytoCurrencyAdapter.CyptoCurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int idForListItemLayout = R.layout.list_item;
        Boolean shouldAttachInParentRoot = false;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idForListItemLayout, parent, shouldAttachInParentRoot);
        CyptoCurrencyViewHolder viewHolder = new CyptoCurrencyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CrytoCurrencyAdapter.CyptoCurrencyViewHolder holder, int position) {
        mKey = mKeyList.get(position);
        holder.bind(context, position, mKeyList.get(position), mInnerList, mInnervalue, mappedData);
    }

    @Override
    public int getItemCount() {
        if (mKeyList == null) {
            return 0;
        } else {
            return mKeyList.size();
        }
    }

    public void setMappedData(LinkedHashMap<String, LinkedHashMap<String, Double>> mappedData) {
        this.mappedData = mappedData;

        Set<String> set = this.mappedData.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            mKeyList.add(iterator.next().toString());
        }
        Log.d(LOG_TAG, String.valueOf(mKeyList.size()));
        Log.d(LOG_TAG, String.valueOf(this.mappedData.size()));

        for (LinkedHashMap.Entry<String, LinkedHashMap<String, Double>> map : this.mappedData.entrySet()) {
            ArrayList<String> innerKeyList = new ArrayList<>();
            ArrayList<Double> innerValueList = new ArrayList<>();
            for (Map.Entry<String, Double> innerMap : map.getValue().entrySet()) {
                innerKeyList.add(innerMap.getKey());
                innerValueList.add(innerMap.getValue());
            }
            mInnerList.add(innerKeyList);
            mInnervalue.add(innerValueList);
        }
        Log.d(LOG_TAG, mInnerList.toString());
        notifyDataSetChanged();
    }

    class CyptoCurrencyViewHolder extends RecyclerView.ViewHolder {
        final TextView mCurrencyValue;
        TextView mItem;
        Spinner mSpinnerCurrency;
        Button mButton;
        ImageView mImage;
        ProgressBar mImageProgressBar;
        int listPosition;
        ArrayList<ArrayList<Double>> innerValue;


        public CyptoCurrencyViewHolder(View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.tv_item);
            mSpinnerCurrency = itemView.findViewById(R.id.sp_currency_spinner);
            mCurrencyValue = itemView.findViewById(R.id.tv_currency_value);
            mButton = itemView.findViewById(R.id.bt_convert);
            mImage = itemView.findViewById(R.id.crypto_image);
            mImageProgressBar = itemView.findViewById(R.id.image_progressbar);
        }

        void bind(final Context context, int position, final String key, ArrayList<ArrayList<String>> innerKeyList,
                  ArrayList<ArrayList<Double>> mInnervalue, LinkedHashMap<String, LinkedHashMap<String, Double>> hashMap) {

            LoadImageFromUrl imageFromUrl = null;
            try {
                imageFromUrl = new LoadImageFromUrl(mImage, mImageProgressBar);
                imageFromUrl.execute(key);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            listPosition = position;
            innerValue = mInnervalue;

            mItem.setText(key);
            mCurrencyValue.setText(String.valueOf(hashMap.get(mKey).get(position)));

            List<String> list = innerKeyList.get(position);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, list);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinnerCurrency.setAdapter(dataAdapter);


            final Double[] value = new Double[1];
            final String[] name = new String[1];


            mSpinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    ArrayList<Double> values = innerValue.get(listPosition);
                    mCurrencyValue.setText(values.get(position).toString());
                    value[0] = values.get(position);  //--
                    name[0] = parent.getSelectedItem().toString();
                    Log.d(LOG_TAG, String.valueOf(value[0]));  //--
                    Log.d(LOG_TAG, parent.getSelectedItem().toString());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ConvertActivity.class);
                    intent.putExtra("CryptoKey", key);
                    intent.putExtra("CurrencyName", name[0]);
                    intent.putExtra("CurrencyValue", value[0]);

                    context.startActivity(intent);
                }
            });

        }
    }
}
