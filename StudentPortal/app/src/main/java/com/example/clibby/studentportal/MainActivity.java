package com.example.clibby.studentportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //instance variables
    private List<Reminder> mReminders;
    private ReminderAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mNewReminderText;
    private GestureDetector mGestureDetector;

    Button button;

    public static final String NEW_PORTAL = "Portal";
    public static final int INTENT_CODE = 1234;
    public static final String URL_STRING = "URL";
    public static final String TITLE_STRING = "Title";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//Initialize the instance variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mReminders = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FloatingActionButton fab = findViewById(R.id.fab);
//set onClick for floating action button to NewPortal.class
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "FAButton", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, NewPortal.class);
                startActivityForResult(intent,INTENT_CODE);
            }
        });
//SimpleCallback for deleting object
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }
                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        mReminders.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }

                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

//update UI to current recyclerview
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ReminderAdapter(mReminders);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

//When done adding a new portal
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== INTENT_CODE){
            Intent intent = getIntent();
            Bundle extras = data.getExtras();
            String URLs = extras.getString(URL_STRING);
            String Titles = extras.getString(TITLE_STRING);
            Reminder newReminder = new Reminder(Titles,URLs);
            mReminders.add(newReminder);
            updateUI();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
/*
    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
    int mAdapterPosition = recyclerView.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
//            Toast.makeText(this, mReminders.get(mAdapterPosition).getmReminderText(), Toast.LENGTH_SHORT).show();
                String WebURL = mReminders.get(mAdapterPosition).getmSiteURL();
                Intent intent = new Intent(MainActivity.this, LinkedActivity.class);

        intent.putExtra(URL_STRING,  WebURL);
        startActivity(intent);
        }
        return false;
        */