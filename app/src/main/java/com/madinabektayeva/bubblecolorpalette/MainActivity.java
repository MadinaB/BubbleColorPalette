package com.madinabektayeva.bubblecolorpalette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView vibrantView;
    private TextView vibrantLightView;
    private TextView vibrantDarkView;
    private TextView mutedView;
    private TextView mutedLightView;
    private TextView mutedDarkView;

    private  Button bubbleButton;
    private ImageView myImageView;

    private int bubbleSize= 7;
    private int backgroundImageCurrent = bubbleSize-1;
    int[] backgroundImages={R.drawable.bubble1,R.drawable.bubble2,R.drawable.bubble3,
                            R.drawable.bubble4,R.drawable.bubble5,R.drawable.bubble6, R.drawable.bubble7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bubbleButton = (Button)findViewById(R.id.button1);
        myImageView = (ImageView)findViewById(R.id.imageWallpaper);
        myImageView.setImageResource(backgroundImages[backgroundImageCurrent]);
        initViews();
        paintTextBackground();


        bubbleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                backgroundImageCurrent++;
                if(backgroundImageCurrent>=bubbleSize){
                    backgroundImageCurrent-=bubbleSize;
                }
                myImageView.setImageResource(backgroundImages[backgroundImageCurrent]);
                paintTextBackground();
            }
        });

    }

    private void initViews() {
        vibrantView = (TextView) findViewById(R.id.vibrantView);
        vibrantLightView = (TextView) findViewById(R.id.vibrantLightView);
        vibrantDarkView = (TextView) findViewById(R.id.vibrantDarkView);
        mutedView = (TextView) findViewById(R.id.mutedView);
        mutedLightView = (TextView) findViewById(R.id.mutedLightView);
        mutedDarkView = (TextView) findViewById(R.id.mutedDarkView);

    }

    private void paintTextBackground() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), backgroundImages[backgroundImageCurrent]);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultValue = 0x000000;
                int vibrant = palette.getVibrantColor(defaultValue);
                int vibrantLight = palette.getLightVibrantColor(defaultValue);
                int vibrantDark = palette.getDarkVibrantColor(defaultValue);
                int muted = palette.getMutedColor(defaultValue);
                int mutedLight = palette.getLightMutedColor(defaultValue);
                int mutedDark = palette.getDarkMutedColor(defaultValue);
                vibrantView.setBackgroundColor(vibrant);
                vibrantLightView.setBackgroundColor(vibrantLight);
                vibrantDarkView.setBackgroundColor(vibrantDark);
                mutedView.setBackgroundColor(muted);
                mutedLightView.setBackgroundColor(mutedLight);
                mutedDarkView.setBackgroundColor(mutedDark);
            }
        });


    }
}
