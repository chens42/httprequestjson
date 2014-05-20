package com.example.puppy.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.puppy.app.db.DatabaseHelper;
import com.example.puppy.app.model.Post;
import com.example.puppy.app.model.Posts;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    ProgressDialog pDialog;
    private static String url = "https://raw.githubusercontent.com/chens42/android-practise/master/puppy/app/tumblr.json";
    ShowAdaptor adapter = null;
    ListView listView = null;
    Posts posts=new Posts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        List<Post> posts1 = getHelper().getPostDAO().queryForAll();
        new getContent().execute();
    }

    public class getContent extends AsyncTask<Void, Void, Posts> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Posts doInBackground(Void... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse;
            HttpEntity httpEntity;
            String jsonStr = null;

            try {
                httpResponse = httpClient.execute(httpGet);
                httpEntity = httpResponse.getEntity();
                jsonStr = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (jsonStr != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                try {
                    posts = objectMapper.readValue(jsonStr, Posts.class);
                    for ( Post post:posts.getPosts()){
                        getHelper().getPostDAO().create(post);
                    }
                    return posts;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{

            }
            return posts;
        }

        @Override
        protected void onPostExecute(Posts posts) {
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            adapter = new ShowAdaptor(posts);
            listView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ShowAdaptor extends ArrayAdapter {
        private Posts posts;
        private List<Post> increment;

        public ShowAdaptor(Posts posts) {
            super(MainActivity.this, R.layout.list_item, posts.getPosts());
            increment= posts.getPosts();
            this.posts = posts;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position == posts.getPosts().size() - 1) {
                pDialog.show();
                adapter.addAll(increment);
                pDialog.dismiss();
            }
            View row = convertView;
            PostHolder holder;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.list_item, parent, false);
                holder = new PostHolder(row);
                row.setTag(holder);
            } else {
                holder = (PostHolder) row.getTag();
            }
            holder.add(posts.getPosts().get(position));
            return row;

        }

    }
}
