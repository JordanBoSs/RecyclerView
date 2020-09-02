package com.jordan.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        names = this.getNames();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Al tocar una vista dentro del recycler view
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
                deleteName(position);
            }
        });

        //mejora la experencia si el tamaño del rechcler view es fijo
        mRecyclerView.setHasFixedSize(true);
        //agrega animacion
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addName(int posicion) {
        names.add(posicion, "Nuevo Nombre "+(++counter));
        mAdapter.notifyItemInserted(posicion);
        //para que nos muestre el nuevo elemento con scroll
        mLayoutManager.scrollToPosition(posicion);
    }

    private void deleteName(int posicion){
        names.remove(posicion);
        mAdapter.notifyItemRemoved(posicion);
    }

    private List<String> getNames(){
        return new ArrayList<String>(){{
            add("Jordan");
            add("Lalo");
            add("Neto");
            add("José Luis");
            add("Pay");
            add("Pequeño");
            add("Abuelo");
        }};
    }
}