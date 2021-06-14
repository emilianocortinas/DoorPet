package com.example.doorpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class tutovideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutovideo);

        VideoView videotuto = findViewById(R.id.video_tuto);
        String videoPath = "android.resource://"+ getPackageName() + "/" + R.raw.videoprueba1;
        Uri uri = Uri.parse(videoPath);
        videotuto.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videotuto.setMediaController(mediaController);
        mediaController.setAnchorView(videotuto);
    }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), homescreen.class);
        finish();
        startActivity(i);
    }
}
