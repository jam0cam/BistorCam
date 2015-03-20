package com.zappos.bistrocam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.btn_sample)
    Button mSample;

    @InjectView(R.id.btn_bistro)
    Button mBistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_sample)
    public void sampleClicked() {
        Intent intent = new Intent(this, TextureActivity.class);
        intent.putExtra("sample", true);
        startActivity(intent);
    }

    @OnClick(R.id.btn_bistro)
    public void bistroClicked() {
        Intent intent = new Intent(this, TextureActivity.class);
        intent.putExtra("sample", false);
        startActivity(intent);
    }

}
