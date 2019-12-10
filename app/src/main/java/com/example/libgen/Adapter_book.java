package com.example.libgen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libgen.Adapters.Custom_book;
import com.example.libgen.Lists.ListFull;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_book);
        final ListView listView = findViewById(R.id.list_book);
        final List<ListFull> listFull = new ArrayList<>();


        Network.getInstance().getApi().databook(StaticDate.listID.toString()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                for(int i = 0; i < response.body().getAsJsonArray().size(); i++){
                    listFull.add(new ListFull(
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("author").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("title").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("pages").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("year").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("publisher").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("filesize").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("extension").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("locator").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("coverurl").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("pagesinfile").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("descr").toString(),
                            response.body().getAsJsonArray().get(i).getAsJsonObject().get("md5").toString()
                    ));
                }

                Custom_book custom_book = new Custom_book(getApplicationContext(), R.layout.item_book, listFull);
                listView.setAdapter(custom_book);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
