package com.example.newsqllite.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsqllite.Helpers.CoffeItem;
import com.example.newsqllite.Helpers.FavDB;
import com.example.newsqllite.R;

import java.util.ArrayList;

public class CoffeADapter extends RecyclerView.Adapter<CoffeADapter.ViewHolder> {
    private ArrayList<CoffeItem> coffeItems;
    private Context context;
    private FavDB favDB;

    public CoffeADapter(ArrayList<CoffeItem> coffeItems, Context context) {
        this.coffeItems = coffeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CoffeADapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        /// creating the table
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOfFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CoffeADapter.ViewHolder holder, int position) {

        final CoffeItem coffeItem = coffeItems.get(position);
        readCursorData(coffeItem, holder);
        holder.imageView.setImageResource(coffeItem.getImageResource());
        holder.titleTextView.setText(coffeItem.getTilte());
    }



    @Override
    public int getItemCount() {
        return coffeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favoriteBtn);

            ///add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CoffeItem coffeItem = coffeItems.get(position);
                    if (coffeItem.getFavStatus().equals("0")) {
                        coffeItem.setFavStatus("1");
                        favDB.insertIntoTheDatabase(coffeItem.getTilte(), coffeItem.getImageResource(),
                                coffeItem.getKey_id(), coffeItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_real);
                    }else {
                        coffeItem.setFavStatus("0");
                        favDB.remove_fav(coffeItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow);
                    }

                }


            });

        }
    }

    //// function of first satrt
    private void createTableOfFirstStart() {
        favDB.insertEmpty();
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(CoffeItem coffeItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(coffeItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();

        try {
            while (cursor.moveToNext()) {

                @SuppressLint("Range") String item_nav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                coffeItem.setFavStatus(item_nav_status);

                //check for status
                if (item_nav_status != null && item_nav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_real);
                } else if (item_nav_status != null && item_nav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow);
                }
            }
        } finally {if (cursor !=null && cursor.isClosed())
            cursor.close();
            db.close();
        }

    }

}
