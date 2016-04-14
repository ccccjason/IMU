/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.os.Build;
/*     */ import android.os.Handler;
/*     */ import android.os.Looper;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.View.OnClickListener;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ // import android.widget.RelativeLayout.LayoutParams;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class UiLayer
/*     */
{
    /*  28 */   private static final String TAG = UiLayer.class.getSimpleName();
    /*     */
    /*     */
    /*     */   private static final int ALIGNMENT_MARKER_LINE_WIDTH = 4;
    /*     */
    /*     */
    /*     */   private static final int ALIGNMENT_MARKER_LINE_COLOR = -13487566;
    /*     */
    /*     */   private final Context context;
    /*     */
    /*     */   private final Handler handler;
    /*     */
    /*     */   private final DisplayMetrics metrics;
    /*     */
    /*     */   private ImageView settingsButton;
    /*     */
    /*     */   private ImageView backButton;
    /*     */
    /*     */   private View alignmentMarker;
    /*     */
    /*     */   private TransitionView transitionView;
    /*     */
    /*     */   private final RelativeLayout rootLayout;
    /*     */
    /*  52 */   private volatile boolean isSettingsButtonEnabled = true;
    /*  53 */   private volatile boolean isAlignmentMarkerEnabled = false;
    /*     */
    /*     */
    /*     */
    /*  57 */   private volatile Runnable backButtonRunnable = null;
    /*     */
    /*  59 */   private volatile boolean transitionViewEnabled = false;
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private volatile String viewerName;
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public UiLayer(Context context)
    /*     */
    {
        /*  72 */     this.context = context;
        /*     */
        /*  74 */     this.handler = new Handler(context.getMainLooper());
        /*  75 */     this.metrics = UiLayerUtils.getDisplayMetrics(context);
        /*  76 */     this.rootLayout = new RelativeLayout(context);
        /*  77 */     initializeViews();
        /*     */
    }
    /*     */
    /*     */   private void initializeViews()
    /*     */
    {
        /*  82 */     this.settingsButton = layoutButton(
                                                /*  83 */       UiLayerUtils.createSettingsButton(this.context),
                                                this.isSettingsButtonEnabled, new int[] { 12, 13 });
        /*     */
        /*     */
        /*     */
        /*  87 */     this.settingsButton.setOnClickListener(new View.OnClickListener()
        /*     */     {
            /*     */       public void onClick(View v) {
                /*  90 */         UiUtils.launchOrInstallCardboard(v.getContext());
                /*     */
            }
            /*  92 */
        });
        /*  93 */     this.rootLayout.addView(this.settingsButton);
        /*     */
        /*     */
        /*     */
        /*     */
        /*  98 */     this.backButton = layoutButton(
                                            /*  99 */       UiLayerUtils.createBackButton(this.context),
                                            /* 100 */       getBackButtonEnabled(), new int[] { 10, Build.VERSION.SDK_INT >= 17 ?
                                                    /*     */
                                                    /*     */
                                                    /* 103 */       20 :
                                                    /* 104 */       9
                                                                                              });
        /* 105 */     this.backButton.setOnClickListener(new View.OnClickListener()
        /*     */     {
            /*     */       public void onClick(View v)
            /*     */       {
                /* 109 */         Runnable runnable = UiLayer.this.backButtonRunnable;

                /* 110 */         if (runnable != null) {
                    /* 111 */           runnable.run();
                    /*     */
                }

                /*     */
            }
            /* 114 */
        });
        /* 115 */     this.rootLayout.addView(this.backButton);
        /*     */
        /* 117 */     int alignmentMarginY =
            this.settingsButton.getLayoutParams().height;
        /* 118 */     this.alignmentMarker = new View(this.context);
        /* 119 */     this.alignmentMarker.setBackground(new ColorDrawable(-13487566));
        /* 120 */     RelativeLayout.LayoutParams layoutParams = new
        RelativeLayout.LayoutParams((int)(4.0F * this.metrics.density), -1);
        /*     */
        /*     */
        /* 123 */     layoutParams.addRule(13);
        /* 124 */     layoutParams.setMargins(0, alignmentMarginY, 0, alignmentMarginY);
        /* 125 */     this.alignmentMarker.setLayoutParams(layoutParams);
        /* 126 */     this.alignmentMarker.setVisibility(computeVisibility(
                    this.isAlignmentMarkerEnabled));
        /* 127 */     this.rootLayout.addView(this.alignmentMarker);
        /*     */
    }
    /*     */
    /*     */   private ImageView layoutButton(ImageView button, boolean enabled,
            int... layoutParams)
    /*     */
    {
        /* 132 */     RelativeLayout.LayoutParams buttonLayoutParams = new
        RelativeLayout.LayoutParams(button.getLayoutParams());

        /* 133 */     for (int layoutParam : layoutParams) {
            /* 134 */       buttonLayoutParams.addRule(layoutParam);
            /*     */
        }

        /* 136 */     button.setLayoutParams(buttonLayoutParams);
        /* 137 */     button.setVisibility(computeVisibility(enabled));
        /* 138 */     return button;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private TransitionView getTransitionView()
    /*     */
    {
        /* 147 */     if (this.transitionView == null) {
            /* 148 */       this.transitionView = new TransitionView(this.context);
            /* 149 */       RelativeLayout.LayoutParams transitionViewLayoutParams = new
            RelativeLayout.LayoutParams(-1, -1);
            /*     */
            /*     */
            /* 152 */       this.transitionView.setLayoutParams(transitionViewLayoutParams);
            /* 153 */       this.transitionView.setVisibility(computeVisibility(
                        this.transitionViewEnabled));

            /* 154 */       if (this.viewerName != null) {
                /* 155 */         this.transitionView.setViewerName(this.viewerName);
                /*     */
            }

            /* 157 */       this.transitionView.setBackButtonListener(
                this.backButtonRunnable);
            /* 158 */       this.rootLayout.addView(this.transitionView);
            /*     */
        }

        /* 160 */     return this.transitionView;
        /*     */
    }
    /*     */
    /*     */
    /*     */   private void runOnUiThread(Runnable r)
    /*     */
    {
        /* 166 */     if (Looper.myLooper() == this.handler.getLooper()) {
            /* 167 */       r.run();
            /* 168 */       return;
            /*     */
        }

        /* 170 */     this.handler.post(r);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static int computeVisibility(boolean enabled)
    /*     */
    {
        /* 179 */     return enabled ? 0 : 8;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void attachUiLayer(final ViewGroup parentView)
    /*     */
    {
        /* 189 */     if (parentView == null) {
            /* 190 */       throw new
            RuntimeException("A valid ViewGroup must be provided.");
            /*     */
        }

        /* 192 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 195 */         parentView.addView(UiLayer.this.rootLayout);
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
    /*     */
    /*     */
    /*     */   public void attachUiLayerToActivity(final Context activityContext)
    /*     */
    {
        /* 207 */     if ((activityContext == null) ||
                          (!(activityContext instanceof Activity))) {
            /* 208 */       throw new
            RuntimeException("A valid activityContext must be provided.");
            /*     */
        }

        /* 210 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run()
            /*     */       {
                /* 214 */ ((Activity)activityContext).addContentView(UiLayer.this.rootLayout,
                        new RelativeLayout.LayoutParams(-1, -1));
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
    /*     */
    /*     */
    /*     */   public void setEnabled(final boolean enabled)
    /*     */
    {
        /* 226 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 229 */         UiLayer.this.rootLayout.setVisibility(
                    UiLayer.computeVisibility(enabled));
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setSettingsButtonEnabled(final boolean enabled)
    /*     */
    {
        /* 238 */     this.isSettingsButtonEnabled = enabled;
        /*     */
        /*     */
        /*     */
        /* 242 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 245 */         UiLayer.this.settingsButton.setVisibility(
                    UiLayer.computeVisibility(enabled));
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
    /*     */   public void setBackButtonListener(final Runnable runnable)
    /*     */
    {
        /* 255 */     this.backButtonRunnable = runnable;
        /* 256 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 259 */         boolean visible = runnable != null;
                /* 260 */         UiLayer.this.backButton.setVisibility(
                    UiLayer.computeVisibility(visible));

                /* 261 */         if (UiLayer.this.transitionView != null) {
                    /* 262 */           UiLayer.this.transitionView.setBackButtonListener(runnable);
                    /*     */
                }

                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setAlignmentMarkerEnabled(final boolean enabled)
    /*     */
    {
        /* 272 */     this.isAlignmentMarkerEnabled = enabled;
        /* 273 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 276 */         UiLayer.this.alignmentMarker.setVisibility(
                    UiLayer.computeVisibility(enabled));
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setTransitionViewEnabled(final boolean enabled)
    /*     */
    {
        /* 285 */     this.transitionViewEnabled = enabled;
        /* 286 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 289 */         if ((!enabled) && (UiLayer.this.transitionView == null)) {
                    /* 290 */           return;
                    /*     */
                }

                /* 292 */         UiLayer.this.getTransitionView().setVisibility(
                    UiLayer.computeVisibility(enabled));
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean getTransitionViewEnabled()
    /*     */
    {
        /* 301 */     return this.transitionViewEnabled;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void setTransitionViewListener(final
            TransitionView.TransitionListener listener)
    /*     */
    {
        /* 308 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 311 */         UiLayer.this.transitionView.setTransitionListener(listener);
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */   public void setViewerName(final String viewerName)
    /*     */
    {
        /* 318 */     this.viewerName = viewerName;
        /* 319 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */
            /*     */       public void run()
            /*     */       {
                /* 324 */         if (UiLayer.this.transitionView != null) {
                    /* 325 */           UiLayer.this.transitionView.setViewerName(viewerName);
                    /*     */
                }

                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */   public void setCustomTransitionLayout(final int transitionLayoutId,
            final int backgroundColor)
    /*     */
    {
        /* 333 */     runOnUiThread(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /* 336 */         UiLayer.this.getTransitionView().setCustomTransitionLayout(
                    transitionLayoutId, backgroundColor);
                /*     */
            }
            /*     */
        });
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean getSettingsButtonEnabled()
    /*     */
    {
        /* 345 */     return this.isSettingsButtonEnabled;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean getBackButtonEnabled()
    /*     */
    {
        /* 352 */     return this.backButtonRunnable != null;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean getAlignmentMarkerEnabled()
    /*     */
    {
        /* 359 */     return this.isAlignmentMarkerEnabled;
        /*     */
    }
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/UiLayer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */