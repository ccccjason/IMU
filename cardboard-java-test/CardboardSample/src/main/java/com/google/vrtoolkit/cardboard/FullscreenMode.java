//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View.OnSystemUiVisibilityChangeListener;

class FullscreenMode
{
    static final int NAVIGATION_BAR_TIMEOUT_MS = 2000;
    Activity activity;

    FullscreenMode(Activity activity)
    {
        this.activity = activity;
    }

    void startFullscreenMode()
    {
        //TODO remove full screen flag
        //        this.activity.getWindow().addFlags(128);
        if (VERSION.SDK_INT < 19) {
            final Handler handler = new Handler();
            this.activity.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
            new OnSystemUiVisibilityChangeListener() {
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & 2) == 0) {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                FullscreenMode.this.setFullscreenMode();
                            }
                        }, 2000L);
                    }

                }
            });
        }

    }

    void onWindowFocusChanged(boolean hasFocus)
    {
        if (hasFocus) {
            this.setFullscreenMode();
        }

    }

    void setFullscreenMode()
    {
        //TODO make cardboard not hide navigation bar
        //        this.activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
