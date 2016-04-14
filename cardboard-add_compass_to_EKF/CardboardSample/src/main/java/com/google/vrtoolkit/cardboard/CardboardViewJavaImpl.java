//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import com.google.vrtoolkit.cardboard.CardboardView.StereoRenderer;
import com.google.vrtoolkit.cardboard.sensors.HeadTracker;

import java.util.concurrent.CountDownLatch;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CardboardViewJavaImpl implements CardboardViewApi
{
    private static final String TAG = CardboardViewJavaImpl.class.getSimpleName();
    private CardboardViewJavaImpl.RendererHelper rendererHelper;
    private HeadTracker headTracker;
    private HeadMountedDisplayManager hmdManager;
    private UiLayer uiLayer;
    private CountDownLatch shutdownLatch;
    private final GLSurfaceView glSurfaceView;
    private boolean convertTapIntoTrigger = true;
    private Runnable cardboardTriggerListener;
    private volatile boolean vrMode = true;
    private volatile boolean restoreGLStateEnabled = true;
    private volatile boolean distortionCorrectionEnabled = true;
    private volatile boolean chromaticAberrationCorrectionEnabled = false;
    private volatile boolean vignetteEnabled = true;

    public CardboardViewJavaImpl(Context context, GLSurfaceView view)
    {

        this.glSurfaceView = view;
        this.headTracker = HeadTracker.createFromContext(context);
        this.hmdManager = new HeadMountedDisplayManager(context);
        this.rendererHelper = new CardboardViewJavaImpl.RendererHelper();
        this.uiLayer = new UiLayer(context);
    }

    public Renderer setRenderer(
        com.google.vrtoolkit.cardboard.CardboardView.Renderer renderer)
    {

        if (renderer == null) {
            return null;
        } else {
            this.rendererHelper.setRenderer(renderer);
            return this.rendererHelper;
        }
    }

    public Renderer setRenderer(StereoRenderer renderer)
    {

        return this.setRenderer((renderer != null ? new
                                 CardboardViewJavaImpl.StereoRendererHelper(renderer) :
                                 (com.google.vrtoolkit.cardboard.CardboardView.Renderer)null));
    }

    public void getCurrentEyeParams(HeadTransform head, Eye leftEye, Eye rightEye,
                                    Eye monocular, Eye leftEyeNoDistortionCorrection,
                                    Eye rightEyeNoDistortionCorrection)
    {

        this.rendererHelper.getCurrentEyeParams(head, leftEye, rightEye, monocular,
                                                leftEyeNoDistortionCorrection, rightEyeNoDistortionCorrection);
    }

    public void setVRModeEnabled(boolean enabled)
    {

        this.vrMode = enabled;
        this.rendererHelper.setVRModeEnabled(enabled);
    }

    public boolean getVRMode()
    {

        return this.vrMode;
    }

    public void setAlignmentMarkerEnabled(boolean enabled)
    {

        this.uiLayer.setAlignmentMarkerEnabled(enabled);
    }

    public boolean getAlignmentMarkerEnabled()
    {

        return this.uiLayer.getAlignmentMarkerEnabled();
    }

    public void setSettingsButtonEnabled(boolean enabled)
    {

        this.uiLayer.setSettingsButtonEnabled(enabled);
    }

    public boolean getSettingsButtonEnabled()
    {

        return this.uiLayer.getSettingsButtonEnabled();
    }

    public HeadMountedDisplay getHeadMountedDisplay()
    {

        return this.hmdManager.getHeadMountedDisplay();
    }

    public void setRestoreGLStateEnabled(boolean enabled)
    {

        this.restoreGLStateEnabled = enabled;
        this.rendererHelper.setRestoreGLStateEnabled(enabled);
    }

    public boolean getRestoreGLStateEnabled()
    {

        return this.restoreGLStateEnabled;
    }

    public void setChromaticAberrationCorrectionEnabled(boolean enabled)
    {

        this.chromaticAberrationCorrectionEnabled = enabled;
        this.rendererHelper.setChromaticAberrationCorrectionEnabled(enabled);
    }

    public boolean getChromaticAberrationCorrectionEnabled()
    {

        return this.chromaticAberrationCorrectionEnabled;
    }

    public void setVignetteEnabled(boolean enabled)
    {

        this.vignetteEnabled = enabled;
        this.rendererHelper.setVignetteEnabled(enabled);
    }

    public boolean getVignetteEnabled()
    {

        return this.vignetteEnabled;
    }

    public void setElectronicDisplayStabilizationEnabled(boolean enabled)
    {

        if (enabled) {
            throw new UnsupportedOperationException("This is not supported in this version.");
        }
    }

    public boolean getElectronicDisplayStabilizationEnabled()
    {

        return false;
    }

    public void setNeckModelEnabled(boolean enabled)
    {

        this.headTracker.setNeckModelEnabled(enabled);
    }

    public float getNeckModelFactor()
    {

        return this.headTracker.getNeckModelFactor();
    }

    public void setNeckModelFactor(float factor)
    {

        this.headTracker.setNeckModelFactor(factor);
    }

    public void setGyroBiasEstimationEnabled(boolean enabled)
    {

        this.headTracker.setGyroBiasEstimationEnabled(enabled);
    }

    public boolean getGyroBiasEstimationEnabled()
    {

        return this.headTracker.getGyroBiasEstimationEnabled();
    }

    public void resetHeadTracker()
    {

        this.headTracker.resetTracker();
    }

    public void updateCardboardDeviceParams(CardboardDeviceParams
                                            cardboardDeviceParams)
    {

        if (this.hmdManager.updateCardboardDeviceParams(cardboardDeviceParams)) {
            this.rendererHelper.setCardboardDeviceParams(this.getCardboardDeviceParams());
        }

    }

    public CardboardDeviceParams getCardboardDeviceParams()
    {

        return this.hmdManager.getHeadMountedDisplay().getCardboardDeviceParams();
    }

    public void updateScreenParams(ScreenParams screenParams)
    {

        if (this.hmdManager.updateScreenParams(screenParams)) {
            this.rendererHelper.setScreenParams(this.getScreenParams());
        }

    }

    public ScreenParams getScreenParams()
    {

        return this.hmdManager.getHeadMountedDisplay().getScreenParams();
    }

    public float getInterpupillaryDistance()
    {

        return this.getCardboardDeviceParams().getInterLensDistance();
    }

    public void setDistortionCorrectionEnabled(boolean enabled)
    {

        this.distortionCorrectionEnabled = enabled;
        this.rendererHelper.setDistortionCorrectionEnabled(enabled);
    }

    public boolean getDistortionCorrectionEnabled()
    {

        return this.distortionCorrectionEnabled;
    }

    public void setLowLatencyModeEnabled(boolean enabled)
    {

        if (enabled) {
            throw new IllegalArgumentException("Low latency mode is not supported in this build");
        }
    }

    public boolean getLowLatencyModeEnabled()
    {

        return false;
    }

    public void undistortTexture(int inputTexture)
    {

        this.rendererHelper.undistortTexture(inputTexture);
    }

    public void renderUiLayer()
    {

        this.rendererHelper.renderUiLayer();
    }

    public void setDistortionCorrectionScale(float scale)
    {

        this.rendererHelper.setDistortionCorrectionScale(scale);
    }

    public void onResume()
    {

        this.hmdManager.onResume();
        this.rendererHelper.setCardboardDeviceParams(this.getCardboardDeviceParams());
        this.headTracker.startTracking();
    }

    public void onPause()
    {

        this.hmdManager.onPause();
        this.headTracker.stopTracking();
    }

    public void onDetachedFromWindow()
    {

        if (this.shutdownLatch == null) {
            this.shutdownLatch = new CountDownLatch(1);
            this.rendererHelper.shutdown();

            try {
                this.shutdownLatch.await();
            } catch (InterruptedException var2) {
                Log.e(TAG, "Interrupted during shutdown: " + var2.toString());
            }

            this.shutdownLatch = null;
        }

    }

    public void runOnCardboardTriggerListener()
    {

        if (this.cardboardTriggerListener != null) {
            this.cardboardTriggerListener.run();
        }

    }

    public boolean onTouchEvent(MotionEvent e)
    {

        if (this.uiLayer.onTouchEvent(e)) {
            return true;
        } else if (e.getActionMasked() == 0 && this.cardboardTriggerListener != null &&
                   this.convertTapIntoTrigger) {
            this.runOnCardboardTriggerListener();
            return true;
        } else {
            return false;
        }
    }

    public void setOnCardboardTriggerListener(Runnable listener)
    {

        this.cardboardTriggerListener = listener;
    }

    public void setConvertTapIntoTrigger(boolean enabled)
    {

        this.convertTapIntoTrigger = enabled;
    }

    public boolean getConvertTapIntoTrigger()
    {

        return this.convertTapIntoTrigger;
    }

    public boolean handlesMagnetInput()
    {

        return false;
    }

    private void queueEvent(Runnable r)
    {

        this.glSurfaceView.queueEvent(r);
    }

    private class StereoRendererHelper implements
        com.google.vrtoolkit.cardboard.CardboardView.Renderer
    {
        private final StereoRenderer stereoRenderer;
        private boolean vrMode;

        public StereoRendererHelper(StereoRenderer stereoRenderer)
        {

            this.stereoRenderer = stereoRenderer;
            this.vrMode = CardboardViewJavaImpl.this.vrMode;
        }

        public void setVRModeEnabled(boolean enabled)
        {

            this.vrMode = enabled;
        }

        public void onDrawFrame(HeadTransform head, Eye leftEye, Eye rightEye)
        {

            this.stereoRenderer.onNewFrame(head);
            GLES20.glEnable(3089);
            leftEye.getViewport().setGLViewport();
            leftEye.getViewport().setGLScissor();
            this.stereoRenderer.onDrawEye(leftEye);

            if (rightEye != null) {
                rightEye.getViewport().setGLViewport();
                rightEye.getViewport().setGLScissor();
                this.stereoRenderer.onDrawEye(rightEye);
            }
        }

        public void onFinishFrame(Viewport viewport)
        {

            viewport.setGLViewport();
            viewport.setGLScissor();
            this.stereoRenderer.onFinishFrame(viewport);
        }

        public void onSurfaceChanged(int width, int height)
        {

            if (this.vrMode) {
                this.stereoRenderer.onSurfaceChanged(width / 2, height);
            } else {
                this.stereoRenderer.onSurfaceChanged(width, height);
            }

        }

        public void onSurfaceCreated(EGLConfig config)
        {

            this.stereoRenderer.onSurfaceCreated(config);
        }

        public void onRendererShutdown()
        {

            this.stereoRenderer.onRendererShutdown();
        }
    }

    private class RendererHelper implements Renderer
    {
        private final HeadTransform headTransform = new HeadTransform();
        private final Eye monocular = new Eye(0);
        private final Eye leftEye = new Eye(1);
        private final Eye rightEye = new Eye(2);
        private final Eye leftEyeNoDistortion;
        private final Eye rightEyeNoDistortion;
        private final float[] leftEyeTranslate;
        private final float[] rightEyeTranslate;
        private com.google.vrtoolkit.cardboard.CardboardView.Renderer renderer;
        private boolean surfaceCreated;
        private HeadMountedDisplay hmd = new HeadMountedDisplay(
            CardboardViewJavaImpl.this.getHeadMountedDisplay());
        private DistortionRenderer distortionRenderer;
        private boolean vrMode;
        private boolean distortionCorrectionEnabled;
        private boolean projectionChanged;
        private boolean invalidSurfaceSizeWarningShown;

        public RendererHelper()
        {

            this.updateFieldOfView(this.leftEye.getFov(), this.rightEye.getFov());
            this.leftEyeNoDistortion = new Eye(1);
            this.rightEyeNoDistortion = new Eye(2);
            this.distortionRenderer = new DistortionRenderer();
            this.distortionRenderer.setRestoreGLStateEnabled(
                CardboardViewJavaImpl.this.restoreGLStateEnabled);
            this.distortionRenderer.setChromaticAberrationCorrectionEnabled(
                CardboardViewJavaImpl.this.chromaticAberrationCorrectionEnabled);
            this.distortionRenderer.setVignetteEnabled(
                CardboardViewJavaImpl.this.vignetteEnabled);
            this.leftEyeTranslate = new float[16];
            this.rightEyeTranslate = new float[16];
            this.vrMode = CardboardViewJavaImpl.this.vrMode;
            this.distortionCorrectionEnabled =
                CardboardViewJavaImpl.this.distortionCorrectionEnabled;
            this.projectionChanged = true;
        }

        public void setRenderer(com.google.vrtoolkit.cardboard.CardboardView.Renderer
                                renderer)
        {

            this.renderer = renderer;
        }

        public void shutdown()
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    if (RendererHelper.this.renderer != null &&
                        RendererHelper.this.surfaceCreated) {
                        RendererHelper.this.surfaceCreated = false;
                        RendererHelper.this.renderer.onRendererShutdown();
                    }

                    CardboardViewJavaImpl.this.shutdownLatch.countDown();
                }
            });
        }

        public void setCardboardDeviceParams(CardboardDeviceParams newParams)
        {

            final CardboardDeviceParams deviceParams = new CardboardDeviceParams(newParams);
            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.hmd.setCardboardDeviceParams(deviceParams);
                    RendererHelper.this.projectionChanged = true;
                }
            });
        }

        public void setScreenParams(ScreenParams newParams)
        {

            final ScreenParams screenParams = new ScreenParams(newParams);
            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.hmd.setScreenParams(screenParams);
                    RendererHelper.this.projectionChanged = true;
                }
            });
        }

        public void setDistortionCorrectionEnabled(final boolean enabled)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionCorrectionEnabled = enabled;
                    RendererHelper.this.projectionChanged = true;
                }
            });
        }

        public void setDistortionCorrectionScale(final float scale)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionRenderer.setResolutionScale(scale);
                }
            });
        }

        public void setVRModeEnabled(final boolean enabled)
        {

            CardboardViewJavaImpl.this.uiLayer.setEnabled(enabled);
            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    if (RendererHelper.this.vrMode != enabled) {
                        RendererHelper.this.vrMode = enabled;

                        if (RendererHelper.this.renderer instanceof
                            CardboardViewJavaImpl.StereoRendererHelper) {
                            CardboardViewJavaImpl.StereoRendererHelper stereoHelper =
                                (CardboardViewJavaImpl.StereoRendererHelper)RendererHelper.this.renderer;
                            stereoHelper.setVRModeEnabled(enabled);
                        }

                        RendererHelper.this.projectionChanged = true;
                        RendererHelper.this.onSurfaceChanged((GL10)null,
                                                             RendererHelper.this.hmd.getScreenParams().getWidth(),
                                                             RendererHelper.this.hmd.getScreenParams().getHeight());
                    }
                }
            });
        }

        public void setRestoreGLStateEnabled(final boolean enabled)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionRenderer.setRestoreGLStateEnabled(enabled);
                }
            });
        }

        public void setChromaticAberrationCorrectionEnabled(final boolean enabled)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionRenderer.setChromaticAberrationCorrectionEnabled(
                        enabled);
                }
            });
        }

        public void setVignetteEnabled(final boolean enabled)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionRenderer.setVignetteEnabled(enabled);
                    RendererHelper.this.projectionChanged = true;
                }
            });
        }

        public void undistortTexture(final int inputTexture)
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.distortionRenderer.undistortTexture(inputTexture);
                }
            });
        }

        public void renderUiLayer()
        {

            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    CardboardViewJavaImpl.this.uiLayer.draw();
                }
            });
        }

        public void getCurrentEyeParams(final HeadTransform head, final Eye leftEye,
                                        final Eye rightEye, final Eye monocular,
                                        final Eye leftEyeNoDistortionCorrection,
                                        final Eye rightEyeNoDistortionCorrection)
        {

            final CountDownLatch finished = new CountDownLatch(1);
            CardboardViewJavaImpl.this.queueEvent(new Runnable() {
                public void run() {
                    RendererHelper.this.getCurrentEyeParamsFromRenderingThread(head, leftEye,
                            rightEye, monocular, leftEyeNoDistortionCorrection,
                            rightEyeNoDistortionCorrection);
                    finished.countDown();
                }
            });

            try {
                finished.await();
            } catch (InterruptedException var9) {
                Log.e(CardboardViewJavaImpl.TAG,
                      "Interrupted while reading frame params: " + var9.toString());
            }

        }

        private void getCurrentEyeParamsFromRenderingThread(HeadTransform head,
                Eye leftEye, Eye rightEye, Eye monocular, Eye leftEyeNoDistortionCorrection,
                Eye rightEyeNoDistortionCorrection)
        {

            this.getFrameParams(head, leftEye, rightEye, monocular);
            System.arraycopy(leftEye.getEyeView(), 0, this.leftEyeNoDistortion.getEyeView(),
                             0, 16);
            System.arraycopy(rightEye.getEyeView(), 0,
                             this.rightEyeNoDistortion.getEyeView(), 0, 16);

            if (leftEye.getProjectionChanged()) {
                this.getFovAndViewportNoDistortionCorrection(leftEyeNoDistortionCorrection,
                        rightEyeNoDistortionCorrection);
            }

        }

        private void getFrameParams(HeadTransform head, Eye leftEye, Eye rightEye,
                                    Eye monocular)
        {

            CardboardDeviceParams cdp = this.hmd.getCardboardDeviceParams();
            ScreenParams screen = this.hmd.getScreenParams();
            CardboardViewJavaImpl.this.headTracker.getLastHeadView(head.getHeadView(), 0);
            float halfInterpupillaryDistance = cdp.getInterLensDistance() * 0.5F;

            if (this.vrMode) {
                Matrix.setIdentityM(this.leftEyeTranslate, 0);
                Matrix.setIdentityM(this.rightEyeTranslate, 0);
                Matrix.translateM(this.leftEyeTranslate, 0, halfInterpupillaryDistance, 0.0F,
                                  0.0F);
                Matrix.translateM(this.rightEyeTranslate, 0, -halfInterpupillaryDistance, 0.0F,
                                  0.0F);
                Matrix.multiplyMM(leftEye.getEyeView(), 0, this.leftEyeTranslate, 0,
                                  head.getHeadView(), 0);
                Matrix.multiplyMM(rightEye.getEyeView(), 0, this.rightEyeTranslate, 0,
                                  head.getHeadView(), 0);
            } else {
                System.arraycopy(head.getHeadView(), 0, monocular.getEyeView(), 0,
                                 head.getHeadView().length);
            }

            if (this.projectionChanged) {
                int monocularWidth = this.vrMode ? screen.getWidth() :
                                     CardboardViewJavaImpl.this.glSurfaceView.getWidth();
                int monocularHeight = this.vrMode ? screen.getHeight() :
                                      CardboardViewJavaImpl.this.glSurfaceView.getHeight();
                monocular.getViewport().setViewport(0, 0, monocularWidth, monocularHeight);
                CardboardViewJavaImpl.this.uiLayer.updateViewport(monocular.getViewport());

                if (!this.vrMode) {
                    this.updateMonocularFieldOfView(monocular.getFov());
                } else {
                    this.updateFieldOfView(leftEye.getFov(), rightEye.getFov());

                    if (this.distortionCorrectionEnabled) {
                        this.distortionRenderer.onFovChanged(this.hmd, leftEye.getFov(),
                                                             rightEye.getFov(), this.getVirtualEyeToScreenDistance());
                    }
                }

                leftEye.setProjectionChanged();
                rightEye.setProjectionChanged();
                monocular.setProjectionChanged();
                this.projectionChanged = false;
            }

            if (this.distortionCorrectionEnabled &&
                this.distortionRenderer.haveViewportsChanged()) {
                this.distortionRenderer.updateViewports(leftEye.getViewport(),
                                                        rightEye.getViewport());
            }

        }

        public void onDrawFrame(GL10 gl)
        {

            if (this.renderer != null && this.surfaceCreated) {
                this.getCurrentEyeParamsFromRenderingThread(this.headTransform, this.leftEye,
                        this.rightEye, this.monocular, this.leftEyeNoDistortion,
                        this.rightEyeNoDistortion);
                GLES20.glDisable(3089);
                GLES20.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
                GLES20.glClear(16640);

                if (this.vrMode) {
                    if (this.distortionCorrectionEnabled) {
                        this.distortionRenderer.beforeDrawFrame();
                        this.renderer.onDrawFrame(this.headTransform, this.leftEye, this.rightEye);
                        this.distortionRenderer.afterDrawFrame();
                    } else {
                        this.renderer.onDrawFrame(this.headTransform, this.leftEyeNoDistortion,
                                                  this.rightEyeNoDistortion);
                    }
                } else {
                    this.renderer.onDrawFrame(this.headTransform, this.monocular, (Eye)null);
                }

                this.renderer.onFinishFrame(this.monocular.getViewport());
                CardboardViewJavaImpl.this.uiLayer.draw();
            }
        }

        public void onSurfaceChanged(GL10 gl, int width, int height)
        {

            if (this.renderer != null && this.surfaceCreated) {
                ScreenParams screen = this.hmd.getScreenParams();

                if (!this.vrMode || width == screen.getWidth() &&
                    height == screen.getHeight()) {
                    this.invalidSurfaceSizeWarningShown = false;
                } else {
                    if (!this.invalidSurfaceSizeWarningShown) {
                        Log.e(CardboardViewJavaImpl.TAG,
                              "Surface size " + width + "x" + height +
                              " does not match the expected screen size " + screen.getWidth() + "x" +
                              screen.getHeight() + ". Stereo rendering might feel off.");
                    }

                    this.invalidSurfaceSizeWarningShown = true;
                }

                this.projectionChanged = true;
                this.renderer.onSurfaceChanged(width, height);
            }
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config)
        {

            if (this.renderer != null) {
                this.surfaceCreated = true;
                this.renderer.onSurfaceCreated(config);
                CardboardViewJavaImpl.this.uiLayer.initializeGl();
            }
        }

        private void updateFieldOfView(FieldOfView leftEyeFov, FieldOfView rightEyeFov)
        {

            CardboardDeviceParams cdp = this.hmd.getCardboardDeviceParams();
            ScreenParams screen = this.hmd.getScreenParams();
            Distortion distortion = cdp.getDistortion();
            float eyeToScreenDist = this.getVirtualEyeToScreenDistance();
            float outerDist = (screen.getWidthMeters() - cdp.getInterLensDistance()) / 2.0F;
            float innerDist = cdp.getInterLensDistance() / 2.0F;
            float bottomDist = cdp.getYEyeOffsetMeters(screen);
            float topDist = screen.getHeightMeters() - bottomDist;
            float outerAngle = (float)Math.toDegrees(Math.atan((double)distortion.distort(
                                   outerDist / eyeToScreenDist)));
            float innerAngle = (float)Math.toDegrees(Math.atan((double)distortion.distort(
                                   innerDist / eyeToScreenDist)));
            float bottomAngle = (float)Math.toDegrees(Math.atan((double)distortion.distort(
                                    bottomDist / eyeToScreenDist)));
            float topAngle = (float)Math.toDegrees(Math.atan((double)distortion.distort(
                    topDist / eyeToScreenDist)));
            leftEyeFov.setLeft(Math.min(outerAngle, cdp.getLeftEyeMaxFov().getLeft()));
            leftEyeFov.setRight(Math.min(innerAngle, cdp.getLeftEyeMaxFov().getRight()));
            leftEyeFov.setBottom(Math.min(bottomAngle, cdp.getLeftEyeMaxFov().getBottom()));
            leftEyeFov.setTop(Math.min(topAngle, cdp.getLeftEyeMaxFov().getTop()));
            rightEyeFov.setLeft(leftEyeFov.getRight());
            rightEyeFov.setRight(leftEyeFov.getLeft());
            rightEyeFov.setBottom(leftEyeFov.getBottom());
            rightEyeFov.setTop(leftEyeFov.getTop());
        }

        private void updateMonocularFieldOfView(FieldOfView monocularFov)
        {

            float monocularBottomFov = 22.5F;
            float monocularLeftFov = (float)Math.toDegrees(Math.atan(Math.tan(
                                         Math.toRadians((double)monocularBottomFov)) * (double)
                                     CardboardViewJavaImpl.this.glSurfaceView.getWidth() / (double)
                                     CardboardViewJavaImpl.this.glSurfaceView.getHeight()));
            monocularFov.setLeft(monocularLeftFov);
            monocularFov.setRight(monocularLeftFov);
            monocularFov.setBottom(monocularBottomFov);
            monocularFov.setTop(monocularBottomFov);
        }

        private void getFovAndViewportNoDistortionCorrection(Eye leftEye, Eye rightEye)
        {

            ScreenParams screen = this.hmd.getScreenParams();
            CardboardDeviceParams cdp = this.hmd.getCardboardDeviceParams();
            Distortion distortion = cdp.getDistortion();
            float eyeToScreenDistMeters = this.getVirtualEyeToScreenDistance();
            float halfLensDistance = cdp.getInterLensDistance() / 2.0F /
                                     eyeToScreenDistMeters;
            float screenWidth = screen.getWidthMeters() / eyeToScreenDistMeters;
            float screenHeight = screen.getHeightMeters() / eyeToScreenDistMeters;
            float xPxPerTanAngle = (float)screen.getWidth() / screenWidth;
            float yPxPerTanAngle = (float)screen.getHeight() / screenHeight;
            float eyePosX = screenWidth / 2.0F - halfLensDistance;
            float eyePosY = cdp.getYEyeOffsetMeters(screen) / eyeToScreenDistMeters;
            FieldOfView maxFov = cdp.getLeftEyeMaxFov();
            float outerDist = Math.min(eyePosX,
                                       distortion.distortInverse((float)Math.tan(Math.toRadians((
                                                   double)maxFov.getLeft()))));
            float innerDist = Math.min(halfLensDistance,
                                       distortion.distortInverse((float)Math.tan(Math.toRadians((
                                                   double)maxFov.getRight()))));
            float bottomDist = Math.min(eyePosY,
                                        distortion.distortInverse((float)Math.tan(Math.toRadians((
                                                    double)maxFov.getBottom()))));
            float topDist = Math.min(screenHeight - eyePosY,
                                     distortion.distortInverse((float)Math.tan(Math.toRadians((
                                                 double)maxFov.getTop()))));
            FieldOfView leftEyeFov = leftEye.getFov();
            leftEyeFov.setLeft((float)Math.toDegrees(Math.atan((double)outerDist)));
            leftEyeFov.setRight((float)Math.toDegrees(Math.atan((double)innerDist)));
            leftEyeFov.setBottom((float)Math.toDegrees(Math.atan((double)bottomDist)));
            leftEyeFov.setTop((float)Math.toDegrees(Math.atan((double)topDist)));
            Viewport leftViewport = leftEye.getViewport();
            leftViewport.x = (int)((eyePosX - outerDist) * xPxPerTanAngle + 0.5F);
            leftViewport.width = (int)((eyePosX + innerDist) * xPxPerTanAngle + 0.5F) -
                                 leftViewport.x;
            leftViewport.y = (int)((eyePosY - bottomDist) * yPxPerTanAngle + 0.5F);
            leftViewport.height = (int)((eyePosY + topDist) * yPxPerTanAngle + 0.5F) -
                                  leftViewport.y;
            leftEye.setProjectionChanged();
            FieldOfView rightEyeFov = rightEye.getFov();
            rightEyeFov.setLeft(leftEyeFov.getRight());
            rightEyeFov.setRight(leftEyeFov.getLeft());
            rightEyeFov.setBottom(leftEyeFov.getBottom());
            rightEyeFov.setTop(leftEyeFov.getTop());
            Viewport rightViewport = rightEye.getViewport();
            rightViewport.width = leftViewport.width;
            rightViewport.height = leftViewport.height;
            rightViewport.x = screen.getWidth() - leftViewport.x - rightViewport.width;
            rightViewport.y = leftViewport.y;
            rightEye.setProjectionChanged();
        }

        private float getVirtualEyeToScreenDistance()
        {

            return this.hmd.getCardboardDeviceParams().getScreenToLensDistance();
        }
    }
}
