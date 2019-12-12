package com.example.libgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.text.DecimalFormat;

public class FullBookDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_book_description);
        ImageView imagebook = findViewById(R.id.fullbook_image);
        TextView fullbook_name = findViewById(R.id.fullbook_tv_namebook);
        TextView fullbook_author = findViewById(R.id.fullbook_tv_author);
        TextView fullbook_year = findViewById(R.id.fullbook_tv_year);
        TextView fullbook_pages = findViewById(R.id.fullbook_tv_pages);
        TextView fullbook_description = findViewById(R.id.fullbook_tv_description);
        TextView fullbook_format = findViewById(R.id.fullbook_tv_format);
        TextView fullbook_filesize = findViewById(R.id.fullbook_tv_filesize);
        TextView fullbook_publisher = findViewById(R.id.fullbook_tv_publisher);
        Button download = findViewById(R.id.fullbook_b_download);
        Button fullBook_b_back = findViewById(R.id.fullbook_b_back);

        Bundle arguments = getIntent().getExtras();
        final int position = arguments.getInt("position");

        Log.e("LIST", StaticDate.listFull.get(position).title);

        final String urlImage = "http://93.174.95.29/covers/" + StaticDate.listFull.get(position).coverurl.replace("\"", "");
        Picasso.get().load(urlImage).error(R.drawable.ic_error).placeholder(R.drawable.ic_sync).into(imagebook);
        fullbook_name.append(StaticDate.listFull.get(position).title);
        fullbook_author.append(StaticDate.listFull.get(position).author);
        fullbook_year.append(StaticDate.listFull.get(position).year);
        fullbook_pages.append(StaticDate.listFull.get(position).pages);
        fullbook_filesize.append(MBfile(StaticDate.listFull.get(position).filesize) + "MB");
        fullbook_format.append(StaticDate.listFull.get(position).extension);


        if (!StaticDate.listFull.get(position).publisher.equals("\"\""))
            fullbook_publisher.append(Jsoup.parse(StaticDate.listFull.get(position).publisher).text());
        else
            fullbook_publisher.append("\"No\"");


        if (!StaticDate.listFull.get(position).descr.equals("\"\""))
            fullbook_description.append(CorrectConvertHtmlToText(StaticDate.listFull.get(position).descr));
        else
            fullbook_description.append("\"No\"");


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://93.174.95.29/main/2448000/" + StaticDate.listFull.get(position).md5.replace("\"", "") + "/" + StaticDate.listFull.get(position).locator.replace("\"", "")));
                startActivity(browserIntent);
            }
        });

        imagebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FullBookDescription.this, FullImage.class);
                intent.putExtra("urlImage", urlImage);
                startActivity(intent);
            }
        });

        fullBook_b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    public String MBfile(String filesize) {
        filesize = filesize.replace("\"", "");
        double result = Double.parseDouble(filesize) / 1048576;
        String formattedDouble = new DecimalFormat("#0.00").format(result);
        return formattedDouble;
    }

    public String CorrectConvertHtmlToText(String description){
        return Html.fromHtml(description).toString().replace("\\r", "").replace("\\n", "").replace("\\", "");
    }
}
