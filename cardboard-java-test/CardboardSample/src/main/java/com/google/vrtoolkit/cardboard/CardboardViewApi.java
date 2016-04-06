//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

import com.google.vrtoolkit.cardboard.CardboardView.StereoRenderer;

public interface CardboardViewApi {
    Renderer setRenderer(com.google.vrtoolkit.cardboard.CardboardView.Renderer var1);

    Renderer setRenderer(StereoRenderer var1);

    void getCurrentEyeParams(HeadTransform var1, Eye var2, Eye var3, Eye var4, Eye var5, Eye var6);

    void setVRModeEnabled(boolean var1);

    boolean getVRMode();

    void setAlignmentMarkerEnabled(boolean var1);

    boolean getAlignmentMarkerEnabled();

    void setSettingsButtonEnabled(boolean var1);

    boolean getSettingsButtonEnabled();

    HeadMountedDisplay getHeadMountedDisplay();

    void setRestoreGLStateEnabled(boolean var1);

    boolean getRestoreGLStateEnabled();

    void setChromaticAberrationCorrectionEnabled(boolean var1);

    boolean getChromaticAberrationCorrectionEnabled();

    void setVignetteEnabled(boolean var1);

    boolean getVignetteEnabled();

    void setElectronicDisplayStabilizationEnabled(boolean var1);

    boolean getElectronicDisplayStabilizationEnabled();

    void setNeckModelEnabled(boolean var1);

    float getNeckModelFactor();

    void setNeckModelFactor(float var1);

    void setGyroBiasEstimationEnabled(boolean var1);

    boolean getGyroBiasEstimationEnabled();

    void resetHeadTracker();

    void updateCardboardDeviceParams(CardboardDeviceParams var1);

    CardboardDeviceParams getCardboardDeviceParams();

    void updateScreenParams(ScreenParams var1);

    ScreenParams getScreenParams();

    float getInterpupillaryDistance();

    void setDistortionCorrectionEnabled(boolean var1);

    boolean getDistortionCorrectionEnabled();

    void setLowLatencyModeEnabled(boolean var1);

    boolean getLowLatencyModeEnabled();

    void undistortTexture(int var1);

    void renderUiLayer();

    void setDistortionCorrectionScale(float var1);

    void onResume();

    void onPause();

    void onDetachedFromWindow();

    boolean onTouchEvent(MotionEvent var1);

    void setOnCardboardTriggerListener(Runnable var1);

    void runOnCardboardTriggerListener();

    void setConvertTapIntoTrigger(boolean var1);

    boolean getConvertTapIntoTrigger();

    boolean handlesMagnetInput();
}
