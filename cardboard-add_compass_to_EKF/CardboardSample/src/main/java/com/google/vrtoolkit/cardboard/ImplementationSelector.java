//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class ImplementationSelector
{
    public ImplementationSelector()
    {

    }

    public static CardboardViewApi createCardboardViewApi(Context context,
            GLSurfaceView view)
    {

        return new CardboardViewJavaImpl(context, view);
    }
}
