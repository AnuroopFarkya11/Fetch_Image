package com.example.fetchimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    List<Image_Model> item_name = new ArrayList<>();

    LayoutInflater inflater;

    public GridAdapter(Context context, List<Image_Model> item_name) {
        this.context = context;
        this.item_name = item_name;
    }


    @Override
    public int getCount() {
        return item_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(inflater==null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        if(view == null)
        {
            view = inflater.inflate(R.layout.photo_card,null);
        }

        TextView item_name_textview = view.findViewById(R.id.card_textview);
        ImageView item_image = view.findViewById(R.id.card_imageview);

        item_name_textview.setText(item_name.get(i).getIMAGE_NAME());

        Picasso.get().load(item_name.get(i).getIMAGE_URL()).into(item_image);



        return view;
    }
}
