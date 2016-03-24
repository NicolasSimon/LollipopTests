package com.example.nicolas.lollipopapp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nicolas.lollipopapp.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by Nicolas on 17/03/2016.
 * (c) Touchnote Ltd., 2015
 */
public class SecondActivity extends AppCompatActivity {
    public static final String              TAG_IMAGE_ID = "ImageResId";

    private ImageView                       mImageView;
    private ImageView                       mSecondImageView;
    private RelativeLayout                  mToRevealLayout;
    private ScrollView                      mScrollView;
    private TextView                        mTitle;
    private TextView                        mContent;
    private FloatingActionButton            mFab;

    private int                             mResId;

    private boolean                         mShowingGrayScale = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        mImageView = (ImageView) findViewById(R.id.image);
        mSecondImageView = (ImageView) findViewById(R.id.imageView2);
        mToRevealLayout = (RelativeLayout) findViewById(R.id.toRevealLayout);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mTitle = (TextView) findViewById(R.id.title);
        mContent = (TextView) findViewById(R.id.content);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShowingGrayScale) {
                    hideGrayImage();
                } else {
                    revealGrayImage();
                }
            }
        });

        mResId = getIntent().getIntExtra(TAG_IMAGE_ID, R.drawable.img01);

        applyPalette(generatePalette());

        Picasso.with(this).load(mResId).into(mImageView);

        mSecondImageView.setImageBitmap(toGrayScale(BitmapFactory.decodeResource(getResources(), mResId)));

        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mTitle.setTranslationY(500);
                mContent.setTranslationY(500);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getSharedElementEnterTransition().removeListener(this);
                mTitle.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                mContent.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
            }
            @Override
            public void onTransitionCancel(Transition transition) {}
            @Override
            public void onTransitionPause(Transition transition) {}
            @Override
            public void onTransitionResume(Transition transition) {}
        });
    }

    private void revealGrayImage() {
        rotateTickToCross();
        // get the center for the clipping circle
        int cx = (mFab.getLeft() + mFab.getRight()) / 2;//mToRevealLayout.getWidth() / 2;
        int cy = (mFab.getTop() + mFab.getBottom()) / 2;//mToRevealLayout.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(mToRevealLayout.getWidth(), mToRevealLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(mToRevealLayout, cx, cy, 0, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mShowingGrayScale = true;
            }
        });

        // make the view visible and start the animation
        mToRevealLayout.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void rotateTickToCross() {
        AnimatedVectorDrawable tickToCross = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_tick_to_cross);
        if (tickToCross != null) {
            mFab.setImageDrawable(tickToCross);
            tickToCross.start();
        }
    }

    private void rotateCrossToTick() {
        AnimatedVectorDrawable crossToTick = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_cross_to_tick);
        if (crossToTick != null) {
            mFab.setImageDrawable(crossToTick);
            crossToTick.start();
        }
    }

    private void hideGrayImage() {
        rotateCrossToTick();
        // get the center for the clipping circle
        int cx = (mFab.getLeft() + mFab.getRight()) / 2;//mToRevealLayout.getWidth() / 2;
        int cy = (mFab.getTop() + mFab.getBottom()) / 2;//mToRevealLayout.getHeight() / 2;

        // get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(mToRevealLayout.getWidth(), mToRevealLayout.getHeight());

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(mToRevealLayout, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mToRevealLayout.setVisibility(View.INVISIBLE);
                mShowingGrayScale = false;
            }
        });

        // start the animation
        anim.start();
    }

    private Palette generatePalette() {
        return (Palette.from(BitmapFactory.decodeResource(getResources(), mResId)).generate());
    }

    private void applyPalette(Palette palette) {
        int vibrant = palette.getVibrantColor(Color.BLACK);
        mScrollView.setBackgroundColor(palette.getMutedColor(Color.WHITE));
        mTitle.setTextColor(vibrant);
        mContent.setTextColor(palette.getLightVibrantColor(Color.BLACK));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(palette.getDarkMutedColor(0x00b5ff)));
            String htmlColor = String.format(Locale.US, "#%06X", (0xFFFFFF & Color.argb(0, Color.red(vibrant), Color.green(vibrant), Color.blue(vibrant))));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"" + htmlColor + "\">" + getString(R.string.app_name) + "</font>"));
        }
        getWindow().setStatusBarColor(palette.getDarkMutedColor(0x2282C4));
    }

    private Bitmap toGrayScale(Bitmap origin) {
        int width, height;
        height = origin.getHeight();
        width = origin.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(origin, 0, 0, paint);
        return (bmpGrayscale);
    }
}
