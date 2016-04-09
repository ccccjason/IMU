//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.os.Build;
import android.util.Log;

import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PhoneParams
{
    private static final String TAG = PhoneParams.class.getSimpleName();
    private static final int STREAM_SENTINEL = 779508118;
    private static final List<PpiOverride> PPI_OVERRIDES = Arrays.asList(
    new PhoneParams.PpiOverride[] {new PhoneParams.PpiOverride("Micromax", (String)null, "4560MMX", (String)null, 217, 217), new PhoneParams.PpiOverride("HTC", "endeavoru", "HTC One X", (String)null, 312, 312), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915FY", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915A", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915T", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915K", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915T", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915G", (String)null, 541, 541), new PhoneParams.PpiOverride("samsung", (String)null, "SM-N915D", (String)null, 541, 541)});

    private PhoneParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

    }

    static boolean getPpiOverride(List<PpiOverride> overrides, String manufacturer,
                                  String device, String model, String hardware,
                                  com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        Log.d(TAG,
              String.format("Override search for device: {MANUFACTURER=%s, DEVICE=%s, MODEL=%s, HARDWARE=%s}",
                            new Object[] {manufacturer, device, model, hardware}));
        Iterator i$ = overrides.iterator();

        PhoneParams.PpiOverride override;

        do {
            if (!i$.hasNext()) {
                return false;
            }

            override = (PhoneParams.PpiOverride)i$.next();
        } while (!override.isMatching(manufacturer, device, model, hardware));

        Log.d(TAG,
              String.format("Found override: {MANUFACTURER=%s, DEVICE=%s, MODEL=%s, HARDWARE=%s} : x_ppi=%d, y_ppi=%d",
                            new Object[] {override.manufacturer, override.device, override.model, override.hardware, Integer.valueOf(override.xPpi), Integer.valueOf(override.yPpi)}));
        params.setXPpi((float)override.xPpi);
        params.setYPpi((float)override.yPpi);
        return true;
    }

    static void registerOverridesInternal(List<PpiOverride> overrides,
                                          String manufacturer, String device, String model, String hardware)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams currentParams =
            readFromExternalStorage();
        com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams newParams = currentParams
                == null ? new com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams() :
                currentParams.clone();

        if (getPpiOverride(overrides, manufacturer, device, model, hardware,
                           newParams) && !MessageNano.messageNanoEquals(currentParams, newParams)) {
            Log.i(TAG, "Applying phone param override.");
            writeToExternalStorage(newParams);
        }

    }

    public static void registerOverrides()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        registerOverridesInternal(PPI_OVERRIDES, Build.MANUFACTURER, Build.DEVICE,
                                  Build.MODEL, Build.HARDWARE);
    }

    static com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams
    readFromInputStream(InputStream inputStream)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (inputStream == null) {
            return null;
        } else {
            try {
                ByteBuffer e = ByteBuffer.allocate(8);

                if (inputStream.read(e.array(), 0, e.array().length) == -1) {
                    Log.e(TAG, "Error parsing param record: end of stream.");
                    return null;
                }

                int sentinel = e.getInt();
                int length = e.getInt();

                if (sentinel != 779508118) {
                    Log.e(TAG, "Error parsing param record: incorrect sentinel.");
                    return null;
                }

                byte[] protoBytes = new byte[length];

                if (inputStream.read(protoBytes, 0, protoBytes.length) == -1) {
                    Log.e(TAG, "Error parsing param record: end of stream.");
                    return null;
                }

                return (com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams)
                       MessageNano.mergeFrom(new
                                             com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams(), protoBytes);
            } catch (InvalidProtocolBufferNanoException var5) {
                Log.w(TAG, "Error parsing protocol buffer: " + var5.toString());
            } catch (IOException var6) {
                Log.w(TAG, "Error reading Cardboard parameters: " + var6.toString());
            }

            return null;
        }
    }

    static com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams
    readFromExternalStorage()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        try {
            BufferedInputStream e = null;

            com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams var1;

            try {
                e = new BufferedInputStream(new FileInputStream(
                                                ConfigUtils.getConfigFile("phone_params")));
                var1 = readFromInputStream(e);
            }

            finally {
                if (e != null)
                {
                    try {
                        e.close();
                    } catch (IOException var10) {
                        ;
                    }
                }

            }

            return var1;
        } catch (FileNotFoundException var12) {
            Log.d(TAG, "Cardboard phone parameters file not found: " + var12);
        } catch (IllegalStateException var13) {
            Log.w(TAG, "Error reading phone parameters: " + var13);
        }

        return null;
    }

    static boolean writeToOutputStream(
        com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams params,
        OutputStream outputStream)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        try {
            if (params.dEPRECATEDGyroBias != null &&
                params.dEPRECATEDGyroBias.length == 0) {
                params = params.clone();
                params.dEPRECATEDGyroBias = new float[] {0.0F, 0.0F, 0.0F};
            }

            byte[] e = MessageNano.toByteArray(params);
            ByteBuffer header = ByteBuffer.allocate(8);
            header.putInt(779508118);
            header.putInt(e.length);
            outputStream.write(header.array());
            outputStream.write(e);
            return true;
        } catch (IOException var4) {
            Log.w(TAG, "Error writing phone parameters: " + var4.toString());
            return false;
        }
    }

    static boolean writeToExternalStorage(
        com.google.vrtoolkit.cardboard.proto.Phone.PhoneParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        boolean success = false;
        BufferedOutputStream stream = null;

        try {
            stream = new BufferedOutputStream(new FileOutputStream(
                                                  ConfigUtils.getConfigFile("phone_params")));
            success = writeToOutputStream(params, stream);
        } catch (FileNotFoundException var14) {
            Log.e(TAG, "Unexpected file not found exception: " + var14);
        } catch (IllegalStateException var15) {
            Log.w(TAG, "Error writing phone parameters: " + var15);
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

        return success;
    }

    static class PpiOverride
    {
        String manufacturer;
        String device;
        String model;
        String hardware;
        int xPpi;
        int yPpi;

        PpiOverride(String manufacturer, String device, String model, String hardware,
                    int xPpi, int yPpi)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.manufacturer = manufacturer;
            this.device = device;
            this.model = model;
            this.hardware = hardware;
            this.xPpi = xPpi;
            this.yPpi = yPpi;
        }

        boolean isMatching(String manufacturer, String device, String model,
                           String hardware)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.manufacturer == null || this.manufacturer.equals(manufacturer)) &&
                   (this.device == null || this.device.equals(device)) && (this.model == null ||
                           this.model.equals(model)) && (this.hardware == null ||
                                   this.hardware.equals(hardware));
        }
    }
}
