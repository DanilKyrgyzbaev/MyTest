package com.example.mytest;

import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.mytest.adapter.FeedAdapter;
import com.example.mytest.common.HTTPDataHandler;
import com.example.mytest.rssfeed.RSSObject;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RSSObject rssObject;
    RecyclerView recyclerView;

    private final String RSS_link = "https://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =  findViewById(R.id.recyclerview);

        toolbar = findViewById(R.id.toolbar);

            toolbar.setTitle("News");
        setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);

        loagRSS();

        RelativeLayout relativeLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

    }

    private void loagRSS() {
        AsyncTask<String,String,String> loadRSSAsyn = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Please wait ...");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                progressDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter feedAdapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsyn.execute(url_get_data.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh)
            loagRSS();
        return true;
    }

    public void setSupportActionBar(Toolbar toolbar) {
    }

}

