package com.example.newsqllite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsqllite.Helpers.FavDB;
import com.example.newsqllite.Helpers.FavItem;
import com.example.newsqllite.R;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.Viewholder> {
    private Context context;
    private List<FavItem> favItemsList;
    private FavDB favDB;

    public FavAdapter(Context context, List<FavItem> favItemsList) {
        this.context = context;
        this.favItemsList = favItemsList;
    }

    @NonNull
    @Override
    public FavAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        favDB  = new FavDB(context);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.Viewholder holder, int position) {
        holder.favTextView.setText(favItemsList.get(position).getItem_title());
        holder.favImageView.setImageResource(favItemsList.get(position).getItem_image());

    }

    @Override
    public int getItemCount() {
        return favItemsList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView favTextView;
        Button favBtn;
        ImageView favImageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            favTextView = itemView.findViewById(R.id.favTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            favImageView = itemView.findViewById(R.id.favImage);

            /// remove Fav after Click

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemsList.get(position);
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);
                }
            });
        }
    }

    private void removeItem(int position) {
        favItemsList.remove(position);
       notifyItemRemoved(position);
       notifyItemRangeChanged(position, favItemsList.size());
    }
}
