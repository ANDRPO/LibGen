package com.example.libgen;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libgen.Lists.ListFull;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaticDate {
    public static List<Integer> listID = new ArrayList<>();
    public static List<ListFull> listFull = new ArrayList<>();
    public static Boolean FIASKO = false;
    public static ListView listView;
    public static Context context;

    public void GlobalPOST(String dateFirst, String dateLast, final Context context)
    {
        try {
            Network.getInstance().getApi().sortdate(dateFirst, dateLast).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (response.isSuccessful()) {
                        Log.e("RESPONCE:", response.body().toString());
                        Log.e("SIZE:", String.valueOf(response.body().getAsJsonArray().size()));
                        //    Log.e("1PARSE", response.body().getAsJsonArray().get(0).getAsJsonObject().get("id").toString());
                        for (int i = 0; i < response.body().getAsJsonArray().size(); i++) {
                            StaticDate.listID.add(response.body().getAsJsonArray().get(i).getAsJsonObject().get("id").getAsInt());
                            Log.e("ID:", response.body().getAsJsonArray().get(i).getAsJsonObject().get("id").toString());
                        }
                        Log.e("SUCCESS!", "SUCCESS!");
                    }
                    else {
                        StaticDate.FIASKO = true;
                        Toast.makeText(context, "Connection error!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.e("CALLERROR:", call.toString());
                    Log.e("ERROR:", t.toString());
                    StaticDate.FIASKO = true;
                    Toast.makeText(context, "Connection error!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            StaticDate.FIASKO = true;
        }
    }
}
