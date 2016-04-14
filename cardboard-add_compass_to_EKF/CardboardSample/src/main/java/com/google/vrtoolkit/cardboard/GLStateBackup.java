//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import android.util.Log;

class GLStateBackup
{
    private boolean cullFaceEnabled;
    private boolean scissorTestEnabled;
    private boolean depthTestEnabled;
    private IntBuffer viewport = IntBuffer.allocate(4);
    private IntBuffer texture2dId = IntBuffer.allocate(1);
    private IntBuffer textureUnit = IntBuffer.allocate(1);
    private IntBuffer scissorBox = IntBuffer.allocate(4);
    private IntBuffer shaderProgram = IntBuffer.allocate(1);
    private IntBuffer arrayBufferBinding = IntBuffer.allocate(1);
    private IntBuffer elementArrayBufferBinding = IntBuffer.allocate(1);
    private FloatBuffer clearColor = FloatBuffer.allocate(4);
    private ArrayList<VertexAttributeState> vertexAttributes = new ArrayList();

    GLStateBackup()
    {

    }

    void addTrackedVertexAttribute(int attributeId)
    {

        this.vertexAttributes.add(new GLStateBackup.VertexAttributeState(attributeId));
    }

    void clearTrackedVertexAttributes()
    {

        this.vertexAttributes.clear();
    }

    void readFromGL()
    {

        GLES20.glGetIntegerv(2978, this.viewport);
        this.cullFaceEnabled = GLES20.glIsEnabled(2884);
        this.scissorTestEnabled = GLES20.glIsEnabled(3089);
        this.depthTestEnabled = GLES20.glIsEnabled(2929);
        GLES20.glGetFloatv(3106, this.clearColor);
        GLES20.glGetIntegerv('讍', this.shaderProgram);
        GLES20.glGetIntegerv(3088, this.scissorBox);
        GLES20.glGetIntegerv('蓠', this.textureUnit);
        GLES20.glGetIntegerv('聩', this.texture2dId);
        GLES20.glGetIntegerv('袔', this.arrayBufferBinding);
        GLES20.glGetIntegerv('袕', this.elementArrayBufferBinding);
        Iterator i$ = this.vertexAttributes.iterator();

        while (i$.hasNext()) {
            GLStateBackup.VertexAttributeState vas = (GLStateBackup.VertexAttributeState)
                    i$.next();
            vas.readFromGL();
        }

    }

    void writeToGL()
    {

        Iterator i$ = this.vertexAttributes.iterator();

        while (i$.hasNext()) {
            GLStateBackup.VertexAttributeState vas = (GLStateBackup.VertexAttributeState)
                    i$.next();
            vas.writeToGL();
        }

        GLES20.glBindBuffer('袒', this.arrayBufferBinding.array()[0]);
        GLES20.glBindBuffer('袓', this.elementArrayBufferBinding.array()[0]);
        GLES20.glBindTexture(3553, this.texture2dId.array()[0]);
        GLES20.glActiveTexture(this.textureUnit.array()[0]);
        GLES20.glScissor(this.scissorBox.array()[0], this.scissorBox.array()[1],
                         this.scissorBox.array()[2], this.scissorBox.array()[3]);
        GLES20.glUseProgram(this.shaderProgram.array()[0]);
        GLES20.glClearColor(this.clearColor.array()[0], this.clearColor.array()[1],
                            this.clearColor.array()[2], this.clearColor.array()[3]);

        if (this.cullFaceEnabled) {
            GLES20.glEnable(2884);
        } else {
            GLES20.glDisable(2884);
        }

        if (this.scissorTestEnabled) {
            GLES20.glEnable(3089);
        } else {
            GLES20.glDisable(3089);
        }

        if (this.depthTestEnabled) {
            GLES20.glEnable(2929);
        } else {
            GLES20.glDisable(2929);
        }

        GLES20.glViewport(this.viewport.array()[0], this.viewport.array()[1],
                          this.viewport.array()[2], this.viewport.array()[3]);
    }

    private class VertexAttributeState
    {
        private int attributeId;
        private IntBuffer enabled = IntBuffer.allocate(1);

        VertexAttributeState(int attributeId)
        {

            this.attributeId = attributeId;
        }

        void readFromGL()
        {

            GLES20.glGetVertexAttribiv(this.attributeId, '蘢', this.enabled);
        }

        void writeToGL()
        {

            if (this.enabled.array()[0] == 0) {
                GLES20.glDisableVertexAttribArray(this.attributeId);
            } else {
                GLES20.glEnableVertexAttribArray(this.attributeId);
            }

        }
    }
}
