package com.natinc.oluwatobiloba.alc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity {

    private static final String LOG_TAG = ConvertActivity.class.getSimpleName();
    TextView mTvFrom;
    TextView mTvTo;
    TextView mEditFrom;
    TextView mEditTo;

    String mCryptoName;
    String mCurrencyName;
    Double mCurrencyValue;
    Double mConversionRate;

    Button one, two, three, four, five, six, seven, eight, nine, zero, clear, enter;

    Button swap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        mTvFrom = (TextView) findViewById(R.id.tv_currency_from);
        mTvTo = (TextView) findViewById(R.id.tv_currency_to);
        mEditFrom = (TextView) findViewById(R.id.et_currency_from);
        mEditTo = (TextView) findViewById(R.id.et_currency_to);

        Intent intent = getIntent();

        mCryptoName = intent.getStringExtra("CryptoKey");
        mCurrencyName = intent.getStringExtra("CurrencyName");
        mCurrencyValue = intent.getDoubleExtra("CurrencyValue", 0);

        mConversionRate = mCurrencyValue;

        mTvFrom.setText(mCryptoName);
        mTvTo.setText(mCurrencyName);


        mEditTo.setText(String.valueOf(mCurrencyValue));
        mEditFrom.setText("1");

        setButtonClickListener();

    }

    void setButtonClickListener() {
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        clear = (Button) findViewById(R.id.clear);
        enter = (Button) findViewById(R.id.enter);

        swap = (Button) findViewById(R.id.button_swap);

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempString = mTvFrom.getText().toString();
                mTvFrom.setText(mTvTo.getText().toString());
                mTvTo.setText(tempString);
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("1");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("2");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("3");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("4");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("5");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("5");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("6");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("5");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("6");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("5");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("6");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("7");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("8");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("9");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditFrom.append("0");
                String text = mEditFrom.getText().toString();
                Double value = Double.parseDouble(text);
                convertCurrency(value, mConversionRate);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int charindex = mEditFrom.getText().length();
                if (charindex != 0) {
                    String input = mEditFrom.getText().toString();
                    input = input.substring(0, charindex - 1);
                    mEditFrom.setText(input);
                } else {
                    mEditFrom.setText("1");
                }

                String text = mEditFrom.getText().toString();
                Double value;
                if (!text.isEmpty()) {
                    value = Double.parseDouble(text);
                } else {
                    value = 1.00;
                }
                convertCurrency(value, mConversionRate);
            }
        });
    }

    public void proceed(View view) {

    }

    void convertCurrency(Double currencyFrom, Double conversionRate) {

        Double amount;

        if (mCryptoName.equals(mTvFrom.getText().toString())) {
            amount = currencyFrom * conversionRate;
            mEditTo.setText(String.format("%.2f", amount));
        } else {
            amount = currencyFrom / conversionRate;
            mEditTo.setText(String.format("%.2f", amount));
        }
    }
}
