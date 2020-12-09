package com.example.examenfinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.DetailPokemonActivity;
import com.example.examenfinal.Entity.PokemonClass;
import com.example.examenfinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.AdapterViewHolder> {

    List<PokemonClass> list;
    Context context;

    public AdapterList(List<PokemonClass> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        holder.SetInfo(list.get(position));

        PokemonClass aClass = list.get(position);

        holder.detail.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetailPokemonActivity.class);
            intent.putExtra("Pokemon",aClass);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageURL;
        TextView name, type;
        Button detail;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imageURL = itemView.findViewById(R.id.imageURL);
            type = itemView.findViewById(R.id.type);
            name = itemView.findViewById(R.id.name);
            detail = itemView.findViewById(R.id.detail);
        }

        void SetInfo(PokemonClass aClass) {

            type.setText(aClass.getTipo());
            name.setText(aClass.getNombre());

            Picasso.get()
                    .load("https://upn.lumenes.tk"+aClass.getUrl_imagen())
                    .into(imageURL);
        }
    }
}
