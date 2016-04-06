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

public class CardboardView extends GLSurfaceView
{
    private CardboardViewApi cardboardViewApi;
    private boolean rendererIsSet = false;

    public CardboardView(Context context)
    {
        super(context);
        this.init(context);
    }

    public CardboardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.init(context);
    }

    public void setRenderer(CardboardView.Renderer renderer)
    {
        GLSurfaceView.Renderer glRenderer = this.cardboardViewApi.setRenderer(renderer);

        if (glRenderer != null) {
            super.setRenderer(glRenderer);
            this.rendererIsSet = true;
        }
    }

    public void setRenderer(CardboardView.StereoRenderer renderer)
    {
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
        this.cardboardViewApi.getCurrentEyeParams(head, leftEye, rightEye, monocular,
                leftEyeNoDistortionCorrection, rightEyeNoDistortionCorrection);
    }

    public void setVRModeEnabled(boolean enabled)
    {
        this.cardboardViewApi.setVRModeEnabled(enabled);
    }

    public boolean getVRMode()
    {
        return this.cardboardViewApi.getVRMode();
    }

    public void setAlignmentMarkerEnabled(boolean enabled)
    {
        this.cardboardViewApi.setAlignmentMarkerEnabled(enabled);
    }

    public boolean getAlignmentMarkerEnabled()
    {
        return this.cardboardViewApi.getAlignmentMarkerEnabled();
    }

    public void setSettingsButtonEnabled(boolean enabled)
    {
        this.cardboardViewApi.setSettingsButtonEnabled(enabled);
    }

    public boolean getSettingsButtonEnabled()
    {
        return this.cardboardViewApi.getSettingsButtonEnabled();
    }

    public HeadMountedDisplay getHeadMountedDisplay()
    {
        return this.cardboardViewApi.getHeadMountedDisplay();
    }

    public void setRestoreGLStateEnabled(boolean enabled)
    {
        this.cardboardViewApi.setRestoreGLStateEnabled(enabled);
    }

    public boolean getRestoreGLStateEnabled()
    {
        return this.cardboardViewApi.getRestoreGLStateEnabled();
    }

    public void setChromaticAberrationCorrectionEnabled(boolean enabled)
    {
        this.cardboardViewApi.setChromaticAberrationCorrectionEnabled(enabled);
    }

    public boolean getChromaticAberrationCorrectionEnabled()
    {
        return this.cardboardViewApi.getChromaticAberrationCorrectionEnabled();
    }

    public void setVignetteEnabled(boolean enabled)
    {
        this.cardboardViewApi.setVignetteEnabled(enabled);
    }

    public boolean getVignetteEnabled()
    {
        return this.cardboardViewApi.getVignetteEnabled();
    }

    public void setNeckModelEnabled(boolean enabled)
    {
        this.cardboardViewApi.setNeckModelEnabled(enabled);
    }

    public float getNeckModelFactor()
    {
        return this.cardboardViewApi.getNeckModelFactor();
    }

    public void setNeckModelFactor(float factor)
    {
        this.cardboardViewApi.setNeckModelFactor(factor);
    }

    public void setGyroBiasEstimationEnabled(boolean enabled)
    {
        this.cardboardViewApi.setGyroBiasEstimationEnabled(enabled);
    }

    public boolean getGyroBiasEstimationEnabled()
    {
        return this.cardboardViewApi.getGyroBiasEstimationEnabled();
    }

    public void resetHeadTracker()
    {
        this.cardboardViewApi.resetHeadTracker();
    }

    public void updateCardboardDeviceParams(CardboardDeviceParams
                                            cardboardDeviceParams)
    {
        this.cardboardViewApi.updateCardboardDeviceParams(cardboardDeviceParams);
    }

    public CardboardDeviceParams getCardboardDeviceParams()
    {
        return this.cardboardViewApi.getCardboardDeviceParams();
    }

    public void updateScreenParams(ScreenParams screenParams)
    {
        this.cardboardViewApi.updateScreenParams(screenParams);
    }

    public ScreenParams getScreenParams()
    {
        return this.cardboardViewApi.getScreenParams();
    }

    public float getInterpupillaryDistance()
    {
        return this.cardboardViewApi.getInterpupillaryDistance();
    }

    public void setDistortionCorrectionEnabled(boolean enabled)
    {
        this.cardboardViewApi.setDistortionCorrectionEnabled(enabled);
    }

    public boolean getDistortionCorrectionEnabled()
    {
        return this.cardboardViewApi.getDistortionCorrectionEnabled();
    }

    public void undistortTexture(int inputTexture)
    {
        this.cardboardViewApi.undistortTexture(inputTexture);
    }

    public void renderUiLayer()
    {
        this.cardboardViewApi.renderUiLayer();
    }

    public void setDistortionCorrectionScale(float scale)
    {
        this.cardboardViewApi.setDistortionCorrectionScale(scale);
    }

    public void onResume()
    {
        if (this.rendererIsSet) {
            super.onResume();
        }

        this.cardboardViewApi.onResume();
    }

    public void onPause()
    {
        this.cardboardViewApi.onPause();

        if (this.rendererIsSet) {
            super.onPause();
        }

    }

    public void queueEvent(Runnable r)
    {
        if (!this.rendererIsSet) {
            r.run();
        } else {
            super.queueEvent(r);
        }
    }

    public void setRenderer(GLSurfaceView.Renderer renderer)
    {
        throw new RuntimeException("Please use the CardboardView renderer interfaces");
    }

    public void onDetachedFromWindow()
    {
        if (this.rendererIsSet) {
            this.cardboardViewApi.onDetachedFromWindow();
        }

        Log.e(this.getClass().getSimpleName(), "onDetachedFromWindow");
        super.onDetachedFromWindow();

    }

    public boolean onTouchEvent(MotionEvent e)
    {
        return this.cardboardViewApi.onTouchEvent(e) ? true : super.onTouchEvent(e);
    }

    public void setOnCardboardTriggerListener(Runnable listener)
    {
        this.cardboardViewApi.setOnCardboardTriggerListener(listener);
    }

    void setConvertTapIntoTrigger(boolean enable)
    {
        this.cardboardViewApi.setConvertTapIntoTrigger(enable);
    }

    boolean getConvertTapIntoTrigger()
    {
        return this.cardboardViewApi.getConvertTapIntoTrigger();
    }

    boolean handlesMagnetInput()
    {
        return this.cardboardViewApi.handlesMagnetInput();
    }

    private void init(Context context)
    {
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
