//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import android.util.Log;
import android.util.Log;

class UiLayer
{
    private static final String TAG = UiLayer.class.getSimpleName();
    private static final int NORMAL_COLOR = -3355444;
    private static final int PRESSED_COLOR = -12303292;
    private static final float CENTER_LINE_THICKNESS_DP = 4.0F;
    private static final int BUTTON_WIDTH_DP = 28;
    private static final float TOUCH_SLOP_FACTOR = 1.5F;
    private final int touchWidthPx;
    private volatile Rect touchRect = new Rect();
    private boolean downWithinBounds;
    private Context context;
    private final GLStateBackup glStateBackup;
    private final UiLayer.ShaderProgram shader;
    private final UiLayer.SettingsButtonRenderer settingsButtonRenderer;
    private final UiLayer.AlignmentMarkerRenderer alignmentMarkerRenderer;
    private Viewport viewport;
    private boolean shouldUpdateViewport = true;
    private boolean settingsButtonEnabled = true;
    private boolean alignmentMarkerEnabled = true;
    private volatile boolean uiLayerEnabled = true;
    private boolean initialized;

    UiLayer(Context context)
    {

        this.context = context;
        float density = context.getResources().getDisplayMetrics().density;
        int buttonWidthPx = (int)(28.0F * density);
        this.touchWidthPx = (int)((float)buttonWidthPx * 1.5F);
        this.glStateBackup = new GLStateBackup();
        this.shader = new UiLayer.ShaderProgram();
        this.settingsButtonRenderer = new UiLayer.SettingsButtonRenderer(this.shader,
                buttonWidthPx);
        this.alignmentMarkerRenderer = new UiLayer.AlignmentMarkerRenderer(this.shader,
                (float)this.touchWidthPx, 4.0F * density);
        this.viewport = new Viewport();
    }

    void setEnabled(boolean enabled)
    {

        this.uiLayerEnabled = enabled;
    }

    void updateViewport(Viewport viewport)
    {

        synchronized(this) {
            if (!this.viewport.equals(viewport)) {
                int w = viewport.width;
                int h = viewport.height;
                this.touchRect = new Rect((w - this.touchWidthPx) / 2, h - this.touchWidthPx,
                                          (w + this.touchWidthPx) / 2, h);
                this.viewport.setViewport(viewport.x, viewport.y, viewport.width,
                                          viewport.height);
                this.shouldUpdateViewport = true;
            }
        }
    }

    void initializeGl()
    {

        this.shader.initializeGl();
        this.glStateBackup.clearTrackedVertexAttributes();
        this.glStateBackup.addTrackedVertexAttribute(this.shader.aPosition);
        this.glStateBackup.readFromGL();
        this.settingsButtonRenderer.initializeGl();
        this.alignmentMarkerRenderer.initializeGl();
        this.glStateBackup.writeToGL();
        this.initialized = true;
    }

    void draw()
    {

        if (this.uiLayerEnabled) {
            if (this.getSettingsButtonEnabled() || this.getAlignmentMarkerEnabled()) {
                if (!this.initialized) {
                    this.initializeGl();
                }

                this.glStateBackup.readFromGL();
                synchronized(this) {
                    if (this.shouldUpdateViewport) {
                        this.shouldUpdateViewport = false;
                        this.settingsButtonRenderer.updateViewport(this.viewport);
                        this.alignmentMarkerRenderer.updateViewport(this.viewport);
                    }

                    this.viewport.setGLViewport();
                }

                if (this.getSettingsButtonEnabled()) {
                    this.settingsButtonRenderer.draw();
                }

                if (this.getAlignmentMarkerEnabled()) {
                    this.alignmentMarkerRenderer.draw();
                }

                this.glStateBackup.writeToGL();
            }
        }
    }

    synchronized void setAlignmentMarkerEnabled(boolean enabled)
    {

        if (this.alignmentMarkerEnabled != enabled) {
            this.alignmentMarkerEnabled = enabled;
            this.shouldUpdateViewport = true;
        }

    }

    synchronized boolean getAlignmentMarkerEnabled()
    {

        return this.alignmentMarkerEnabled;
    }

    synchronized void setSettingsButtonEnabled(boolean enabled)
    {

        if (this.settingsButtonEnabled != enabled) {
            this.settingsButtonEnabled = enabled;
            this.shouldUpdateViewport = true;
        }

    }

    synchronized boolean getSettingsButtonEnabled()
    {

        return this.settingsButtonEnabled;
    }

