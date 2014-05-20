package com.example.puppy.app;

import android.view.View;
import android.widget.TextView;
import com.example.puppy.app.model.Post;
public class PostHolder {
    TextView identify;
    TextView urlAddress;
    TextView image1280;
    TextView image500;
    TextView image400;
    TextView image250;
    TextView image100;
    TextView image75;
    public PostHolder(View view) {
        identify = (TextView) view.findViewById(R.id.id);
        urlAddress= (TextView) view.findViewById(R.id.urlAddress);
        image1280=(TextView)view.findViewById(R.id.image1280);
        image500=(TextView)view.findViewById(R.id.image500);
        image400=(TextView)view.findViewById(R.id.image400);
        image250=(TextView)view.findViewById(R.id.image250);
        image100=(TextView)view.findViewById(R.id.image100);
        image75=(TextView)view.findViewById(R.id.image75);
    }
    public void add(Post post) {
        identify.setText("" + post.getId());
        urlAddress.setText("" + post.getUrl());
        image1280.setText(post.getPhotoUrl1280());
        image500.setText(post.getPhotoUrl500());
        image400.setText(post.getPhotoUrl400());
        image250.setText(post.getPhotoUrl250());
        image100.setText(post.getPhotoUrl100());
        image75.setText(post.getPhotoUrl75());
    }
}
