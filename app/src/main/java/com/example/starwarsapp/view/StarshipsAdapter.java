package com.example.starwarsapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwarsapp.R;
import com.example.starwarsapp.model.Starships;

import java.util.List;

public class StarshipsAdapter extends RecyclerView.Adapter<StarshipsAdapter.CelluleJava> {

    private List<Starships> starshipsList;
    private final OnItemClickListener listener;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(Starships starships);
    }


    public class CelluleJava extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imgIcon;

        public CelluleJava(View view) {
            super(view);
            txtHeader = (TextView) view.findViewById(R.id.tile_title);
            //txtFooter = (TextView) view.findViewById(R.id.gender);
            imgIcon = (ImageView) view.findViewById(R.id.tile_picture);
        }
    }

    public StarshipsAdapter(List<Starships> listValues, OnItemClickListener listener, Context context) {
        this.starshipsList = listValues;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public CelluleJava onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fragment_main_item, parent, false);
        CelluleJava vh = new CelluleJava(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CelluleJava holder, final int position) {
        final Starships starships = starshipsList.get(position);
        final String name = starshipsList.get(position).getName();
        final String crew = starshipsList.get(position).getCrew();
        final String picture = starshipsList.get(position).getPicture();

        holder.txtHeader.setText(name);
        //holder.txtFooter.setText("Crew : " + crew);

        /*
        Picasso.with(context)
                .load(picture)
                .error(R.drawable.error_icon)
                .into(holder.imgIcon);
        */

        Glide.with(context)
                .load(picture)
                .error(R.drawable.error_icon)
                .fitCenter()
                .into(holder.imgIcon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(starships);
            }
        });

    }

    @Override
    public int getItemCount() {
        return starshipsList.size();
    }
}