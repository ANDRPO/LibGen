package com.example.libgen.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libgen.Lists.ListFull;
import com.example.libgen.R;
import com.squareup.picasso.Picasso;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = inflater.inflate(listitemId, parent, false);

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
        yearbook.append(listFull.getYear());
        descriptionbook.append(listFull.getDescr());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getContext().startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });

        return v;
    }
}
