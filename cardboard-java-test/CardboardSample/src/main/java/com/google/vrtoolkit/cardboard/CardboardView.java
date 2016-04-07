//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import android.util.Log;
import android.util.Log;
import android.util.Log;

public class CardboardView extends GLSurfaceView
{
    private CardboardViewApi cardboardViewApi;
    private boolean rendererIsSet = false;

    public CardboardView(Context context)
    {

        super(context);
        Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO [" + Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + Thread.currentThread().getStackTrace()[2].getFileName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");

        this.init(context);
    }

    public CardboardView(Context context, AttributeSet attrs)
    {

        super(context, attrs);
        Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO [" + Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + Thread.currentThread().getStackTrace()[2].getFileName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]");

        this.init(context);
    }

    public void setRenderer(CardboardView.Renderer renderer)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        GLSurfaceView.Renderer glRenderer = this.cardboardViewApi.setRenderer(renderer);

        if (glRenderer != null) {
            super.setRenderer(glRenderer);
            this.rendererIsSet = true;
        }
    }

    public void setRenderer(CardboardView.StereoRenderer renderer)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        GLSurfaceView.Renderer glRenderer = this.cardboardViewApi.setRenderer(renderer);

        if (glRenderer != null) {
            super.setRenderer(glRenderer);
            this.rendererIsSet = true;
        }
    }

    public void getCurrentEyeParams(HeadTransform head, Eye leftEye, Eye rightEye,
                                    Eye monocular, Eye leftEyeNoDistortionCorrection,
                                    Eye rightEyeNoDistortionCorrection)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.getCurrentEyeParams(head, leftEye, rightEye, monocular,
                leftEyeNoDistortionCorrection, rightEyeNoDistortionCorrection);
    }

    public void setVRModeEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setVRModeEnabled(enabled);
    }

    public boolean getVRMode()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getVRMode();
    }

    public void setAlignmentMarkerEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setAlignmentMarkerEnabled(enabled);
    }

    public boolean getAlignmentMarkerEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getAlignmentMarkerEnabled();
    }

    public void setSettingsButtonEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setSettingsButtonEnabled(enabled);
    }

    public boolean getSettingsButtonEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getSettingsButtonEnabled();
    }

    public HeadMountedDisplay getHeadMountedDisplay()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getHeadMountedDisplay();
    }

    public void setRestoreGLStateEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setRestoreGLStateEnabled(enabled);
    }

    public boolean getRestoreGLStateEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getRestoreGLStateEnabled();
    }

    public void setChromaticAberrationCorrectionEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setChromaticAberrationCorrectionEnabled(enabled);
    }

    public boolean getChromaticAberrationCorrectionEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getChromaticAberrationCorrectionEnabled();
    }

    public void setVignetteEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setVignetteEnabled(enabled);
    }

    public boolean getVignetteEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getVignetteEnabled();
    }

    public void setNeckModelEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setNeckModelEnabled(enabled);
    }

    public float getNeckModelFactor()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getNeckModelFactor();
    }

    public void setNeckModelFactor(float factor)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setNeckModelFactor(factor);
    }

    public void setGyroBiasEstimationEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setGyroBiasEstimationEnabled(enabled);
    }

    public boolean getGyroBiasEstimationEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getGyroBiasEstimationEnabled();
    }

    public void resetHeadTracker()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.resetHeadTracker();
    }

    public void updateCardboardDeviceParams(CardboardDeviceParams
                                            cardboardDeviceParams)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.updateCardboardDeviceParams(cardboardDeviceParams);
    }

    public CardboardDeviceParams getCardboardDeviceParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getCardboardDeviceParams();
    }

    public void updateScreenParams(ScreenParams screenParams)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.updateScreenParams(screenParams);
    }

    public ScreenParams getScreenParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getScreenParams();
    }

    public float getInterpupillaryDistance()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getInterpupillaryDistance();
    }

    public void setDistortionCorrectionEnabled(boolean enabled)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setDistortionCorrectionEnabled(enabled);
    }

    public boolean getDistortionCorrectionEnabled()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getDistortionCorrectionEnabled();
    }

    public void undistortTexture(int inputTexture)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.undistortTexture(inputTexture);
    }

    public void renderUiLayer()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.renderUiLayer();
    }

    public void setDistortionCorrectionScale(float scale)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setDistortionCorrectionScale(scale);
    }

    public void onResume()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (this.rendererIsSet) {
            super.onResume();
        }

        this.cardboardViewApi.onResume();
    }

    public void onPause()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.onPause();

        if (this.rendererIsSet) {
            super.onPause();
        }

    }

    public void queueEvent(Runnable r)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (!this.rendererIsSet) {
            r.run();
        } else {
            super.queueEvent(r);
        }
    }

    public void setRenderer(GLSurfaceView.Renderer renderer)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        throw new RuntimeException("Please use the CardboardView renderer interfaces");
    }

    public void onDetachedFromWindow()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (this.rendererIsSet) {
            this.cardboardViewApi.onDetachedFromWindow();
        }

        Log.e(this.getClass().getSimpleName(), "onDetachedFromWindow");
        super.onDetachedFromWindow();

    }

    public boolean onTouchEvent(MotionEvent e)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.onTouchEvent(e) ? true : super.onTouchEvent(e);
    }

    public void setOnCardboardTriggerListener(Runnable listener)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setOnCardboardTriggerListener(listener);
    }

    void setConvertTapIntoTrigger(boolean enable)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi.setConvertTapIntoTrigger(enable);
    }

    boolean getConvertTapIntoTrigger()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.getConvertTapIntoTrigger();
    }

    boolean handlesMagnetInput()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.cardboardViewApi.handlesMagnetInput();
    }

    private void init(Context context)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.cardboardViewApi = ImplementationSelector.createCardboardViewApi(context,
                                this);

        if (VERSION.SDK_INT < 19) {
            this.setOnSystemUiVisibilityChangeListener(new
            OnSystemUiVisibilityChangeListener() {
                public void onSystemUiVisibilityChange(int visibility) {
                    if (CardboardView.this.getConvertTapIntoTrigger() && (visibility & 2) == 0) {
                        CardboardView.this.cardboardViewApi.runOnCardboardTriggerListener();
                    }

                }
            });
        }

        this.setEGLContextClientVersion(2);
        this.setPreserveEGLContextOnPause(true);
    }

    public interface StereoRenderer {
        @UsedByNative
        void onNewFrame(HeadTransform var1);

        @UsedByNative
        void onDrawEye(Eye var1);

        @UsedByNative
        void onFinishFrame(Viewport var1);

        void onSurfaceChanged(int var1, int var2);

        void onSurfaceCreated(EGLConfig var1);

        void onRendererShutdown();
    }

    public interface Renderer {
        @UsedByNative
        void onDrawFrame(HeadTransform var1, Eye var2, Eye var3);

        @UsedByNative
        void onFinishFrame(Viewport var1);

        void onSurfaceChanged(int var1, int var2);

        void onSurfaceCreated(EGLConfig var1);

        void onRendererShutdown();
    }
}
