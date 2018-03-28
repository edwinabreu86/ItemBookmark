package com.edwin.abreusoft.itembookmark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class FavActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());

        VersesDbHelper dbHelper = new VersesDbHelper(this);

        List<Verse> verseList = dbHelper.getVersesList();

        recycler.setAdapter(new VersesAdapter(this, verseList));
    }
}
