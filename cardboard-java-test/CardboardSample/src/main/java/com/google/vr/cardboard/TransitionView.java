/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import android.content.Context;
/*     */ import android.graphics.drawable.AnimationDrawable;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.OrientationEventListener;
/*     */ import android.view.View;
/*     */ import android.view.View.OnClickListener;
/*     */ import android.view.View.OnTouchListener;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.view.animation.AccelerateInterpolator;
/*     */ import android.view.animation.AlphaAnimation;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.widget.FrameLayout;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import com.google.vrtoolkit.cardboard.R;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class TransitionView
/*     */   extends FrameLayout
/*     */   implements View.OnClickListener, View.OnTouchListener
/*     */
{
    /*     */   public static final int TRANSITION_BACKGROUND_COLOR = -12232092;
    /*     */   public static final int ALREADY_LANDSCAPE_LEFT_TRANSITION_DELAY_MS =
        2000;
    /*     */   public static final int TRANSITION_ANIMATION_DURATION_MS = 500;
    /*     */   private static final int VIEW_CORRECTION_ROTATION_DEGREES = -90;
    /*     */   private static final int PORTRAIT_TOLERANCE_DEGREES = 45;
    /*     */   private static final int LANDSCAPE_TOLERANCE_DEGREES = 5;
    /*  72 */   private int orientation = -1;
    /*     */
    /*     */
    /*     */   private OrientationEventListener orientationEventListener;
    /*     */
    /*     */
    /*     */   private boolean rotationChecked;
    /*     */
    /*     */
    /*     */   private boolean useCustomTransitionLayout;
    /*     */
    /*     */
    /*     */   private String viewerName;
    /*     */
    /*     */
    /*     */   private AnimationDrawable animationDrawable;
    /*     */
    /*     */   private TransitionListener transitionListener;
    /*     */
    /*     */   private ImageView backButton;
    /*     */
    /*     */   private Runnable backButtonRunnable;
    /*     */
    /*     */
    /*     */   public TransitionView(Context context)
    /*     */
    {
        /*  98 */     super(context);
        /*  99 */     setOnTouchListener(this);
        /* 100 */     setBackground(new ColorDrawable(-12232092));
        /* 101 */     inflateContentView(R.layout.transition_view);
        /*     */
        /*     */
        /*     */
        /* 105 */     super.setVisibility(8);
        /*     */
    }
    /*     */
    /*     */   private void inflateContentView(int layoutId)
    {
        /* 109 */     removeAllViews();
        /*     */
        /* 111 */     LayoutInflater.from(getContext()).inflate(layoutId, this, true);
        /* 112 */     View switchActionView = findViewById(
                R.id.transition_switch_action);
        /* 113 */     switchActionView.setOnClickListener(this);
        /* 114 */     updateBackButtonVisibilityAndAttachment();
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void setCustomTransitionLayout(int transitionLayoutId,
            int backgroundColor)
    /*     */
    {
        /* 123 */     this.useCustomTransitionLayout = true;
        /* 124 */     inflateContentView(transitionLayoutId);
        /* 125 */     setBackground(new ColorDrawable(backgroundColor));
        /*     */
        /* 127 */     setViewerName(this.viewerName);
        /*     */
        /* 129 */     ImageView icon = (ImageView)findViewById(R.id.transition_icon);
        /* 130 */     Drawable drawable = icon.getDrawable();

        /* 131 */     if ((drawable != null) &&
                          ((drawable instanceof AnimationDrawable))) {
            /* 132 */       this.animationDrawable = ((AnimationDrawable)drawable);
            /* 133 */       this.animationDrawable.start();
            /*     */
        }

        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setViewerName(String viewerName)
    /*     */
    {
        /* 141 */     this.viewerName = viewerName;
        /* 142 */     TextView transitionText = (TextView)findViewById(
                R.id.transition_text);

        /* 143 */     if (viewerName != null) {
            /* 144 */       transitionText.setText(getContext().getString(
                    R.string.place_your_viewer_into_viewer_format, new Object[] { viewerName }));
            /*     */
        }
        /*     */     else {
            /* 147 */       transitionText.setText(getContext().getString(
                    R.string.place_your_phone_into_cardboard));
            /*     */
        }

        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void setBackButtonListener(Runnable runnable)
    /*     */
    {
        /* 158 */     this.backButtonRunnable = runnable;
        /* 159 */     updateBackButtonVisibilityAndAttachment();
        /*     */
    }
    /*     */
    /*     */   public void setVisibility(int visibility)
    /*     */
    {
        /* 164 */     int previousVisibility = getVisibility();
        /* 165 */     super.setVisibility(visibility);

        /*     */

        /* 167 */     if (previousVisibility != visibility) {
            /* 168 */       if (visibility == 0) {
                /* 169 */         startOrientationMonitor();
                /*     */
            } else {
                /* 171 */         stopOrientationMonitor();
                /*     */
            }

            /*     */
        }

        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setTransitionListener(TransitionListener
            transitionListener)
    /*     */
    {
        /* 180 */     this.transitionListener = transitionListener;
        /*     */
    }
    /*     */
    /*     */   private void startOrientationMonitor()
    {
        /* 184 */     if (this.orientationEventListener != null) {
            /* 185 */       return;
            /*     */
        }

        /*     */
        /* 188 */     this.orientationEventListener = new OrientationEventListener(
            getContext())
        /*     */     {
            /*     */       public void onOrientationChanged(int orientation) {
                /* 191 */         TransitionView.this.orientation = orientation;

                /*     */

                /* 193 */         if (!TransitionView.this.rotationChecked) {
                    /* 194 */           TransitionView.this.rotateViewIfNeeded();
                    /*     */
                    /*     */
                    /*     */
                }
                /* 198 */         else if (TransitionView.isLandscapeLeft(orientation)) {
                    /* 199 */           TransitionView.this.fadeOutAndRemove(false);
                    /* 200 */
                } else if (!TransitionView.isLandscapeRight(orientation)) {}

                /*     */
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /* 206 */
        };
        /* 207 */     this.orientationEventListener.enable();
        /*     */
    }
    /*     */
    /*     */   private void stopOrientationMonitor()
    {
        /* 211 */     if (this.orientationEventListener == null) {
            /* 212 */       return;
            /*     */
        }

        /*     */
        /* 215 */     this.orientation = -1;
        /* 216 */     this.orientationEventListener.disable();
        /* 217 */     this.orientationEventListener = null;
        /*     */
    }
    /*     */
    /*     */   protected void onAttachedToWindow()
    /*     */
    {
        /* 222 */     super.onAttachedToWindow();

        /* 223 */     if (this.orientationEventListener != null) {
            /* 224 */       this.orientationEventListener.enable();
            /*     */
        }

        /*     */
        /* 227 */     rotateViewIfNeeded();
        /*     */
    }
    /*     */
    /*     */   protected void onDetachedFromWindow()
    /*     */
    {
        /* 232 */     if (this.orientationEventListener != null) {
            /* 233 */       this.orientation = -1;
            /* 234 */       this.orientationEventListener.disable();
            /*     */
        }

        /* 236 */     super.onDetachedFromWindow();
        /*     */
    }
    /*     */
    /*     */
    /*     */   private void rotateViewIfNeeded()
    /*     */
    {
        /* 242 */     if ((getWidth() <= 0) || (getHeight() <= 0) ||
                          (this.orientation == -1) || (this.orientationEventListener == null) ||
                          (this.rotationChecked))
            /*     */     {
            /*     */
            /* 245 */       return;
            /*     */
        }

        /*     */
        /* 248 */     boolean viewPortrait = getWidth() < getHeight();
        /* 249 */     boolean devicePortrait = isPortrait(this.orientation);

        /*     */
        /*     */
        /*     */

        /* 253 */     if (viewPortrait != devicePortrait) {
            /* 254 */       View frame = findViewById(R.id.transition_frame);
            /* 255 */       int frameWidth = frame.getWidth();
            /* 256 */       int frameHeight = frame.getHeight();

            /*     */
            /*     */
            /*     */
            /*     */

            /* 261 */       if ((Build.VERSION.SDK_INT >= 17) &&
                                /* 262 */ (getLayoutDirection() == 1)) {
                /* 263 */         frame.setPivotX(frameHeight - frame.getPivotX());
                /* 264 */         frame.setPivotY(frameWidth - frame.getPivotY());
                /*     */
            }

            /*     */
            /*     */
            /*     */
            /*     */
            /* 270 */       frame.setRotation(-90.0F);
            /*     */
            /* 272 */       frame.setTranslationX((frameWidth - frameHeight) / 2);
            /* 273 */       frame.setTranslationY((frameHeight - frameWidth) / 2);
            /* 274 */       ViewGroup.LayoutParams layoutParams = frame.getLayoutParams();
            /* 275 */       layoutParams.height = frameWidth;
            /* 276 */       layoutParams.width = frameHeight;
            /*     */
            /* 278 */       frame.requestLayout();
            /*     */
        }

        /*     */

        /* 281 */     if (!devicePortrait)
            /*     */     {
            /*     */
            /*     */
            /*     */
            /* 286 */       findViewById(R.id.transition_bottom_frame).setVisibility(8);

            /*     */

            /* 288 */       if (this.useCustomTransitionLayout)
                /*     */       {
                /*     */
                /* 291 */         TextView transitionText = (TextView)findViewById(
                            R.id.transition_text);

                /* 292 */         if (transitionText != null) {
                    /* 293 */           transitionText.setMaxWidth(2 *
                            transitionText.getMaxWidth());
                    /*     */
                }

                /*     */
                /*     */
                /* 297 */         View transitionIcon = findViewById(R.id.transition_icon);

                /* 298 */         if (transitionIcon != null)
                    /*     */         {
                    /* 300 */           RelativeLayout.LayoutParams iconLayoutParams =
                        (RelativeLayout.LayoutParams)transitionIcon.getLayoutParams();
                    /* 301 */           int iconTopMargin = iconLayoutParams.topMargin;
                    /* 302 */           int adjustedIconTopMargin = -1 * iconTopMargin;
                    /* 303 */           iconLayoutParams.setMargins(adjustedIconTopMargin, 0, 0, 0);
                    /* 304 */           transitionIcon.requestLayout();
                    /*     */
                }

                /*     */
            }

            /*     */
        }

        /*     */
        /*     */
        /*     */
        /* 311 */     this.rotationChecked = true;

        /*     */
        /*     */
        /*     */

        /* 315 */     if (isLandscapeLeft(this.orientation)) {
            /* 316 */       fadeOutAndRemove(true);
            /*     */
        }

        /*     */
    }
    /*     */
    /*     */   private void fadeOutAndRemove(boolean delay)
    /*     */
    {
        /* 322 */     stopOrientationMonitor();
        /*     */
        /* 324 */     Animation fadeOut = new AlphaAnimation(1.0F, 0.0F);
        /* 325 */     fadeOut.setInterpolator(new AccelerateInterpolator());
        /* 326 */     fadeOut.setRepeatCount(0);
        /* 327 */     fadeOut.setDuration(500L);

        /*     */

        /* 329 */     if (delay) {
            /* 330 */       fadeOut.setStartOffset(2000L);
            /*     */
        }

        /*     */
        /* 333 */     fadeOut.setAnimationListener(new Animation.AnimationListener()
        /*     */     {
            /*     */       public void onAnimationStart(Animation animation) {}
            /*     */
            /*     */       public void onAnimationEnd(Animation animation)
            /*     */       {
                /* 339 */         TransitionView.this.setVisibility(8);
                /* 340 */ ((ViewGroup)TransitionView.this.getParent()).removeView(
                    TransitionView.this);

                /*     */
                /*     */

                /* 343 */         if (TransitionView.this.animationDrawable != null) {
                    /* 344 */           TransitionView.this.animationDrawable.stop();
                    /* 345 */           TransitionView.this.animationDrawable = null;
                    /*     */
                }

                /*     */
                /*     */
                /*     */

                /* 350 */         if (TransitionView.this.transitionListener != null) {
                    /* 351 */           TransitionView.this.transitionListener.onTransitionDone();
                    /*     */
                }

                /*     */
            }
            /*     */
            /*     */
            /*     */
            /*     */       public void onAnimationRepeat(Animation animation) {}
            /* 358 */
        });
        /* 359 */     startAnimation(fadeOut);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean onTouch(View v, MotionEvent event)
    /*     */
    {
        /* 366 */     return true;
        /*     */
    }
    /*     */
    /*     */
    /*     */   public void onClick(View v)
    /*     */
    {
        /* 372 */     UiUtils.launchOrInstallCardboard(getContext(), false);

        /* 373 */     if (this.transitionListener != null) {
            /* 374 */       this.transitionListener.onSwitchViewer();
            /*     */
        }

        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private void updateBackButtonVisibilityAndAttachment()
    /*     */
    {
        /* 383 */     boolean attach = this.backButtonRunnable != null;

        /* 384 */     if (!attach) {
            /* 385 */       if (this.backButton != null) {
                /* 386 */         this.backButton.setVisibility(8);
                /* 387 */         this.backButton.setTag(null);
                /* 388 */         this.backButton.setOnClickListener(null);
                /*     */
            }

            /* 390 */       return;
            /*     */
        }

        /*     */

        /* 393 */     if (this.backButton == null)
            /*     */     {
            /* 395 */       this.backButton = UiLayerUtils.createBackButton(getContext());
            /*     */
            /* 397 */       RelativeLayout.LayoutParams backLayout = new
            RelativeLayout.LayoutParams(this.backButton.getLayoutParams());
            /* 398 */       backLayout.addRule(10);
            /*     */
            /*     */
            /*     */
            /* 402 */       backLayout.addRule(Build.VERSION.SDK_INT >= 17 ?
                                               /*     */
                                               /* 404 */         20 :
                                               /* 405 */         9);
            /* 406 */       this.backButton.setLayoutParams(backLayout);
            /*     */
        }

        /*     */
        /* 409 */     ViewGroup frame = (ViewGroup)findViewById(R.id.transition_frame);

        /* 410 */     if (frame.indexOfChild(this.backButton) == -1)
            /*     */     {
            /*     */
            /*     */
            /* 414 */       if (this.backButton.getParent() != null) {
                /* 415 */         ViewGroup parent = (ViewGroup)this.backButton.getParent();
                /* 416 */         parent.removeView(this.backButton);
                /*     */
            }

            /*     */
            /*     */
            /* 420 */       frame.addView(this.backButton);
            /*     */
        }

        /*     */
        /* 423 */     this.backButton.setTag(this.backButtonRunnable);
        /* 424 */     this.backButton.setVisibility(0);
        /* 425 */     this.backButton.setOnClickListener(new View.OnClickListener()
        /*     */     {
            /*     */       public void onClick(View v)
            /*     */       {
                /* 429 */         TransitionView.this.backButtonRunnable.run();
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static boolean isPortrait(int orientationDegrees)
    /*     */
    {
        /* 439 */     return Math.abs(orientationDegrees - 180) > 135;
        /*     */
    }
    /*     */
    /*     */   private static boolean isLandscapeLeft(int orientationDegrees)
    {
        /* 443 */     return Math.abs(orientationDegrees - 270) < 5;
        /*     */
    }
    /*     */
    /*     */   private static boolean isLandscapeRight(int orientationDegrees)
    {
        /* 447 */     return Math.abs(orientationDegrees - 90) < 5;
        /*     */
    }
    /*     */
    /*     */   public static abstract interface TransitionListener
    /*     */   {
        /*     */     public abstract void onTransitionDone();
        /*     */
        /*     */     public abstract void onSwitchViewer();
        /*     */
    }
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/TransitionView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */