package com.example.mvvm_tutorial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvm_tutorial.Entity.Note;
import com.example.mvvm_tutorial.ViewModel.NoteViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private BottomAppBar bottomAppBar;

    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseViews();
        setupRecyclerView();

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });
    }

    private void initialiseViews() {
        appBarLayout = findViewById(R.id.main_appbarlayout);
        toolbar = findViewById(R.id.main_toolbar);
        //swipeRefreshLayout = findViewById(R.id.main_swiperefreshlayout);
        recyclerView = findViewById(R.id.main_recyclerview);
        fab = findViewById(R.id.main_fab);
        bottomAppBar = findViewById(R.id.main_bottomappbar);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new MyRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
