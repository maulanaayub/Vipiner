package vision.google.com.vipiner;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import static vision.google.com.vipiner.R.id.progressbar;

/**
 * Created by Asus on 5/29/2017.
 */

public class VideoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ProgressBar progressBar = null;
        VideoView videoView = null;

        Bundle b = getIntent().getExtras();
        String link = b.getString("parse_link");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(R.id.videoview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //setting kontrol media
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        //setting url tujuan
        Uri uri = Uri.parse(link);
        //atur videonya
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        progressBar.setVisibility(View.VISIBLE);
        final ProgressBar finalProgressBar = progressBar;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                   int arg2) {
                        // TODO Auto-generated method stub
                        finalProgressBar.setVisibility(View.GONE);
                        mp.start();
                    }
                });
            }
        });
    return;
    }




    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
       }

}