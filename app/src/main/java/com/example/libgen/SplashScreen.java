package com.example.libgen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libgen.Adapters.Custom_book;
import com.example.libgen.Lists.ListFull;
import com.google.gson.JsonElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        try {
            StaticDate staticDate = new StaticDate();
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateTextLast = dateFormat.format(currentDate);
            String dateText = date(dateTextLast, dateFormat);

            Log.e("Актуальная дата:", dateTextLast);
            Log.e("Дата-1", dateText);

            staticDate.GlobalPOST(dateText, dateTextLast, getApplicationContext());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_LONG).show();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, Adapter_book.class));
            }
        }, 5000);
    }

    public String date(String dateTextLast, DateFormat dateFormat) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(Objects.requireNonNull(dateFormat.parse(dateTextLast)));
        c.add(Calendar.DATE, -1);  // number of days to add
        return dateFormat.format(c.getTime());
    }
}
