package com.example.anecdotru;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.anecdotru.NetworkUtils.generateURL;
import static com.example.anecdotru.NetworkUtils.responseFromURL;

public class MainActivity extends DefaultActivity {

    private FloatingActionButton addAnecdot;

    private ArrayList<ExampleList> mExampleList;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AnecdotAdaper anecdotAdaper;

    boolean ONE_JOKE = true;


    class QueryTask extends AsyncTask<URL, Void, String>
    {
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try{
                response = responseFromURL(urls[0]);}
            catch (IOException e) {
                e.printStackTrace();

            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String[] ArrayResponse;
            WorkWithResponse resp = new WorkWithResponse(response);
            resp.getNormalResponse();
            if (ONE_JOKE){
                response = resp.getmResponse();
                mExampleList.add(new ExampleList(R.drawable.ic_baseline_emoji_emotions_24,response));
            }
            else{
                ArrayResponse = resp.getArrayResponse();
                mExampleList.add(new ExampleList(R.drawable.ic_baseline_emoji_emotions_24,ArrayResponse[0]));
                mExampleList.add(new ExampleList(R.drawable.ic_baseline_emoji_emotions_24,ArrayResponse[1]));
                mExampleList.add(new ExampleList(R.drawable.ic_baseline_emoji_emotions_24,ArrayResponse[2]));
            }



            insertItem();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createExampleList();
        buildRecycleView();


        addAnecdot = findViewById(R.id.fab);
        addAnecdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedURL = generateURL();
                new QueryTask().execute(generatedURL);

            }
        });

    }

    public void insertItem(){
        anecdotAdaper.notifyDataSetChanged();
    }

    public void createExampleList(){
        mExampleList = new ArrayList<>();

    }
    public void buildRecycleView(){

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        anecdotAdaper = new AnecdotAdaper(mExampleList);

        recyclerView.setLayoutManager(layoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(anecdotAdaper);
    }


    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.PressFToRespect:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://docs.google.com/spreadsheets/d/17SkMybOUwEx1dnFWMe3VUzRbgy5Jwi2L4usZ5-yKYQk/edit#gid=1986591562"));
                startActivity(browserIntent);
                return true;
            case R.id.one_joke:
                ONE_JOKE = true;
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Один анекдот ", Toast.LENGTH_SHORT);
                toast1.show();
                return true;
            case R.id.three_jokes:
                ONE_JOKE = false;
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        "Целых три анекдота", Toast.LENGTH_SHORT);
                toast2.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
            new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mExampleList.remove(viewHolder.getAdapterPosition());
            anecdotAdaper.notifyDataSetChanged();
        }
    };
}