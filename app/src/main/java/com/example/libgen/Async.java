package com.example.libgen;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.libgen.Adapters.Custom_book;
import com.example.libgen.Lists.ListFull;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Async extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            int a = 0;
            while (true) {

                if (!StaticDate.listID.isEmpty()) {
                    Network.getInstance().getApi().databook(StaticDate.listID.toString()).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            Log.e("YES2", "YES2");
                            if (response.body() != null) {
                                for(int i = 0; i < response.body().getAsJsonArray().size(); i++){
                                    Log.e("RESPONCE2", String.valueOf(response.isSuccessful()));
                                    StaticDate.listFull.add(new ListFull(
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("author").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("title").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("pages").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("year").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("publisher").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("filesize").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("extension").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("locator").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("coverurl").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("descr").toString(),
                                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("md5").toString()
                                    ));
                                }
                            }
                            else {
                                Toast.makeText(StaticDate.context, "INCORRECT RESPONSE", Toast.LENGTH_LONG).show();
                            }
                            Custom_book custom_book = new Custom_book(StaticDate.context, R.layout.item_book, StaticDate.listFull);
                            StaticDate.listView.setAdapter(custom_book);

                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    });
                    break;
                }
                else{
                    a++;
                    Thread.sleep(1000);
                }
                if(a > 15){
                    break;
                }
            }
        } catch (Exception e) {
            Toast.makeText(StaticDate.context, "Connection error!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return null;
    }
}
