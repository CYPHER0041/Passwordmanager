package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Data> dataList;
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private DataViewModel dataViewModel;
    public static final int NEW_DATA_ACTIVITY_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter=new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        dataViewModel=new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getAllData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                recyclerViewAdapter.setDatalist(data);
            }
        });
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewDataActivity.class);
                startActivityForResult(intent, NEW_DATA_ACTIVITY_REQUEST_CODE);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Data mydata = recyclerViewAdapter.getPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting: " + mydata.getSite_url(), Toast.LENGTH_SHORT).show();

                        DataViewModel.deleteData(mydata);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
            }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dropdown, menu);
        return true;
    }

    // The options menu has a single item "Clear all data now"
    // that deletes all the entries in the database
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, R.string.clear_data_toast_text, Toast.LENGTH_LONG).show();

            // Delete the existing data
            DataViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_DATA_ACTIVITY_REQUEST_CODE && resultCode == 1) {
           Data data_bundle = new Data(data.getStringExtra("username"),data.getStringExtra("password"),data.getStringExtra("site_url"));
            dataViewModel.insert(data_bundle);
        }
        else if(requestCode==NEW_DATA_ACTIVITY_REQUEST_CODE&& resultCode==2){
            Data data_update=new Data(data.getStringExtra("username"),data.getStringExtra("password"),data.getStringExtra("site_url"));
            dataViewModel.updateData(data_update);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}