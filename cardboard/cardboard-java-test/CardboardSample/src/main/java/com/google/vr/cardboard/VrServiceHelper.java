/*    */ package com.google.vr.cardboard;
/*    */
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.os.Handler;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class VrServiceHelper
/*    */
{
    /*    */   public static final String COMMAND = "command";
    /*    */   public static final String ACTION = "com.google.vr.VRSERVICE";
    /*    */   public static final String MSG_NONE = "none";
    /*    */   public static final String MSG_NOTIFICATION_POSTED =
        "notificationPosted";
    /*    */   public static final String MSG_REQUEST_SERVICE = "requestService";
    /*    */   public static final String MSG_STOP_SERVICE = "stopService";
    /*    */   public static final long KEEPALIVE_GAP_MS = 1000L;
    /*    */   private final Context context;
    /* 30 */   private final Handler handler = new Handler();
    /*    */
    /*    */
    /* 33 */   private final Runnable keepAliveRunnable = new Runnable()
    /*    */
    {
        /*    */     public void run() {
            /* 36 */       Intent intent = new Intent("com.google.vr.VRSERVICE");
            /* 37 */       intent.putExtra("command", "requestService");
            /* 38 */       VrServiceHelper.this.context.sendBroadcast(intent);
            /* 39 */       VrServiceHelper.this.handler.postDelayed(
                VrServiceHelper.this.keepAliveRunnable, 1000L);
            /*    */
        }
        /*    */
    };
    /*    */
    /*    */   public VrServiceHelper(Context context)
    {
        /* 44 */     this.context = context;
        /*    */
    }
    /*    */
    /*    */   public void connectVrService()
    /*    */
    {
        /* 49 */     this.handler.removeCallbacks(this.keepAliveRunnable);
        /* 50 */     this.handler.post(this.keepAliveRunnable);
        /*    */
    }
    /*    */
    /*    */   public void disconnectVrService()
    /*    */
    {
        /* 55 */     this.handler.removeCallbacks(this.keepAliveRunnable);
        /* 56 */     Intent intent = new Intent("com.google.vr.VRSERVICE");
        /* 57 */     intent.putExtra("command", "stopService");
        /* 58 */     this.context.sendBroadcast(intent);
        /*    */
    }
    /*    */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/VrServiceHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */