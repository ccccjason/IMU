/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import android.content.Context;
/*     */ import android.view.Choreographer;
/*     */ import android.view.Display;
/*     */ import android.view.WindowManager;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ public class DisplaySynchronizer
/*     */   implements Choreographer.FrameCallback
/*     */
{
    /*     */   private static final float MIN_VALID_DISPLAY_REFRESH_RATE = 30.0F;
    /*     */   private final long nativeDisplaySynchronizer;
    /*     */   private final FrameMonitor frameMonitor;
    /*     */
    /*     */   public DisplaySynchronizer(Context context)
    /*     */
    {
        /*  31 */     this(getDefaultDisplay(context));
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public DisplaySynchronizer(Display display)
    /*     */
    {
        /*  40 */     float refreshRate = display.getRefreshRate();
        /*     */
        /*     */
        /*     */
        /*  44 */     long frameDurationNanos = refreshRate >= 30.0F ? (long)((
                float)TimeUnit.SECONDS.toNanos(1L) / refreshRate) : 0L;
        /*     */
        /*  46 */     this.frameMonitor = new FrameMonitor(this);
        /*     */
        /*     */
        /*     */
        /*  50 */     this.nativeDisplaySynchronizer = nativeInit(frameDurationNanos);
        /*     */
    }
    /*     */
    /*     */   /* Error */
    /*     */   protected void finalize()
    /*     */     throws java.lang.Throwable
    /*     */   {
        /*     */     // Byte code:
        /*     */     //   0: aload_0
        /*     */     //   1: aload_0
        /*     */     //   2: getfield 13   com/google/vr/cardboard/DisplaySynchronizer:nativeDisplaySynchronizer   J
        /*     */     //   5: invokespecial 14  com/google/vr/cardboard/DisplaySynchronizer:nativeDestroy   (J)V
        /*     */     //   8: aload_0
        /*     */     //   9: invokespecial 15  java/lang/Object:finalize   ()V
        /*     */     //   12: goto +10 -> 22
        /*     */     //   15: astore_1
        /*     */     //   16: aload_0
        /*     */     //   17: invokespecial 15 java/lang/Object:finalize   ()V
        /*     */     //   20: aload_1
        /*     */     //   21: athrow
        /*     */     //   22: return
        /*     */     // Line number table:
        /*     */     //   Java source line #56 -> byte code offset #0
        /*     */     //   Java source line #58 -> byte code offset #8
        /*     */     //   Java source line #59 -> byte code offset #12
        /*     */     //   Java source line #58 -> byte code offset #15
        /*     */     //   Java source line #60 -> byte code offset #22
        /*     */     // Local variable table:
        /*     */     //   start    length  slot    name    signature
        /*     */     //   0    23  0   this    DisplaySynchronizer
        /*     */     //   15   6   1   localObject Object
        /*     */     // Exception table:
        /*     */     //   from to  target  type
        /*     */     //   0    8   15  finally
        /*     */
    }
    /*     */
    /*     */   public void onPause()
    /*     */
    {
        /*  66 */     this.frameMonitor.onPause();
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public void onResume()
    /*     */
    {
        /*  73 */     this.frameMonitor.onResume();
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public long retainNativeDisplaySynchronizer()
    /*     */
    {
        /*  83 */     return nativeRetainNativeDisplaySynchronizer(
                                 this.nativeDisplaySynchronizer);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void doFrame(long vsync)
    /*     */
    {
        /*  94 */     nativeAddSyncTime(this.nativeDisplaySynchronizer, vsync);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public long syncToNextVsync()
    /*     */
    {
        /* 104 */     return nativeSyncToNextVsync(this.nativeDisplaySynchronizer);
        /*     */
    }
    /*     */
    /*     */   private static Display getDefaultDisplay(Context context)
    {
        /* 108 */     WindowManager windowManager = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        /* 109 */     return windowManager.getDefaultDisplay();
        /*     */
    }
    /*     */
    /*     */   private native long nativeInit(long paramLong);
    /*     */
    /*     */   private native void nativeDestroy(long paramLong);
    /*     */
    /*     */   private native void nativeAddSyncTime(long paramLong1,
            long paramLong2);
    /*     */
    /*     */   private native long nativeSyncToNextVsync(long paramLong);
    /*     */
    /*     */   private native long nativeRetainNativeDisplaySynchronizer(
        long paramLong);
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/DisplaySynchronizer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */