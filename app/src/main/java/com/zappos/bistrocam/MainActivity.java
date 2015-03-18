package com.zappos.bistrocam;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();
    @InjectView(R.id.video_view1)
    VideoView mVideoView1;

    @InjectView(R.id.video_view2)
    VideoView mVideoView2;

    private static final String URL = "http://edge04.hdontap.com/hdstreams/eagle1_skidaway/media_w969064102_23286.ts";
    private static final String RTSP1 = "rtsp://a2047.v1412b.c1412.g.vq.akamaistream.net/5/2047/1412/1_h264_350/1a1a1ae555c531960166df4dbc3095c327960d7be756b71b49aa1576e344addb3ead1a497aaedf11/8848125_1_350.mov";
    private static final String RTSP2 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";

    private static final String RTSP3 = "http://www.opticodec.com/test/tropic.mp4";

    private static final String TIME_SQUARE = "http://static.earthcamcdn.com/swf/ectv/ectv.swf?20150309";
    private static final String FISH_CAM = "http://fish.schou.me/fish.m3u8";
    private static final String SOCCER = "http://144.76.44.78:8000/live/Royal/Skype:Micro2me/79.ts";
    private static final String MOVIE = "http://144.76.44.78:8000/live/Royal/Skype:Micro2me/39.ts";
    private static final String BGOAL = "http://rtmp.infomaniak.ch/livecast/telebielinguech/hasbahca.m3u8";
    private static final String FOX = "http://200.76.77.237/LIVE/H01/CANAL251/PROFILE03.m3u8";
    private static final String SPORT = "http://46.249.213.93/iPhone/HLS_TS/broadcast/sportmax-tablet.3gp/sportmax-tablet.3gp-mr397k.m3u8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        playVideo(mVideoView1, SPORT);
        playVideo(mVideoView2, BGOAL);

    }

    
    private void playVideo(VideoView vv, String url) {

        vv.setVideoURI(Uri.parse(url));
        vv.setMediaController(new MediaController(this));
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onError");
                return true;
            }
        });

        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion");
                mp.start();
            }
        });

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared");
                mp.start();
            }
        });

        vv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch");
                return true;
            }
        });
    }
}
