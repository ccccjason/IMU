//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

import java.io.IOException;
import android.util.Log;

public interface CardboardDevice {
    public static final class DeviceParams extends MessageNano implements Cloneable {
        private static volatile CardboardDevice.DeviceParams[] _emptyArray;
        private int bitField0_;
        private String vendor_;
        private String model_;
        private float screenToLensDistance_;
        private float interLensDistance_;
        public float[] leftEyeFieldOfViewAngles;
        private int verticalAlignment_;
        private float trayToLensDistance_;
        public float[] distortionCoefficients;
        private boolean hasMagnet_;
        private int primaryButton_;

        public static CardboardDevice.DeviceParams[] emptyArray()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if (_emptyArray == null) {
                Object var0 = InternalNano.LAZY_INIT_LOCK;
                synchronized(InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardboardDevice.DeviceParams[0];
                    }
                }
            }

            return _emptyArray;
        }

        public String getVendor()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.vendor_;
        }

        public CardboardDevice.DeviceParams setVendor(String value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if (value == null) {
                throw new NullPointerException();
            } else {
                this.vendor_ = value;
                this.bitField0_ |= 1;
                return this;
            }
        }

        public boolean hasVendor()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 1) != 0;
        }

        public CardboardDevice.DeviceParams clearVendor()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.vendor_ = "";
            this.bitField0_ &= -2;
            return this;
        }

        public String getModel()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.model_;
        }

        public CardboardDevice.DeviceParams setModel(String value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if (value == null) {
                throw new NullPointerException();
            } else {
                this.model_ = value;
                this.bitField0_ |= 2;
                return this;
            }
        }

        public boolean hasModel()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 2) != 0;
        }

        public CardboardDevice.DeviceParams clearModel()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.model_ = "";
            this.bitField0_ &= -3;
            return this;
        }

        public float getScreenToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.screenToLensDistance_;
        }

        public CardboardDevice.DeviceParams setScreenToLensDistance(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.screenToLensDistance_ = value;
            this.bitField0_ |= 4;
            return this;
        }

        public boolean hasScreenToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 4) != 0;
        }

        public CardboardDevice.DeviceParams clearScreenToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.screenToLensDistance_ = 0.0F;
            this.bitField0_ &= -5;
            return this;
        }

        public float getInterLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.interLensDistance_;
        }

        public CardboardDevice.DeviceParams setInterLensDistance(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.interLensDistance_ = value;
            this.bitField0_ |= 8;
            return this;
        }

        public boolean hasInterLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 8) != 0;
        }

        public CardboardDevice.DeviceParams clearInterLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.interLensDistance_ = 0.0F;
            this.bitField0_ &= -9;
            return this;
        }

        public int getVerticalAlignment()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.verticalAlignment_;
        }

        public CardboardDevice.DeviceParams setVerticalAlignment(int value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.verticalAlignment_ = value;
            this.bitField0_ |= 16;
            return this;
        }

        public boolean hasVerticalAlignment()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 16) != 0;
        }

        public CardboardDevice.DeviceParams clearVerticalAlignment()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.verticalAlignment_ = 0;
            this.bitField0_ &= -17;
            return this;
        }

        public float getTrayToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.trayToLensDistance_;
        }

        public CardboardDevice.DeviceParams setTrayToLensDistance(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.trayToLensDistance_ = value;
            this.bitField0_ |= 32;
            return this;
        }

        public boolean hasTrayToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 32) != 0;
        }

        public CardboardDevice.DeviceParams clearTrayToLensDistance()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.trayToLensDistance_ = 0.0F;
            this.bitField0_ &= -33;
            return this;
        }

        public boolean getHasMagnet()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.hasMagnet_;
        }

        public CardboardDevice.DeviceParams setHasMagnet(boolean value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.hasMagnet_ = value;
            this.bitField0_ |= 64;
            return this;
        }

        public boolean hasHasMagnet()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 64) != 0;
        }

        public CardboardDevice.DeviceParams clearHasMagnet()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.hasMagnet_ = false;
            this.bitField0_ &= -65;
            return this;
        }

        public int getPrimaryButton()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.primaryButton_;
        }

        public CardboardDevice.DeviceParams setPrimaryButton(int value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.primaryButton_ = value;
            this.bitField0_ |= 128;
            return this;
        }

        public boolean hasPrimaryButton()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 128) != 0;
        }

        public CardboardDevice.DeviceParams clearPrimaryButton()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.primaryButton_ = 1;
            this.bitField0_ &= -129;
            return this;
        }

        public DeviceParams()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.clear();
        }

        public CardboardDevice.DeviceParams clear()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.bitField0_ = 0;
            this.vendor_ = "";
            this.model_ = "";
            this.screenToLensDistance_ = 0.0F;
            this.interLensDistance_ = 0.0F;
            this.leftEyeFieldOfViewAngles = WireFormatNano.EMPTY_FLOAT_ARRAY;
            this.verticalAlignment_ = 0;
            this.trayToLensDistance_ = 0.0F;
            this.distortionCoefficients = WireFormatNano.EMPTY_FLOAT_ARRAY;
            this.hasMagnet_ = false;
            this.primaryButton_ = 1;
            this.cachedSize = -1;
            return this;
        }

        public CardboardDevice.DeviceParams clone()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            CardboardDevice.DeviceParams cloned;

            try {
                cloned = (CardboardDevice.DeviceParams)super.clone();
            } catch (CloneNotSupportedException var3) {
                throw new AssertionError(var3);
            }

            if (this.leftEyeFieldOfViewAngles != null &&
                this.leftEyeFieldOfViewAngles.length > 0) {
                cloned.leftEyeFieldOfViewAngles = (float[])
                                                  this.leftEyeFieldOfViewAngles.clone();
            }

            if (this.distortionCoefficients != null &&
                this.distortionCoefficients.length > 0) {
                cloned.distortionCoefficients = (float[])this.distortionCoefficients.clone();
            }

            return cloned;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if ((this.bitField0_ & 1) != 0)
            {
                output.writeString(1, this.vendor_);
            }

            if ((this.bitField0_ & 2) != 0)
            {
                output.writeString(2, this.model_);
            }

            if ((this.bitField0_ & 4) != 0)
            {
                output.writeFloat(3, this.screenToLensDistance_);
            }

            if ((this.bitField0_ & 8) != 0)
            {
                output.writeFloat(4, this.interLensDistance_);
            }

            int dataSize;
            int i;

            if (this.leftEyeFieldOfViewAngles != null && this.leftEyeFieldOfViewAngles.length > 0)
            {
                dataSize = 4 * this.leftEyeFieldOfViewAngles.length;
                output.writeRawVarint32(42);
                output.writeRawVarint32(dataSize);

                for (i = 0; i < this.leftEyeFieldOfViewAngles.length; ++i) {
                    output.writeFloatNoTag(this.leftEyeFieldOfViewAngles[i]);
                }
            }

            if ((this.bitField0_ & 32) != 0)
            {
                output.writeFloat(6, this.trayToLensDistance_);
            }

            if (this.distortionCoefficients != null && this.distortionCoefficients.length > 0)
            {
                dataSize = 4 * this.distortionCoefficients.length;
                output.writeRawVarint32(58);
                output.writeRawVarint32(dataSize);

                for (i = 0; i < this.distortionCoefficients.length; ++i) {
                    output.writeFloatNoTag(this.distortionCoefficients[i]);
                }
            }

            if ((this.bitField0_ & 64) != 0)
            {
                output.writeBool(10, this.hasMagnet_);
            }

            if ((this.bitField0_ & 16) != 0)
            {
                output.writeInt32(11, this.verticalAlignment_);
            }

            if ((this.bitField0_ & 128) != 0)
            {
                output.writeInt32(12, this.primaryButton_);
            }

            super.writeTo(output);
        }

        protected int computeSerializedSize()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            int size = super.computeSerializedSize();

            if ((this.bitField0_ & 1) != 0) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.vendor_);
            }

            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.model_);
            }

            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(3,
                        this.screenToLensDistance_);
            }

            if ((this.bitField0_ & 8) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(4, this.interLensDistance_);
            }

            int dataSize;

            if (this.leftEyeFieldOfViewAngles != null &&
                this.leftEyeFieldOfViewAngles.length > 0) {
                dataSize = 4 * this.leftEyeFieldOfViewAngles.length;
                size += dataSize;
                ++size;
                size += CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
            }

            if ((this.bitField0_ & 32) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(6, this.trayToLensDistance_);
            }

            if (this.distortionCoefficients != null &&
                this.distortionCoefficients.length > 0) {
                dataSize = 4 * this.distortionCoefficients.length;
                size += dataSize;
                ++size;
                size += CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
            }

            if ((this.bitField0_ & 64) != 0) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.hasMagnet_);
            }

            if ((this.bitField0_ & 16) != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.verticalAlignment_);
            }

            if ((this.bitField0_ & 128) != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.primaryButton_);
            }

            return size;
        }

        public CardboardDevice.DeviceParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            while (true)
            {
                int tag = input.readTag();
                int value;
                int limit;
                float[] arrayLength;
                int i;
                float[] newArray;
                int var8;

                switch (tag) {
                case 0:
                    return this;

                case 10:
                    this.vendor_ = input.readString();
                    this.bitField0_ |= 1;
                    continue;

                case 18:
                    this.model_ = input.readString();
                    this.bitField0_ |= 2;
                    continue;

                case 29:
                    this.screenToLensDistance_ = input.readFloat();
                    this.bitField0_ |= 4;
                    continue;

                case 37:
                    this.interLensDistance_ = input.readFloat();
                    this.bitField0_ |= 8;
                    continue;

                case 42:
                    value = input.readRawVarint32();
                    limit = input.pushLimit(value);
                    var8 = value / 4;
                    i = this.leftEyeFieldOfViewAngles == null ? 0 :
                    this.leftEyeFieldOfViewAngles.length;
                    newArray = new float[i + var8];

                    if (i != 0) {
                        System.arraycopy(this.leftEyeFieldOfViewAngles, 0, newArray, 0, i);
                    }

                    break;

                case 45:
                    value = WireFormatNano.getRepeatedFieldArrayLength(input, 45);
                    limit = this.leftEyeFieldOfViewAngles == null ? 0 :
                            this.leftEyeFieldOfViewAngles.length;
                    arrayLength = new float[limit + value];

                    if (limit != 0) {
                        System.arraycopy(this.leftEyeFieldOfViewAngles, 0, arrayLength, 0, limit);
                    }

                    while (limit < arrayLength.length - 1) {
                        arrayLength[limit] = input.readFloat();
                        input.readTag();
                        ++limit;
                    }

                    arrayLength[limit] = input.readFloat();
                    this.leftEyeFieldOfViewAngles = arrayLength;
                    continue;

                case 53:
                    this.trayToLensDistance_ = input.readFloat();
                    this.bitField0_ |= 32;
                    continue;

                case 58:
                    value = input.readRawVarint32();
                    limit = input.pushLimit(value);
                    var8 = value / 4;
                    i = this.distortionCoefficients == null ? 0 :
                        this.distortionCoefficients.length;
                    newArray = new float[i + var8];

                    if (i != 0) {
                        System.arraycopy(this.distortionCoefficients, 0, newArray, 0, i);
                    }

                    while (i < newArray.length) {
                        newArray[i] = input.readFloat();
                        ++i;
                    }

                    this.distortionCoefficients = newArray;
                    input.popLimit(limit);
                    continue;

                case 61:
                    value = WireFormatNano.getRepeatedFieldArrayLength(input, 61);
                    limit = this.distortionCoefficients == null ? 0 :
                            this.distortionCoefficients.length;
                    arrayLength = new float[limit + value];

                    if (limit != 0) {
                        System.arraycopy(this.distortionCoefficients, 0, arrayLength, 0, limit);
                    }

                    while (limit < arrayLength.length - 1) {
                        arrayLength[limit] = input.readFloat();
                        input.readTag();
                        ++limit;
                    }

                    arrayLength[limit] = input.readFloat();
                    this.distortionCoefficients = arrayLength;
                    continue;

                case 80:
                    this.hasMagnet_ = input.readBool();
                    this.bitField0_ |= 64;
                    continue;

                case 88:
                    value = input.readInt32();

                    switch (value) {
                    case 0:
                    case 1:
                    case 2:
                        this.verticalAlignment_ = value;
                        this.bitField0_ |= 16;

                    default:
                        continue;
                    }

                case 96:
                    value = input.readInt32();

                    switch (value) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        this.primaryButton_ = value;
                        this.bitField0_ |= 128;

                    default:
                        continue;
                    }

                default:
                    if (WireFormatNano.parseUnknownField(input, tag)) {
                        continue;
                    }

                    return this;
                }

                while (i < newArray.length) {
                    newArray[i] = input.readFloat();
                    ++i;
                }

                this.leftEyeFieldOfViewAngles = newArray;
                input.popLimit(limit);
            }
        }

        public static CardboardDevice.DeviceParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (CardboardDevice.DeviceParams) MessageNano.mergeFrom(new CardboardDevice.DeviceParams(), data);
        }

        public static CardboardDevice.DeviceParams parseFrom(CodedInputByteBufferNano input) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (new CardboardDevice.DeviceParams()).mergeFrom(input);
        }

        public interface ButtonType {
            int NONE = 0;
            int MAGNET = 1;
            int TOUCH = 2;
            int INDIRECT_TOUCH = 3;
        }

        public interface VerticalAlignmentType {
            int BOTTOM = 0;
            int CENTER = 1;
            int TOP = 2;
        }
    }
}
