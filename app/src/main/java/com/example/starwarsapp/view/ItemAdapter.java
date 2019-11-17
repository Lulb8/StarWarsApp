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
import com.example.starwarsapp.model.People;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CelluleJava> {

    private List<People> peopleList;
    private final OnItemClickListener listener;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(People people);
    }


    public class CelluleJava extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imgIcon;

        public CelluleJava(View view) {
            super(view);
            txtHeader = (TextView) view.findViewById(R.id.title);
            txtFooter = (TextView) view.findViewById(R.id.gender);
            imgIcon = (ImageView) view.findViewById(R.id.imageIcon);
        }
    }

    public ItemAdapter(List<People> listValues, OnItemClickListener listener, Context context) {
        this.peopleList = listValues;
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
        final People people = peopleList.get(position);
        final String name = peopleList.get(position).getName();
        final String gender = peopleList.get(position).getGender();
        final String image_icon = peopleList.get(position).getImageIcon();
        System.out.println("url = " + image_icon);

        holder.txtHeader.setText(name);
        holder.txtFooter.setText("Gender : " + gender);

        /*
        Picasso.with(context)
                .load(image_icon)
                .error(R.drawable.error_icon)
                .into(holder.imgIcon);
        */

        Glide.with(context)
                .load(image_icon)
                .error(R.drawable.error_icon)
                .fitCenter()
                .into(holder.imgIcon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(people);
            }
        });

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}