package com.example.jp.reclwviewdemo.activity;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.jp.reclwviewdemo.R;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      //  VideoView videoView=(VideoView)findViewById(R.id.v);
        final TextView rand=(TextView)findViewById(R.id.random);
        Button num=(Button)findViewById(R.id.btn);

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
                Random rnd = new Random();
                StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
              //  for (int i = 0; i < 5; i++)
                    //sb.append(chars[rnd.nextInt(chars.length)]);
                  //  sb.append(chars[rnd.nextInt(chars.length)]);
                    rand.setText(sb);

            }
        });


      /*  MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

        //specify the locationrse of media file
       // Uri uri=Uri.pa(Environment.getExternalStorageDirectory()+"/Clip/ClipVideos/mm.mp4");
       // String path = "android.resource://"+getPackageName()+"/"+R.raw.abc;
      *//* String path="https://www.youtube.com/watch?v=Pcv0aoOlsLM";
       String videoUrl = (path);*//*
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
       // videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
*/
    }
}
