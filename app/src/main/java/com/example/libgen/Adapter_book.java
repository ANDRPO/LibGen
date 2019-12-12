package com.example.libgen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Adapter_book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_book);
        StaticDate.listView = findViewById(R.id.list_book);
        StaticDate.context = getApplicationContext();
        Async async = new Async();
        async.execute();
    }
}
