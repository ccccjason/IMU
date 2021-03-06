/*     */ package com.google.vrtoolkit.cardboard;
/*     */
/*     */ import android.app.Activity;
/*     */ import android.nfc.NdefMessage;
/*     */ import android.os.Bundle;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import com.google.vrtoolkit.cardboard.sensors.NfcSensor;
/*     */ import com.google.vrtoolkit.cardboard.sensors.SensorConnection;
/*     */ import
com.google.vrtoolkit.cardboard.sensors.SensorConnection.SensorListener;
import android.util.Log;
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
/*     */ public class CardboardActivity
/*     */   extends Activity
/*     */   implements SensorConnection.SensorListener, VolumeKeyState.Handler
/*     */
{
    /*  39 */   private final SensorConnection sensorConnection = new
    SensorConnection(this);
    /*  40 */   private final VolumeKeyState volumeKeyState = new VolumeKeyState(
        this);
    /*  41 */   private final FullscreenMode fullscreenMode = new FullscreenMode(
        this);
    /*     */   private CardboardView cardboardView;
    /*  43 */   private boolean convertTapIntoTriggerEnabled = true;
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
    /*     */   public void setCardboardView(CardboardView cardboardView)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /*  59 */     if (this.cardboardView == cardboardView) {
            /*  60 */       return;
            /*     */
        }

        /*     */

        /*  63 */     if (this.cardboardView != null) {
            /*  64 */       this.cardboardView.setOnCardboardTriggerListener(null);
            /*     */
        }

        /*     */
        /*  67 */     this.cardboardView = cardboardView;

        /*     */

        /*  69 */     if (cardboardView == null) {
            /*  70 */       return;
            /*     */
        }

        /*     */
        /*  73 */     cardboardView.setOnCardboardTriggerListener(new Runnable()
        /*     */     {
            /*     */       public void run() {
                /*  76 */         CardboardActivity.this.onCardboardTrigger();
                /*     */
            }
            /*     */
            /*     */
            /*  80 */
        });
        /*  81 */     NdefMessage tagContents =
            this.sensorConnection.getNfcSensor().getTagContents();

        /*  82 */     if (tagContents != null) {
            /*  83 */       updateCardboardDeviceParams(
                CardboardDeviceParams.createFromNfcContents(tagContents));
            /*     */
        }

        /*     */

        /*  86 */     if (cardboardView.handlesMagnetInput())
            /*     */     {
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*  94 */       this.sensorConnection.disableMagnetSensor();
            /*     */
        }

        /*     */
        /*  97 */     cardboardView.setConvertTapIntoTrigger(
            this.convertTapIntoTriggerEnabled);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public CardboardView getCardboardView()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 107 */     return this.cardboardView;
        /*     */
    }
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
    /*     */   public NfcSensor getNfcSensor()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 121 */     return this.sensorConnection.getNfcSensor();
        /*     */
    }
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
    /*     */   public void setVolumeKeysMode(int mode)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 135 */     this.volumeKeyState.setVolumeKeysMode(mode);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public int getVolumeKeysMode()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 146 */     return this.volumeKeyState.getVolumeKeysMode();
        /*     */
    }
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
    /*     */   public boolean areVolumeKeysDisabled()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 161 */     return this.volumeKeyState.areVolumeKeysDisabled(
                                 this.sensorConnection.getNfcSensor());
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void onInsertedIntoCardboard(CardboardDeviceParams
            cardboardDeviceParams)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 173 */     updateCardboardDeviceParams(cardboardDeviceParams);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void onRemovedFromCardboard() {}
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public void onCardboardTrigger() {}
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   protected void updateCardboardDeviceParams(CardboardDeviceParams
            newParams)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 204 */     if (this.cardboardView != null) {
            /* 205 */       this.cardboardView.updateCardboardDeviceParams(newParams);
            /*     */
        }

        /*     */
    }
    /*     */
    /*     */
    /*     */   protected void onCreate(Bundle savedInstanceState)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 212 */     super.onCreate(savedInstanceState);
        /*     */
        /* 214 */     requestWindowFeature(1);
        /* 215 */     this.fullscreenMode.startFullscreenMode();
        /* 216 */     this.sensorConnection.onCreate(this);
        /* 217 */     this.volumeKeyState.onCreate();
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public synchronized void setConvertTapIntoTrigger(boolean enabled)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 229 */     this.convertTapIntoTriggerEnabled = enabled;

        /* 230 */     if (this.cardboardView != null) {
            /* 231 */       this.cardboardView.setConvertTapIntoTrigger(enabled);
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
    /*     */   public synchronized boolean getConvertTapIntoTrigger()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 242 */     return this.cardboardView != null ?
                             this.cardboardView.getConvertTapIntoTrigger() :
                             this.convertTapIntoTriggerEnabled;
        /*     */
    }
    /*     */
    /*     */
    /*     */   protected void onResume()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 248 */     super.onResume();

        /*     */

        /* 250 */     if (this.cardboardView != null) {
            /* 251 */       this.cardboardView.onResume();
            /*     */
        }

        /*     */
        /* 254 */     this.sensorConnection.onResume(this);
        /* 255 */     this.fullscreenMode.setFullscreenMode();
        /*     */
    }
    /*     */
    /*     */
    /*     */   protected void onPause()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 261 */     super.onPause();

        /* 262 */     if (this.cardboardView != null) {
            /* 263 */       this.cardboardView.onPause();
            /*     */
        }

        /*     */
        /* 266 */     this.sensorConnection.onPause(this);
        /*     */
    }
    /*     */
    /*     */
    /*     */   protected void onDestroy()
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 272 */     this.sensorConnection.onDestroy(this);
        /*     */
        /* 274 */     super.onDestroy();
        /*     */
    }
    /*     */
    /*     */
    /*     */   public void setContentView(View view)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 280 */     if ((view instanceof CardboardView)) {
            /* 281 */       setCardboardView((CardboardView)view);
            /*     */
        }

        /*     */
        /* 284 */     super.setContentView(view);
        /*     */
    }
    /*     */
    /*     */
    /*     */   public void setContentView(View view, ViewGroup.LayoutParams params)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 290 */     if ((view instanceof CardboardView)) {
            /* 291 */       setCardboardView((CardboardView)view);
            /*     */
        }

        /*     */
        /* 294 */     super.setContentView(view, params);
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean onKeyDown(int keyCode, KeyEvent event)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 301 */     return (this.volumeKeyState.onKey(keyCode)) ||
                             (super.onKeyDown(keyCode, event));
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public boolean onKeyUp(int keyCode, KeyEvent event)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 308 */     return (this.volumeKeyState.onKey(keyCode)) ||
                             (super.onKeyUp(keyCode, event));
        /*     */
    }
    /*     */
    /*     */
    /*     */   public void onWindowFocusChanged(boolean hasFocus)
    /*     */
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        /* 314 */     super.onWindowFocusChanged(hasFocus);
        /* 315 */     this.fullscreenMode.onWindowFocusChanged(hasFocus);
        /*     */
    }
    /*     */
}
