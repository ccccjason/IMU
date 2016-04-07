//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.net.Uri;
import android.net.Uri.Builder;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.util.Base64;
import android.util.Log;

import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.vrtoolkit.cardboard.proto.CardboardDevice.DeviceParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class CardboardDeviceParams
{
    private static final String TAG = "CardboardDeviceParams";
    private static final String HTTP_SCHEME = "http";
    private static final String URI_HOST_GOOGLE_SHORT = "g.co";
    private static final String URI_HOST_GOOGLE = "google.com";
    private static final String URI_PATH_CARDBOARD_HOME = "cardboard";
    private static final String URI_PATH_CARDBOARD_CONFIG = "cardboard/cfg";
    private static final String URI_SCHEME_LEGACY_CARDBOARD = "cardboard";
    private static final String URI_HOST_LEGACY_CARDBOARD = "v1.0.0";
    private static final Uri URI_ORIGINAL_CARDBOARD_NFC = (new
            Builder()).scheme("cardboard").authority("v1.0.0").build();
    private static final Uri URI_ORIGINAL_CARDBOARD_QR_CODE =
        (new Builder()).scheme("http").authority("g.co").appendEncodedPath("cardboard").build();
    private static final String URI_KEY_PARAMS = "p";
    private static final int STREAM_SENTINEL = 894990891;
    private static final String DEFAULT_VENDOR = "Google, Inc.";
    private static final String DEFAULT_MODEL = "Cardboard v1";
    private static final float DEFAULT_INTER_LENS_DISTANCE = 0.06F;
    private static final CardboardDeviceParams.VerticalAlignmentType
    DEFAULT_VERTICAL_ALIGNMENT;
    private static final float DEFAULT_VERTICAL_DISTANCE_TO_LENS_CENTER = 0.035F;
    private static final float DEFAULT_SCREEN_TO_LENS_DISTANCE = 0.042F;
    private static final CardboardDeviceParams DEFAULT_PARAMS;
    private String vendor;
    private String model;
    private float interLensDistance;
    private CardboardDeviceParams.VerticalAlignmentType verticalAlignment;
    private float verticalDistanceToLensCenter;
    private float screenToLensDistance;
    private FieldOfView leftEyeMaxFov;
    private boolean hasMagnet;
    private Distortion distortion;

    public CardboardDeviceParams()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.setDefaultValues();
    }

    public CardboardDeviceParams(CardboardDeviceParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.copyFrom(params);
    }

    public CardboardDeviceParams(DeviceParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.setDefaultValues();

        if (params != null) {
            this.vendor = params.getVendor();
            this.model = params.getModel();
            this.interLensDistance = params.getInterLensDistance();
            this.verticalAlignment =
                CardboardDeviceParams.VerticalAlignmentType.fromProtoValue(
                    params.getVerticalAlignment());
            this.verticalDistanceToLensCenter = params.getTrayToLensDistance();
            this.screenToLensDistance = params.getScreenToLensDistance();
            this.leftEyeMaxFov = FieldOfView.parseFromProtobuf(
                                     params.leftEyeFieldOfViewAngles);

            if (this.leftEyeMaxFov == null) {
                this.leftEyeMaxFov = new FieldOfView();
            }

            this.distortion = Distortion.parseFromProtobuf(params.distortionCoefficients);

            if (this.distortion == null) {
                this.distortion = new Distortion();
            }

            this.hasMagnet = params.getHasMagnet();
        }
    }

    public static boolean isOriginalCardboardDeviceUri(Uri uri)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return URI_ORIGINAL_CARDBOARD_QR_CODE.equals(uri) ||
               URI_ORIGINAL_CARDBOARD_NFC.getScheme().equals(uri.getScheme()) &&
               URI_ORIGINAL_CARDBOARD_NFC.getAuthority().equals(uri.getAuthority());
    }

    private static boolean isCardboardDeviceUri(Uri uri)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return "http".equals(uri.getScheme()) &&
               "google.com".equals(uri.getAuthority()) &&
               "/cardboard/cfg".equals(uri.getPath());
    }

    public static boolean isCardboardUri(Uri uri)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return isOriginalCardboardDeviceUri(uri) || isCardboardDeviceUri(uri);
    }

    public static CardboardDeviceParams createFromUri(Uri uri)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (uri == null) {
            return null;
        } else if (isOriginalCardboardDeviceUri(uri)) {
            Log.d("CardboardDeviceParams", "URI recognized as original cardboard device.");
            CardboardDeviceParams params1 = new CardboardDeviceParams();
            params1.setDefaultValues();
            return params1;
        } else if (!isCardboardDeviceUri(uri)) {
            Log.w("CardboardDeviceParams",
                  String.format("URI \"%s\" not recognized as cardboard device.", new Object[] {uri}));
            return null;
        } else {
            DeviceParams params = null;
            String paramsEncoded = uri.getQueryParameter("p");

            if (paramsEncoded != null) {
                try {
                    byte[] e = Base64.decode(paramsEncoded, 11);
                    params = (DeviceParams) MessageNano.mergeFrom(new DeviceParams(), e);
                    Log.d("CardboardDeviceParams", "Read cardboard params from URI.");
                } catch (Exception var4) {
                    Log.w("CardboardDeviceParams",
                          "Parsing cardboard parameters from URI failed: " + var4.toString());
                }
            } else {
                Log.w("CardboardDeviceParams", "No cardboard parameters in URI.");
            }

            return new CardboardDeviceParams(params);
        }
    }

    public static CardboardDeviceParams createFromInputStream(
        InputStream inputStream)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (inputStream == null) {
            return null;
        } else {
            try {
                ByteBuffer e = ByteBuffer.allocate(8);

                if (inputStream.read(e.array(), 0, e.array().length) == -1) {
                    Log.e("CardboardDeviceParams", "Error parsing param record: end of stream.");
                    return null;
                }

                int sentinel = e.getInt();
                int length = e.getInt();

                if (sentinel != 894990891) {
                    Log.e("CardboardDeviceParams",
                          "Error parsing param record: incorrect sentinel.");
                    return null;
                }

                byte[] protoBytes = new byte[length];

                if (inputStream.read(protoBytes, 0, protoBytes.length) == -1) {
                    Log.e("CardboardDeviceParams", "Error parsing param record: end of stream.");
                    return null;
                }

                return new CardboardDeviceParams((DeviceParams) MessageNano.mergeFrom(
                                                     new DeviceParams(), protoBytes));
            } catch (InvalidProtocolBufferNanoException var5) {
                Log.w("CardboardDeviceParams",
                      "Error parsing protocol buffer: " + var5.toString());
            } catch (IOException var6) {
                Log.w("CardboardDeviceParams",
                      "Error reading Cardboard parameters: " + var6.toString());
            }

            return null;
        }
    }

    public boolean writeToOutputStream(OutputStream outputStream)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        try {
            byte[] e = this.toByteArray();
            ByteBuffer header = ByteBuffer.allocate(8);
            header.putInt(894990891);
            header.putInt(e.length);
            outputStream.write(header.array());
            outputStream.write(e);
            return true;
        } catch (IOException var4) {
            Log.w("CardboardDeviceParams",
                  "Error writing Cardboard parameters: " + var4.toString());
            return false;
        }
    }

    public static CardboardDeviceParams createFromNfcContents(
        NdefMessage tagContents)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (tagContents == null) {
            Log.w("CardboardDeviceParams", "Could not get contents from NFC tag.");
            return null;
        } else {
            NdefRecord[] arr$ = tagContents.getRecords();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                NdefRecord record = arr$[i$];
                CardboardDeviceParams params = createFromUri(record.toUri());

                if (params != null) {
                    return params;
                }
            }

            return null;
        }
    }

    byte[] toByteArray()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        DeviceParams params = new DeviceParams();
        params.setVendor(this.vendor);
        params.setModel(this.model);
        params.setInterLensDistance(this.interLensDistance);
        params.setVerticalAlignment(this.verticalAlignment.toProtoValue());

        if (this.verticalAlignment ==
            CardboardDeviceParams.VerticalAlignmentType.CENTER) {
            params.setTrayToLensDistance(0.035F);
        } else {
            params.setTrayToLensDistance(this.verticalDistanceToLensCenter);
        }

        params.setScreenToLensDistance(this.screenToLensDistance);
        params.leftEyeFieldOfViewAngles = this.leftEyeMaxFov.toProtobuf();
        params.distortionCoefficients = this.distortion.toProtobuf();

        if (this.hasMagnet) {
            params.setHasMagnet(this.hasMagnet);
        }

        return MessageNano.toByteArray(params);
    }

    public Uri toUri()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        byte[] paramsData = this.toByteArray();
        int paramsSize = paramsData.length;
        return (new
                Builder()).scheme("http").authority("google.com").appendEncodedPath("cardboard/cfg").appendQueryParameter("p",
                        Base64.encodeToString(paramsData, 0, paramsSize, 11)).build();
    }

    public void setVendor(String vendor)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.vendor = vendor != null ? vendor : "";
    }

    public String getVendor()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.vendor;
    }

    public void setModel(String model)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.model = model != null ? model : "";
    }

    public String getModel()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.model;
    }

    public void setInterLensDistance(float interLensDistance)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.interLensDistance = interLensDistance;
    }

    public float getInterLensDistance()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.interLensDistance;
    }

    public CardboardDeviceParams.VerticalAlignmentType getVerticalAlignment()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.verticalAlignment;
    }

    public void setVerticalAlignment(CardboardDeviceParams.VerticalAlignmentType
                                     verticalAlignment)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.verticalAlignment = verticalAlignment;
    }

    public void setVerticalDistanceToLensCenter(float verticalDistanceToLensCenter)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.verticalDistanceToLensCenter = verticalDistanceToLensCenter;
    }

    public float getVerticalDistanceToLensCenter()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.verticalDistanceToLensCenter;
    }

    float getYEyeOffsetMeters(ScreenParams screen)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        //        switch(CardboardDeviceParams.SyntheticClass_1.$SwitchMap$com$google$vrtoolkit$cardboard$CardboardDeviceParams$VerticalAlignmentType[this.getVerticalAlignment().ordinal()]) {
        switch (this.getVerticalAlignment().ordinal()) {
        case 1:
        default:
            return screen.getHeightMeters() / 2.0F;

        case 2:
            return this.getVerticalDistanceToLensCenter() - screen.getBorderSizeMeters();

        case 3:
            return screen.getHeightMeters() - (this.getVerticalDistanceToLensCenter() -
                                               screen.getBorderSizeMeters());
        }
    }

    public void setScreenToLensDistance(float screenToLensDistance)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.screenToLensDistance = screenToLensDistance;
    }

    public float getScreenToLensDistance()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.screenToLensDistance;
    }

    public Distortion getDistortion()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.distortion;
    }

    public FieldOfView getLeftEyeMaxFov()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.leftEyeMaxFov;
    }

    public boolean getHasMagnet()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return this.hasMagnet;
    }

    public void setHasMagnet(boolean magnet)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.hasMagnet = magnet;
    }

    public boolean equals(Object other)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof CardboardDeviceParams)) {
            return false;
        } else {
            CardboardDeviceParams o = (CardboardDeviceParams)other;
            return this.vendor.equals(o.vendor) && this.model.equals(o.model) &&
                   this.interLensDistance == o.interLensDistance &&
                   this.verticalAlignment == o.verticalAlignment &&
                   (this.verticalAlignment == CardboardDeviceParams.VerticalAlignmentType.CENTER ||
                    this.verticalDistanceToLensCenter == o.verticalDistanceToLensCenter) &&
                   this.screenToLensDistance == o.screenToLensDistance &&
                   this.leftEyeMaxFov.equals(o.leftEyeMaxFov) &&
                   this.distortion.equals(o.distortion) && this.hasMagnet == o.hasMagnet;
        }
    }

    public String toString()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return "{\n" + "  vendor: " + this.vendor + ",\n" + "  model: " + this.model +
               ",\n" + "  inter_lens_distance: " + this.interLensDistance + ",\n" +
               "  vertical_alignment: " + this.verticalAlignment + ",\n" +
               "  vertical_distance_to_lens_center: " + this.verticalDistanceToLensCenter +
               ",\n" + "  screen_to_lens_distance: " + this.screenToLensDistance + ",\n" +
               "  left_eye_max_fov: " + this.leftEyeMaxFov.toString().replace("\n",
                       "\n  ") + ",\n" + "  distortion: " + this.distortion.toString().replace("\n",
                               "\n  ") + ",\n" + "  magnet: " + this.hasMagnet + ",\n" + "}\n";
    }

    public boolean isDefault()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        return DEFAULT_PARAMS.equals(this);
    }

    private void setDefaultValues()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.vendor = "Google, Inc.";
        this.model = "Cardboard v1";
        this.interLensDistance = 0.06F;
        this.verticalAlignment = DEFAULT_VERTICAL_ALIGNMENT;
        this.verticalDistanceToLensCenter = 0.035F;
        this.screenToLensDistance = 0.042F;
        this.leftEyeMaxFov = new FieldOfView();
        this.hasMagnet = true;
        this.distortion = new Distortion();
    }

    private void copyFrom(CardboardDeviceParams params)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        this.vendor = params.vendor;
        this.model = params.model;
        this.interLensDistance = params.interLensDistance;
        this.verticalAlignment = params.verticalAlignment;
        this.verticalDistanceToLensCenter = params.verticalDistanceToLensCenter;
        this.screenToLensDistance = params.screenToLensDistance;
        this.leftEyeMaxFov = new FieldOfView(params.leftEyeMaxFov);
        this.hasMagnet = params.hasMagnet;
        this.distortion = new Distortion(params.distortion);
    }

    static {
        DEFAULT_VERTICAL_ALIGNMENT = CardboardDeviceParams.VerticalAlignmentType.BOTTOM;
        DEFAULT_PARAMS = new CardboardDeviceParams();
    }

    public static enum VerticalAlignmentType {
        BOTTOM(0),
        CENTER(1),
        TOP(2);

        private final int protoValue;

        private VerticalAlignmentType(int protoValue)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.protoValue = protoValue;
        }

        int toProtoValue()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.protoValue;
        }

        static CardboardDeviceParams.VerticalAlignmentType fromProtoValue(int protoValue)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            CardboardDeviceParams.VerticalAlignmentType[] arr$ = values();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                CardboardDeviceParams.VerticalAlignmentType type = arr$[i$];

                if (type.protoValue == protoValue) {
                    return type;
                }
            }

            Log.e("CardboardDeviceParams",
                  String.format("Unknown alignment type from proto: %d", new Object[] {Integer.valueOf(protoValue)}));
            return BOTTOM;
        }
    }
}
