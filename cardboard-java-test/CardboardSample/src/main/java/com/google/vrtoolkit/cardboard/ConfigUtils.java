//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import android.util.Log;

public abstract class ConfigUtils
{
    public static final String CARDBOARD_CONFIG_FOLDER = "Cardboard";
    public static final String CARDBOARD_DEVICE_PARAMS_FILE =
        "current_device_params";
    public static final String CARDBOARD_PHONE_PARAMS_FILE = "phone_params";

    public ConfigUtils()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

    }

    public static File getConfigFile(String filename)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        File configFolder = new File(Environment.getExternalStorageDirectory(),
                                     "Cardboard");

        if (!configFolder.exists()) {
            configFolder.mkdirs();
        } else if (!configFolder.isDirectory()) {
            throw new IllegalStateException(configFolder +
                                            " already exists as a file, but is " + "expected to be a directory.");
        }

        return new File(configFolder, filename);
    }

    public static InputStream openAssetConfigFile(AssetManager assetManager,
            String filename) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        String assetPath = (new File("Cardboard", filename)).getPath();
        return assetManager.open(assetPath);
    }
}
