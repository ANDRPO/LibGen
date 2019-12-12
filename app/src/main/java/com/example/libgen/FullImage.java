package com.example.libgen;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class FullImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Button imagefull_back = findViewById(R.id.fullimage_b_back);
        Bundle arguments = getIntent().getExtras();
        String urlimage = arguments.getString("urlImage");
        Picasso.get().load(urlimage).error(R.drawable.ic_error).placeholder(R.drawable.ic_sync).into((ImageView) findViewById(R.id.fullImageBook_photoview));
        imagefull_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
