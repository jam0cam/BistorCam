package com.zappos.bistrocam;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class TextureActivity extends ActionBarActivity {

    private static final String TAG = TextureActivity.class.getName();


    @InjectView(R.id.texture_view)
    TextureView mVideo1;

    @InjectView(R.id.texture_view2)
    TextureView mVideo2;

    @InjectView(R.id.texture_view3)
    TextureView mVideo3;

    @InjectView(R.id.texture_view4)
    TextureView mVideo4;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

//
//    @InjectView(R.id.linear_layout)
//    LinearLayout mContainer;


    private static final String MOVIE = "http://212.115.255.195:8080/hls/tv1000action.m3u8";
    private static final String CARTOON = "http://200.76.77.237/LIVE/H01/CANAL251/PROFILE03.m3u8";
    private static final String BGOAL = "http://rtmp.infomaniak.ch/livecast/telebielinguech/hasbahca.m3u8";
    private static final String BISTRO1 = "http://theandroidguy.com:8090/channel1.webm";
    private static final String BISTRO2 = "http://theandroidguy.com:8090/channel2.webm";
    private static final String BUCK_BUNNY = "http://video.webmfiles.org/big-buck-bunny_trailer.webm";

    private MediaPlayer mMediaPlayer;
    private int mVideoWidth;
    private int mVideoHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_texture));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mVideoWidth = size.x;
        mVideoHeight = mVideoWidth * 2/3;

        mVideo1.setLayoutParams(new LinearLayout.LayoutParams(mVideoWidth, mVideoHeight));
        mVideo2.setLayoutParams(new LinearLayout.LayoutParams(mVideoWidth, mVideoHeight));
        mVideo3.setLayoutParams(new LinearLayout.LayoutParams(mVideoWidth, mVideoHeight));
//        mVideo4.setLayoutParams(new LinearLayout.LayoutParams(mVideoWidth, mVideoHeight));

        playVideo(mVideo1, BISTRO1);
        playVideo(mVideo2, BUCK_BUNNY);
        playVideo(mVideo3, BGOAL);
//        playVideo(mVideo4, BISTRO2);

    }

    private void playVideo(final TextureView tv, final String url) {
        tv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int newWidth, int newHeight) {
                Log.d(TAG, "onSurfaceTextureAvailable");

                Surface s = new Surface(surface);

                Matrix txform = new Matrix();
                tv.getTransform(txform);
                txform.setScale((float) newWidth / mVideoWidth, (float) newHeight / mVideoHeight);
                tv.setTransform(txform);


                try {
                    mMediaPlayer= new MediaPlayer();
                    mMediaPlayer.setDataSource(url);
                    mMediaPlayer.setSurface(s);
                    mMediaPlayer.prepareAsync();
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Log.e(TAG, "onCompletion");
                        }
                    });
                    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.e(TAG, "onError");
                            return false;
                        }
                    });

                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            Log.d(TAG, "onPrepared");
                            mp.start();
                        }
                    });

                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d(TAG, "Exception");
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

}
