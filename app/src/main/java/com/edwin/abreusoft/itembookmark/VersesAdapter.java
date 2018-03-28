package com.edwin.abreusoft.itembookmark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VersesAdapter extends RecyclerView.Adapter<VersesAdapter.ViewHolder> {

    private int imgRes;
    private Context ctx;
    private final List<Verse> versesList;

    private VersesDbHelper dbHelper;

    VersesAdapter(Context ctx, List<Verse> versesList) {
        this.ctx = ctx;
        this.versesList = versesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View verseView = inflater.inflate(R.layout.verse, parent, false);
        return new ViewHolder(verseView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Verse verse = versesList.get(position);

        holder.text1.setText(String.format("%s %s", verse.getBook(), verse.getVerse()));
        holder.text2.setText(verse.getContent());

        dbHelper = new VersesDbHelper(ctx);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!verse.isFavorite()) {
                    dbHelper.addVerse(verse);
                    imgRes = R.drawable.bookmark_true;
                    verse.setFavorite(true);
                    Toast.makeText(ctx, "Verso añadido", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.removeVerse(verse);
                    imgRes = R.drawable.bookmark_false;
                    verse.setFavorite(false);
                    Toast.makeText(ctx, "Verso removido", Toast.LENGTH_SHORT).show();
                }

                holder.imgView.setImageResource(imgRes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return versesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text1;
        private TextView text2;
        private ImageView imgView;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            imgView = itemView.findViewById(R.id.img_view);
        }
    }
}
