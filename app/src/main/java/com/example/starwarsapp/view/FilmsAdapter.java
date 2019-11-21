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
import com.example.starwarsapp.model.Films;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.CelluleJava> {

    private List<Films> filmsList;
    private final OnItemClickListener listener;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(Films films);
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

    public FilmsAdapter(List<Films> listValues, OnItemClickListener listener, Context context) {
        this.filmsList = listValues;
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
        final Films films = filmsList.get(position);
        final String title = filmsList.get(position).getTitle();
        final String director = filmsList.get(position).getDirector();
        final String filmPoster = filmsList.get(position).getFilm_poster();

        holder.txtHeader.setText(title);
        //holder.txtFooter.setText("Director : " + director);

        /*
        Picasso.with(context)
                .load(image_icon)
                .error(R.drawable.error_icon)
                .into(holder.imgIcon);
        */

        Glide.with(context)
                .load(filmPoster)
                .error(R.drawable.error_icon)
                .fitCenter()
                .into(holder.imgIcon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(films);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filmsList.size();
    }
}