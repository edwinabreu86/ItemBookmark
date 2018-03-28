package com.edwin.abreusoft.itembookmark;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setAdapter(createAdapter(VersesText.NT_GOSPELS));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        item.setChecked(true);
        int id = item.getItemId();

        if(id == R.id.pentateuco) {
            recycler.setAdapter(createAdapter(VersesText.OT_MOSES));
        } else if(id == R.id.historicos1) {
            recycler.setAdapter(createAdapter(VersesText.OT_HISTORICAL));

        } else if(id == R.id.poeticos) {
            recycler.setAdapter(createAdapter(VersesText.OT_POETRY));

        } else if(id == R.id.prof_mayores) {
            recycler.setAdapter(createAdapter(VersesText.OT_MAJ_PROPHETS));

        } else if(id == R.id.prof_menores) {
            recycler.setAdapter(createAdapter(VersesText.OT_MIN_PROPHETS));

        } else if(id == R.id.evangelios) {
            recycler.setAdapter(createAdapter(VersesText.NT_GOSPELS));

        } else if(id == R.id.historicos2) {
            recycler.setAdapter(createAdapter(VersesText.NT_HISTORICAL));

        } else if(id == R.id.paulinas) {
            recycler.setAdapter(createAdapter(VersesText.NT_PAULINE));

        } else if(id == R.id.generales) {
            recycler.setAdapter(createAdapter(VersesText.NT_GENERAL));
        } else if(id == R.id.profecia) {
            recycler.setAdapter(createAdapter(VersesText.NT_PROPHECY));
        } else if(id == R.id.favoritos) {
            startActivity(new Intent(this, FavActivity.class));
        }

        toolbar.setTitle(item.getTitle());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        thread.run();

        return true;
    }

    private VersesAdapter createAdapter(String[][] verses) {
        List<Verse> versesList = new ArrayList<>();

        for (String[] verse : verses) {
            versesList.add(new Verse(verse[0], verse[1], verse[2]));
        }
        return new VersesAdapter(this, versesList);
    }
}
