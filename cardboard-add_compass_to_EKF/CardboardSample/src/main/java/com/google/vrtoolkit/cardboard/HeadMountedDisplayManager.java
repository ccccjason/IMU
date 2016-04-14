//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HeadMountedDisplayManager
{
    private static final String TAG = "HeadMountedDisplayManager";
    private final HeadMountedDisplay hmd;
    private final Context context;

    public HeadMountedDisplayManager(Context context)
    {

        this.context = context;
        this.hmd = new HeadMountedDisplay(this.createScreenParams(),
                                          this.createCardboardDeviceParams());
    }

    public HeadMountedDisplay getHeadMountedDisplay()
    {

        return this.hmd;
    }

    public void onResume()
    {

        CardboardDeviceParams deviceParams =
            this.createCardboardDeviceParamsFromExternalStorage();

        if (deviceParams != null &&
            !deviceParams.equals(this.hmd.getCardboardDeviceParams())) {
            this.hmd.setCardboardDeviceParams(deviceParams);
            Log.i("HeadMountedDisplayManager",
                  "Successfully read updated device params from external storage");
        }

        ScreenParams screenParams = this.createScreenParamsFromExternalStorage(
                                        this.getDisplay());

        if (screenParams != null && !screenParams.equals(this.hmd.getScreenParams())) {
            this.hmd.setScreenParams(screenParams);
            Log.i("HeadMountedDisplayManager",
                  "Successfully read updated screen params from external storage");
        }

    }

    public void onPause()
    {

    }

    public boolean updateCardboardDeviceParams(CardboardDeviceParams
            cardboardDeviceParams)
    {

        if (cardboardDeviceParams != null &&
            !cardboardDeviceParams.equals(this.hmd.getCardboardDeviceParams())) {
            this.hmd.setCardboardDeviceParams(cardboardDeviceParams);
            this.writeCardboardParamsToExternalStorage();
            return true;
        } else {
            return false;
        }
    }

    public boolean updateScreenParams(ScreenParams screenParams)
    {

        if (screenParams != null && !screenParams.equals(this.hmd.getScreenParams())) {
            this.hmd.setScreenParams(screenParams);
            return true;
        } else {
            return false;
        }
    }

    private void writeCardboardParamsToExternalStorage()
    {

        boolean success = false;
        BufferedOutputStream stream = null;

        try {
            stream = new BufferedOutputStream(new FileOutputStream(
                                                  ConfigUtils.getConfigFile("current_device_params")));
            success = this.hmd.getCardboardDeviceParams().writeToOutputStream(stream);
        } catch (FileNotFoundException var14) {
            Log.e("HeadMountedDisplayManager",
                  "Unexpected file not found exception: " + var14);
        } catch (IllegalStateException var15) {
            Log.w("HeadMountedDisplayManager", "Error writing phone parameters: " + var15);
        }

        finally {
            if (stream != null)
            {
                try {
                    stream.close();
                } catch (IOException var13) {
                    ;
                }
            }

        }

        if (!success) {
            Log.e("HeadMountedDisplayManager",
                  "Could not write Cardboard parameters to external storage.");
        } else {
            Log.i("HeadMountedDisplayManager",
                  "Successfully wrote Cardboard parameters to external storage.");
        }

    }

    private Display getDisplay()
    {

        WindowManager windowManager = (WindowManager)this.context.getSystemService(
                                          Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay();
    }

    private ScreenParams createScreenParams()
    {

        Display display = this.getDisplay();
        ScreenParams params = this.createScreenParamsFromExternalStorage(display);

        if (params != null) {
            Log.i("HeadMountedDisplayManager",
                  "Successfully read screen params from external storage");
            return params;
        } else {
            return new ScreenParams(display);
        }
    }

    private CardboardDeviceParams createCardboardDeviceParams()
    {

        CardboardDeviceParams params =
            this.createCardboardDeviceParamsFromExternalStorage();

        if (params != null) {
            Log.i("HeadMountedDisplayManager",
                  "Successfully read device params from external storage");
            return params;
        } else {
            params = this.createCardboardDeviceParamsFromAssetFolder();

            if (params != null) {
                Log.i("HeadMountedDisplayManager",
                      "Successfully read device params from asset folder");
                this.writeCardboardParamsToExternalStorage();
                return params;
            } else {
                return new CardboardDeviceParams();
            }
        }
    }

    private CardboardDeviceParams createCardboardDeviceParamsFromAssetFolder()
    {

        try {
            BufferedInputStream e = null;

            CardboardDeviceParams var2;

            try {
                e = new BufferedInputStream(ConfigUtils.openAssetConfigFile(
                                                this.context.getAssets(), "current_device_params"));
                var2 = CardboardDeviceParams.createFromInputStream(e);
            }

            finally {
                if (e != null)
                {
                    e.close();
                }

            }

            return var2;
        } catch (FileNotFoundException var8) {
            Log.d("HeadMountedDisplayManager",
                  "Bundled Cardboard device parameters not found: " + var8);
        } catch (IOException var9) {
            Log.e("HeadMountedDisplayManager",
                  "Error reading setConfig file in asset folder: " + var9);
        }

        return null;
    }

    private CardboardDeviceParams createCardboardDeviceParamsFromExternalStorage()
    {

        try {
            BufferedInputStream e = null;

            CardboardDeviceParams var2;

            try {
                e = new BufferedInputStream(new FileInputStream(
                                                ConfigUtils.getConfigFile("current_device_params")));
                var2 = CardboardDeviceParams.createFromInputStream(e);
            }

            finally {
                if (e != null)
                {
                    try {
                        e.close();
                    } catch (IOException var11) {
                        ;
                    }
                }

            }

            return var2;
        } catch (FileNotFoundException var13) {
            Log.d("HeadMountedDisplayManager",
                  "Cardboard device parameters file not found: " + var13);
        } catch (IllegalStateException var14) {
            Log.w("HeadMountedDisplayManager",
                  "Error reading Cardboard device parameters: " + var14);
        }

        return null;
    }

    private ScreenParams createScreenParamsFromExternalStorage(Display display)
    {

        try {
            BufferedInputStream e = null;

            ScreenParams var3;

            try {
                e = new BufferedInputStream(new FileInputStream(
                                                ConfigUtils.getConfigFile("phone_params")));
                var3 = ScreenParams.createFromInputStream(display, e);
            }

            finally {
                if (e != null)
                {
                    try {
                        e.close();
                    } catch (IOException var12) {
                        ;
                    }
                }

            }

            return var3;
        } catch (FileNotFoundException var14) {
            Log.d("HeadMountedDisplayManager",
                  "Cardboard screen parameters file not found: " + var14);
        } catch (IllegalStateException var15) {
            Log.w("HeadMountedDisplayManager",
                  "Error reading Cardboard screen parameters: " + var15);
        }

        return null;
    }
}