    boolean onTouchEvent(MotionEvent e)
    {

        if (!this.uiLayerEnabled) {
            return false;
        } else {
            boolean touchWithinBounds = false;
            synchronized(this) {
                if (!this.settingsButtonEnabled) {
                    return false;
                }

                touchWithinBounds = this.touchRect.contains((int)e.getX(), (int)e.getY());
            }

            if (e.getActionMasked() == 0 && touchWithinBounds) {
                this.downWithinBounds = true;
            }

            if (!this.downWithinBounds) {
                return false;
            } else {
                if (e.getActionMasked() == 1) {
                    if (touchWithinBounds) {
                        UiUtils.launchOrInstallCardboard(this.context);
                    }

                    this.downWithinBounds = false;
                } else if (e.getActionMasked() == 3) {
                    this.downWithinBounds = false;
                }

                this.setPressed(this.downWithinBounds && touchWithinBounds);
                return true;
            }
        }
    }

    private void setPressed(boolean pressed)
    {

        if (this.settingsButtonRenderer != null) {
            this.settingsButtonRenderer.setColor(pressed ? -12303292 : -3355444);
        }

    }

    private static void clearGlError()
    {

        while (GLES20.glGetError() != 0) {
            ;
        }

    }

    private static void checkGlError(String op)
    {

        int error;

        if ((error = GLES20.glGetError()) != 0) {
            Log.e(TAG, op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    private static float lerp(float a, float b, float t)
    {

        return a * (1.0F - t) + b * t;
    }

    private static class SettingsButtonRenderer extends UiLayer.MeshRenderer
    {
        private static final int DEGREES_PER_GEAR_SECTION = 60;
        private static final int OUTER_RIM_END_DEG = 12;
        private static final int INNER_RIM_BEGIN_DEG = 20;
        private static final float OUTER_RADIUS = 1.0F;
        private static final float MIDDLE_RADIUS = 0.75F;
        private static final float INNER_RADIUS = 0.3125F;
        private static final int NUM_VERTICES = 60;
        private int buttonWidthPx;
        private int color = -3355444;

        SettingsButtonRenderer(UiLayer.ShaderProgram shader, int buttonWidthPx)
        {

            super(shader);

            this.buttonWidthPx = buttonWidthPx;
        }

        void initializeGl()
        {

            float[] vertexData = new float[120];
            byte numVerticesPerRim = 30;
            float lerpInterval = 8.0F;

            int innerStartingIndex;
            float i;

            for (innerStartingIndex = 0; innerStartingIndex < numVerticesPerRim;
                 ++innerStartingIndex) {
                float indexData = (float)innerStartingIndex / (float)numVerticesPerRim * 360.0F;
                i = indexData % 60.0F;
                float r;

                if (i <= 12.0F) {
                    r = 1.0F;
                } else if (i <= 20.0F) {
                    r = UiLayer.lerp(1.0F, 0.75F, (i - 12.0F) / lerpInterval);
                } else if (i <= 40.0F) {
                    r = 0.75F;
                } else if (i <= 48.0F) {
                    r = UiLayer.lerp(0.75F, 1.0F, (i - 60.0F + 20.0F) / lerpInterval);
                } else {
                    r = 1.0F;
                }

                vertexData[2 * innerStartingIndex] = r * (float)Math.cos(Math.toRadians((
                        double)(90.0F - indexData)));
                vertexData[2 * innerStartingIndex + 1] = r * (float)Math.sin(Math.toRadians((
                            double)(90.0F - indexData)));
            }

            innerStartingIndex = 2 * numVerticesPerRim;

            for (int var8 = 0; var8 < numVerticesPerRim; ++var8) {
                i = (float)var8 / (float)numVerticesPerRim * 360.0F;
                vertexData[innerStartingIndex + 2 * var8] = 0.3125F * (float)Math.cos(
                            Math.toRadians((double)(90.0F - i)));
                vertexData[innerStartingIndex + 2 * var8 + 1] = 0.3125F * (float)Math.sin(
                            Math.toRadians((double)(90.0F - i)));
            }

            short[] var9 = new short[62];

            for (int var10 = 0; var10 < numVerticesPerRim; ++var10) {
                var9[2 * var10] = (short)var10;
                var9[2 * var10 + 1] = (short)(numVerticesPerRim + var10);
            }

            var9[var9.length - 2] = 0;
            var9[var9.length - 1] = (short)numVerticesPerRim;
            this.genAndBindBuffers(vertexData, var9);
        }

        synchronized void setColor(int color)
        {

            this.color = color;
        }

        void updateViewport(Viewport viewport)
        {

            Matrix.setIdentityM(this.mvp, 0);
            float yScale = (float)this.buttonWidthPx / (float)viewport.height;
            float xScale = yScale * (float)viewport.height / (float)viewport.width;
            Matrix.translateM(this.mvp, 0, 0.0F, yScale - 1.0F, 0.0F);
            Matrix.scaleM(this.mvp, 0, xScale, yScale, 1.0F);
        }

        void draw()
        {

            GLES20.glUseProgram(this.shader.program);
            synchronized(this) {
                GLES20.glUniform4f(this.shader.uColor, (float)Color.red(this.color) / 255.0F,
                                   (float)Color.green(this.color) / 255.0F, (float)Color.blue(this.color) / 255.0F,
                                   (float)Color.alpha(this.color) / 255.0F);
            }

            super.draw();
        }
    }

    private static class AlignmentMarkerRenderer extends UiLayer.MeshRenderer
    {
        private static final int COLOR = Color.argb(255, 50, 50, 50);
        private float verticalBorderPaddingPx;
        private float lineThicknessPx;

        AlignmentMarkerRenderer(UiLayer.ShaderProgram shader,
                                float verticalBorderPaddingPx, float lineThicknessPx)
        {

            super(shader);

            this.verticalBorderPaddingPx = verticalBorderPaddingPx;
            this.lineThicknessPx = lineThicknessPx;
        }

        void initializeGl()
        {

            float[] vertexData = new float[] {1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, -1.0F, -1.0F};
            short[] indexData = new short[vertexData.length / 2];

            for (int i = 0; i < indexData.length; ++i) {
                indexData[i] = (short)i;
            }

            this.genAndBindBuffers(vertexData, indexData);
        }

        void updateViewport(Viewport viewport)
        {

            Matrix.setIdentityM(this.mvp, 0);
            float xScale = this.lineThicknessPx / (float)viewport.width;
            float yScale = 1.0F - 2.0F * this.verticalBorderPaddingPx /
                           (float)viewport.height;
            Matrix.scaleM(this.mvp, 0, xScale, yScale, 1.0F);
        }

        void draw()
        {

            GLES20.glUseProgram(this.shader.program);
            GLES20.glUniform4f(this.shader.uColor, (float)Color.red(COLOR) / 255.0F,
                               (float)Color.green(COLOR) / 255.0F, (float)Color.blue(COLOR) / 255.0F,
                               (float)Color.alpha(COLOR) / 255.0F);
            super.draw();
        }
    }

    private static class MeshRenderer
    {
        private static final int BYTES_PER_FLOAT = 4;
        private static final int BYTES_PER_SHORT = 4;
        protected static final int COMPONENTS_PER_VERT = 2;
        private static final int DATA_STRIDE_BYTES = 8;
        private static final int DATA_POS_OFFSET = 0;
        protected int arrayBufferId = -1;
        protected int elementBufferId = -1;
        protected UiLayer.ShaderProgram shader;
        protected float[] mvp = new float[16];
        private int numIndices;

        MeshRenderer(UiLayer.ShaderProgram shader)
        {

            this.shader = shader;
        }

        void genAndBindBuffers(float[] vertexData, short[] indexData)
        {

            FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(vertexData.length *
                                       4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            vertexBuffer.put(vertexData).position(0);
            this.numIndices = indexData.length;
            ShortBuffer indexBuffer = ByteBuffer.allocateDirect(this.numIndices * 4).order(
                                          ByteOrder.nativeOrder()).asShortBuffer();
            indexBuffer.put(indexData).position(0);
            int[] bufferIds = new int[2];
            GLES20.glGenBuffers(2, bufferIds, 0);
            this.arrayBufferId = bufferIds[0];
            this.elementBufferId = bufferIds[1];
            UiLayer.clearGlError();
            GLES20.glBindBuffer('袒', this.arrayBufferId);
            GLES20.glBufferData('袒', vertexData.length * 4, vertexBuffer, '裤');
            GLES20.glBindBuffer('袓', this.elementBufferId);
            GLES20.glBufferData('袓', indexData.length * 4, indexBuffer, '裤');
            UiLayer.checkGlError("genAndBindBuffers");
        }

        void updateViewport(Viewport viewport)
        {

            Matrix.setIdentityM(this.mvp, 0);
        }

        void draw()
        {

            GLES20.glDisable(2929);
            GLES20.glDisable(2884);
            GLES20.glUseProgram(this.shader.program);
            GLES20.glUniformMatrix4fv(this.shader.uMvpMatrix, 1, false, this.mvp, 0);
            GLES20.glBindBuffer('袒', this.arrayBufferId);
            GLES20.glVertexAttribPointer(this.shader.aPosition, 2, 5126, false, 8, 0);
            GLES20.glEnableVertexAttribArray(this.shader.aPosition);
            GLES20.glBindBuffer('袓', this.elementBufferId);
            GLES20.glDrawElements(5, this.numIndices, 5123, 0);
        }
    }

    private static class ShaderProgram
    {
        private static final String VERTEX_SHADER =
            "uniform mat4 uMVPMatrix;\nattribute vec2 aPosition;\nvoid main() {\n  gl_Position = uMVPMatrix * vec4(aPosition, 0.0, 1.0);\n}\n";
        private static final String FRAGMENT_SHADER =
            "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n  gl_FragColor = uColor;\n}\n";
        public int program;
        public int aPosition;
        public int uMvpMatrix;
        public int uColor;

        private ShaderProgram()
        {

        }

        void initializeGl()
        {

            this.program =
                this.createProgram("uniform mat4 uMVPMatrix;\nattribute vec2 aPosition;\nvoid main() {\n  gl_Position = uMVPMatrix * vec4(aPosition, 0.0, 1.0);\n}\n",
                                   "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n  gl_FragColor = uColor;\n}\n");

            if (this.program == 0) {
                throw new RuntimeException("Could not create program");
            } else {
                UiLayer.clearGlError();
                this.aPosition = GLES20.glGetAttribLocation(this.program, "aPosition");
                UiLayer.checkGlError("glGetAttribLocation aPosition");

                if (this.aPosition == -1) {
                    throw new RuntimeException("Could not get attrib location for aPosition");
                } else {
                    this.uMvpMatrix = GLES20.glGetUniformLocation(this.program, "uMVPMatrix");

                    if (this.uMvpMatrix == -1) {
                        throw new RuntimeException("Could not get uniform location for uMVPMatrix");
                    } else {
                        this.uColor = GLES20.glGetUniformLocation(this.program, "uColor");

                        if (this.uColor == -1) {
                            throw new RuntimeException("Could not get uniform location for uColor");
                        }
                    }
                }
            }
        }

        private int loadShader(int shaderType, String source)
        {

            int shader = GLES20.glCreateShader(shaderType);

            if (shader != 0) {
                GLES20.glShaderSource(shader, source);
                GLES20.glCompileShader(shader);
                int[] compiled = new int[1];
                GLES20.glGetShaderiv(shader, '讁', compiled, 0);

                if (compiled[0] == 0) {
                    Log.e(UiLayer.TAG, "Could not compile shader " + shaderType + ":");
                    Log.e(UiLayer.TAG, GLES20.glGetShaderInfoLog(shader));
                    GLES20.glDeleteShader(shader);
                    shader = 0;
                }
            }

            return shader;
        }

        private int createProgram(String vertexSource, String fragmentSource)
        {

            int vertexShader = this.loadShader('謱', vertexSource);

            if (vertexShader == 0) {
                return 0;
            } else {
                int pixelShader = this.loadShader('謰', fragmentSource);

                if (pixelShader == 0) {
                    return 0;
                } else {
                    int program = GLES20.glCreateProgram();

                    if (program != 0) {
                        UiLayer.clearGlError();
                        GLES20.glAttachShader(program, vertexShader);
                        UiLayer.checkGlError("glAttachShader");
                        GLES20.glAttachShader(program, pixelShader);
                        UiLayer.checkGlError("glAttachShader");
                        GLES20.glLinkProgram(program);
                        int[] linkStatus = new int[1];
                        GLES20.glGetProgramiv(program, '讂', linkStatus, 0);

                        if (linkStatus[0] != 1) {
                            Log.e(UiLayer.TAG, "Could not link program: ");
                            Log.e(UiLayer.TAG, GLES20.glGetProgramInfoLog(program));
                            GLES20.glDeleteProgram(program);
                            program = 0;
                        }

                        UiLayer.checkGlError("glLinkProgram");
                    }

                    return program;
                }
            }
        }
    }
}
