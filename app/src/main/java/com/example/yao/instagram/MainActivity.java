package com.example.yao.instagram;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.yao.gallery.WebInterface;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
public class MainActivity extends Activity {
    private JSONObject imageData;
    private GridView gridView;
    private static int TILE_WIDTH = 150;
    int number = 0;
    RequestImagesTask request;
    Context context;
    private final static String BASE_API_REQUEST_URL = "https://api.instagram.com/v1/tags/selfie/media/recent?access_token=";
    private final static String ACCESS_TOKEN = "1529602551.130defc.a3d527607994453f8c4ba415b6d4d244";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.image_grid_view);
        String requestURL = BASE_API_REQUEST_URL + ACCESS_TOKEN;
       // request = new RequestImagesTask (requestURL,this);
        request.execute();
        context = this;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gridView.setNumColumns(metrics.widthPixels / TILE_WIDTH);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(MainActivity.this, Image.class);
                try {
                    String url = imageData.getJSONArray("data")
                            .getJSONObject(position).getJSONObject("images")
                            .getJSONObject("standard_resolution")
                            .getString("url");
                    i.putExtra("url", url);
                } catch (JSONException e) {
                    i.putExtra("url", "");
                }
                startActivity(i);
            }
        });
        gridView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private class RequestImagesTask {
        String requestURL;
        public RequestImagesTask(String requestURL, this)        {
        }

        public void execute() {

        }
    }
}

  class ScrollViews extends ScrollView {
    private ScrollViewListener scrollViewListener = null;
    public ScrollViews(Context context){
        super(context);
    }
    public ScrollViews (Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
    }
    public ScrollViews(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        this.scrollViewListener = scrollViewListener;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollViewListener != null){
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}



