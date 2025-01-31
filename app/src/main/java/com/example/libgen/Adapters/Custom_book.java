package com.example.libgen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libgen.FullBookDescription;
import com.example.libgen.Lists.ListFull;
import com.example.libgen.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.util.List;

public class Custom_book extends ArrayAdapter<ListFull> {

    private LayoutInflater inflater;
    private int listitemId;
    private List<ListFull> list;

    public Custom_book(@NonNull Context context, int resource, @NonNull List<ListFull> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.listitemId = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = inflater.inflate(listitemId, parent, false);
        FullBookDescription fullBookDescription = new FullBookDescription();

        ImageView imagebook = v.findViewById(R.id.item_i_book);

        TextView namebook = v.findViewById(R.id.item_tv_namebook);
        TextView authorbook = v.findViewById(R.id.item_tv_authorbook);
        TextView yearbook = v.findViewById(R.id.item_tv_year);
        TextView descriptionbook = v.findViewById(R.id.item_tv_description);

        ListFull listFull = this.list.get(position);

        String urlImage = "http://93.174.95.29/covers/" + listFull.getCoverurl().replace("\"", "");

        Log.e("URLIMAGE", urlImage);

        Picasso.get().load(urlImage).error(R.drawable.ic_error).placeholder(R.drawable.ic_sync).into(imagebook);

        namebook.append(listFull.getTitle());
        authorbook.append(listFull.getAuthor());
        yearbook.append(fullBookDescription.MakeCorrectYearOrPages(listFull.getYear()));
        if(!listFull.getDescr().equals("\"\"")){
            descriptionbook.append(fullBookDescription.CorrectConvertHtmlToText(listFull.getDescr()));
        }
        else
            descriptionbook.append("\"No\"");

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullBookDescription.class);
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });

        return v;
    }
}
