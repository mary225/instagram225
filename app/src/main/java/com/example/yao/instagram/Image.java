package com.example.yao.instagram;


import com.example.yao.gallery.BitmapDownload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Image extends Activity {
    ZoomView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        Intent i = getIntent();
        String url = i.getExtras().getString("url");
        if (url.length() > 0) {
            imageView = (ZoomView) findViewById(R.id.image_view);

            BitmapDownload task = new BitmapDownload(imageView);
            if (!task.searchCache(url))
                task.execute(url);
        } else {

        }
    }
}

