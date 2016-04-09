/*    */ package com.google.vr.cardboard;
/*    */
/*    */ import android.os.Looper;
/*    */ import android.view.Choreographer;
/*    */ import android.view.Choreographer.FrameCallback;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class FrameMonitor
/*    */   implements Choreographer.FrameCallback
/*    */
{
    /*    */   private final Choreographer choreographer;
    /*    */   private final Choreographer.FrameCallback callback;
    /*    */   private boolean isPaused;
    /*    */
    /*    */   public FrameMonitor(Choreographer.FrameCallback callback)
    /*    */
    {
        /* 22 */     this(Choreographer.getInstance(), callback);
        /*    */
    }
    /*    */
    /*    */
    /*    */
    /*    */   public FrameMonitor(Choreographer choreographer,
                                   Choreographer.FrameCallback callback)
    /*    */
    {
        /* 29 */     assert(Looper.getMainLooper() == Looper.myLooper());
        /*    */
        /* 31 */     this.callback = callback;
        /* 32 */     this.choreographer = choreographer;
        /* 33 */     choreographer.postFrameCallback(this);
        /*    */
    }
    /*    */
    /*    */
    /*    */
    /*    */   public void onPause()
    /*    */
    {
        /* 40 */     if (this.isPaused) {
            /* 41 */       return;
            /*    */
        }

        /* 43 */     this.choreographer.removeFrameCallback(this);
        /* 44 */     this.isPaused = true;
        /*    */
    }
    /*    */
    /*    */
    /*    */
    /*    */   public void onResume()
    /*    */
    {
        /* 51 */     if (!this.isPaused) {
            /* 52 */       return;
            /*    */
        }

        /* 54 */     this.isPaused = false;
        /* 55 */     this.choreographer.postFrameCallback(this);
        /*    */
    }
    /*    */
    /*    */   public void doFrame(long vsync)
    /*    */
    {
        /* 60 */     assert(!this.isPaused);
        /* 61 */     this.choreographer.postFrameCallback(this);
        /* 62 */     this.callback.doFrame(vsync);
        /*    */
    }
    /*    */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/FrameMonitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */