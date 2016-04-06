/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import android.content.Context;
/*     */ import android.content.res.Configuration;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Matrix;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.os.Build;
/*     */ import android.util.Base64;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.Display;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.WindowManager;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.ImageView.ScaleType;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class UiLayerUtils
/*     */
{
    /*     */   private static final int ICON_WIDTH_DP = 28;
    /*     */   private static final float TOUCH_SLOP_FACTOR = 1.5F;
    /*     */
    /*     */   static ImageView createSettingsButton(Context context)
    /*     */
    {
        /*  33 */     BitmapDrawable settingsIcon = decodeBitmapFromString(context,
                "iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAQAAAD/5HvMAAADEklEQVRoBe3BXWjVZQDH8d/0HKW00OZ0kh6XiKUiIl0okhARdBcEjUQSmViYkF14K+TCnTNDEd9ShMGgFGZC9HaZqElo0aZDkhAkt2b5np7j8e3P+XazwWE8/+floA9enM9Hqqure0oxn0HSDPCyYqMDm82KjcPYHFRs9GHzq2KjiM1NxUUzLo2KieW4LFVMrMFllWIij0u7Hi/GcIRr7GexRmE8H/E3LgOsY7xG4VUOcJVDNCgUaxnxG2uZoGGsYABfl3hPw5jIh/zOiNUKw/NcodptvmARzfxIqO+ZymL2c4dql5moEGzDpEwtypgU5I+5PORJu89s+eIHYvhGfniLWN6QGxn+IJZ+xsqFT4hpveyYwi1ius5k2bCPUBV66SJPni76qBBql9LxIgkhinQwU1XIUaBEiEc0KQ1TKePvKDNkQI7j+CsxSelYxhB+DpFRCrL04GeQJbJjGsdwO0pGFmQ5gdtPNMmNDNuwKzJDDuQoYdfJWPniXYqk65AHOkl3m3cUhnmcx6zCTHlgFhXMzjFX4ViJWa88cRazVtWCjZh1yRPdmG1QLfgMs7w8UcBsk2pBO2Z5eaKA2SbVgo2YdckT3ZhtUC1YiVmfPNGPWavCsYA/MauQkwdaqGB2nnkKwwpKpCvIA1tJV6RVvsiyE7sSOTnQQhm77WTkxnR+xu04WVkwjpO4HWOa7FjOP/jpIasUjONr/AyxTOlo4h7+TpCTAS2cxN9dJisN00kIUaKTWapCC1spE+IhU5SOPYSqcJZuChTopp8KoXbIhhe4QUzXmCQ7PiamdXIhwzliOcMYufEmsbwuP3xLDEfkizk84Em7x0vyx+eYlKhFEZMOheA5/qXaLXaygCa+I9RhGlnIbv6j2hATFIY1jPiF1TyjYbRyEV8XeFvDeJY2TjFilULRwEEG2c1CjUKWDxjA5S/ayGgUFrGXy3xJgx4v8ri0KybacHlfMfEaLksUE824NCou7mBzU7HRi81pxUYPNl8pNrZg86li4xUukpCQkJCQkJCQkJDwiAvMUV1d3VPqfz17MXquI1uXAAAAAElFTkSuQmCC");
        /*  34 */     return createButton(context, settingsIcon);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   static ImageView createBackButton(Context context)
    /*     */
    {
        /*  44 */     BitmapDrawable backIcon = decodeBitmapFromString(context,
                                                "iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAQAAABIkb+zAAAArklEQVR42u3VyRHCMBBFwQkAcoQ8WJQbEJewby4XRxkzQ3cE/1VpiQAAANhEb73lnj9ruednTVjMz5iwmp8t4cP82Tn3/Ec/mG+++eabb7755ptvvvnmm2+++eYPmv8FyecPCdhz/oCAfecLKHCEClziAs9ogY9MggQJ/5DwlCBBQp2EowQJEmokvCRIkCDhZxJOEZkTLpHLKuEa+SwSMs5fJNwirynhHgAAAJt4A/ZvpX5veSF2AAAAAElFTkSuQmCC");

        /*     */
        /*     */
        /*     */
        /*     */

        /*  49 */     if ((Build.VERSION.SDK_INT >= 17) &&
                          /*  50 */ (context.getResources().getConfiguration().getLayoutDirection() == 1))
            /*     */     {
            /*  52 */       if (Build.VERSION.SDK_INT >= 19) {
                /*  53 */         backIcon.setAutoMirrored(true);
                /*     */
            }
            /*     */       else {
                /*  56 */         backIcon = reverseBitmap(backIcon.getBitmap());
                /*     */
            }

            /*     */
        }

        /*  59 */     return createButton(context, backIcon);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   static DisplayMetrics getDisplayMetrics(Context context)
    /*     */
    {
        /*  66 */     WindowManager windowManager = (WindowManager)
                context.getSystemService("window");
        /*  67 */     Display display = windowManager.getDefaultDisplay();
        /*  68 */     DisplayMetrics metrics = new DisplayMetrics();

        /*     */

        /*  70 */     if (Build.VERSION.SDK_INT >= 17) {
            /*  71 */       display.getRealMetrics(metrics);
            /*     */
        } else {
            /*  73 */       display.getMetrics(metrics);
            /*     */
        }

        /*  75 */     return metrics;
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   private static BitmapDrawable reverseBitmap(Bitmap bitmap)
    /*     */
    {
        /*  82 */     Matrix matrix = new Matrix();
        /*  83 */     matrix.preScale(-1.0F, 1.0F);
        /*     */
        /*  85 */     Bitmap reversedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                              bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        /*  86 */     reversedBitmap.setDensity(160);
        /*  87 */     return new BitmapDrawable(reversedBitmap);
        /*     */
    }
    /*     */
    /*     */   private static BitmapDrawable decodeBitmapFromString(
        Context context, String bitmapString)
    {
        /*  91 */     byte[] decodedBytes = Base64.decode(bitmapString, 0);
        /*  92 */     Bitmap buttonBitmap = BitmapFactory.decodeByteArray(decodedBytes,
                                            0, decodedBytes.length);
        /*  93 */     return new BitmapDrawable(context.getResources(), buttonBitmap);
        /*     */
    }
    /*     */
    /*     */   private static ImageView createButton(Context context,
            BitmapDrawable icon)
    {
        /*  97 */     float density = getDisplayMetrics(context).density;
        /*  98 */     int iconWidthPx = (int)(28.0F * density);
        /*     */
        /* 100 */     int buttonWidthPx = (int)(iconWidthPx * 1.5F);
        /* 101 */     int padding = (buttonWidthPx - iconWidthPx) / 2;
        /*     */
        /* 103 */     ImageView button = new ImageView(context);
        /* 104 */     button.setPadding(padding, padding, padding, padding);
        /* 105 */     button.setImageDrawable(icon);
        /* 106 */     button.setScaleType(ImageView.ScaleType.FIT_CENTER);
        /* 107 */     ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
            buttonWidthPx, buttonWidthPx);
        /* 108 */     button.setLayoutParams(layoutParams);
        /* 109 */     return button;
        /*     */
    }
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/UiLayerUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */