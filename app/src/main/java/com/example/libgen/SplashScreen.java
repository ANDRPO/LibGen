package com.example.libgen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        try {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateTextLast = dateFormat.format(currentDate);

            Calendar c = Calendar.getInstance();
            c.setTime(dateFormat.parse(dateTextLast));
            c.add(Calendar.DATE, -1);  // number of days to add
            String dateText = dateFormat.format(c.getTime());  // dt is now the new date

            Log.e("Актуальная дата:", dateTextLast);
            Log.e("Дата-1", dateText);


            Network.getInstance().getApi().sortdate(dateText, dateTextLast).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    Log.e("RESPONCE:", response.body().toString());
                    Log.e("SIZE:", String.valueOf(response.body().getAsJsonArray().size()));
                //    Log.e("1PARSE", response.body().getAsJsonArray().get(0).getAsJsonObject().get("id").toString());
                    for (int i = 0; i < response.body().getAsJsonArray().size(); i++) {
                        StaticDate.listID.add(response.body().getAsJsonArray().get(i).getAsJsonObject().get("id").getAsInt());
                        Log.e("ID:", response.body().getAsJsonArray().get(i).getAsJsonObject().get("id").toString());
                    }
                    Log.e("SUCCESS!", "SUCCESS!");

                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.e("CALLERROR:", call.toString());
                    Log.e("ERROR:", t.toString());
                    Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_LONG).show();
                }
            });
            Log.e("STACK ID", StaticDate.listID.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_LONG).show();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(SplashScreen.this, Adapter_book.class));
            }
        },3000);
    }
}
