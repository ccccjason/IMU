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

public interface Phone {
    public static final class PhoneParams extends MessageNano implements Cloneable {
        private static volatile Phone.PhoneParams[] _emptyArray;
        private int bitField0_;
        private float xPpi_;
        private float yPpi_;
        private float bottomBezelHeight_;
        public float[] dEPRECATEDGyroBias;

        public static Phone.PhoneParams[] emptyArray()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if (_emptyArray == null) {
                Object var0 = InternalNano.LAZY_INIT_LOCK;
                synchronized(InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Phone.PhoneParams[0];
                    }
                }
            }

            return _emptyArray;
        }

        public float getXPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.xPpi_;
        }

        public Phone.PhoneParams setXPpi(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.xPpi_ = value;
            this.bitField0_ |= 1;
            return this;
        }

        public boolean hasXPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 1) != 0;
        }

        public Phone.PhoneParams clearXPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.xPpi_ = 0.0F;
            this.bitField0_ &= -2;
            return this;
        }

        public float getYPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.yPpi_;
        }

        public Phone.PhoneParams setYPpi(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.yPpi_ = value;
            this.bitField0_ |= 2;
            return this;
        }

        public boolean hasYPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 2) != 0;
        }

        public Phone.PhoneParams clearYPpi()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.yPpi_ = 0.0F;
            this.bitField0_ &= -3;
            return this;
        }

        public float getBottomBezelHeight()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return this.bottomBezelHeight_;
        }

        public Phone.PhoneParams setBottomBezelHeight(float value)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.bottomBezelHeight_ = value;
            this.bitField0_ |= 4;
            return this;
        }

        public boolean hasBottomBezelHeight()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (this.bitField0_ & 4) != 0;
        }

        public Phone.PhoneParams clearBottomBezelHeight()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.bottomBezelHeight_ = 0.0F;
            this.bitField0_ &= -5;
            return this;
        }

        public PhoneParams()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.clear();
        }

        public Phone.PhoneParams clear()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            this.bitField0_ = 0;
            this.xPpi_ = 0.0F;
            this.yPpi_ = 0.0F;
            this.bottomBezelHeight_ = 0.0F;
            this.dEPRECATEDGyroBias = WireFormatNano.EMPTY_FLOAT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public Phone.PhoneParams clone()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            Phone.PhoneParams cloned;

            try {
                cloned = (Phone.PhoneParams)super.clone();
            } catch (CloneNotSupportedException var3) {
                throw new AssertionError(var3);
            }

            if (this.dEPRECATEDGyroBias != null && this.dEPRECATEDGyroBias.length > 0) {
                cloned.dEPRECATEDGyroBias = (float[])this.dEPRECATEDGyroBias.clone();
            }

            return cloned;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            if ((this.bitField0_ & 1) != 0)
            {
                output.writeFloat(1, this.xPpi_);
            }

            if ((this.bitField0_ & 2) != 0)
            {
                output.writeFloat(2, this.yPpi_);
            }

            if ((this.bitField0_ & 4) != 0)
            {
                output.writeFloat(3, this.bottomBezelHeight_);
            }

            if (this.dEPRECATEDGyroBias != null && this.dEPRECATEDGyroBias.length > 0)
            {
                int dataSize = 4 * this.dEPRECATEDGyroBias.length;
                output.writeRawVarint32(34);
                output.writeRawVarint32(dataSize);

                for (int i = 0; i < this.dEPRECATEDGyroBias.length; ++i) {
                    output.writeFloatNoTag(this.dEPRECATEDGyroBias[i]);
                }
            }

            super.writeTo(output);
        }

        protected int computeSerializedSize()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            int size = super.computeSerializedSize();

            if ((this.bitField0_ & 1) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(1, this.xPpi_);
            }

            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(2, this.yPpi_);
            }

            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputByteBufferNano.computeFloatSize(3, this.bottomBezelHeight_);
            }

            if (this.dEPRECATEDGyroBias != null && this.dEPRECATEDGyroBias.length > 0) {
                int dataSize = 4 * this.dEPRECATEDGyroBias.length;
                size += dataSize;
                ++size;
                size += CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
            }

            return size;
        }

        public Phone.PhoneParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            while (true)
            {
                int tag = input.readTag();
                int length;
                int limit;
                int i;
                float[] newArray;

                switch (tag) {
                case 0:
                    return this;

                case 13:
                    this.xPpi_ = input.readFloat();
                    this.bitField0_ |= 1;
                    continue;

                case 21:
                    this.yPpi_ = input.readFloat();
                    this.bitField0_ |= 2;
                    continue;

                case 29:
                    this.bottomBezelHeight_ = input.readFloat();
                    this.bitField0_ |= 4;
                    continue;

                case 34:
                    length = input.readRawVarint32();
                    limit = input.pushLimit(length);
                    int var8 = length / 4;
                    i = this.dEPRECATEDGyroBias == null ? 0 : this.dEPRECATEDGyroBias.length;
                    newArray = new float[i + var8];

                    if (i != 0) {
                        System.arraycopy(this.dEPRECATEDGyroBias, 0, newArray, 0, i);
                    }

                    break;

                case 37:
                    length = WireFormatNano.getRepeatedFieldArrayLength(input, 37);
                    limit = this.dEPRECATEDGyroBias == null ? 0 : this.dEPRECATEDGyroBias.length;
                    float[] arrayLength = new float[limit + length];

                    if (limit != 0) {
                        System.arraycopy(this.dEPRECATEDGyroBias, 0, arrayLength, 0, limit);
                    }

                    while (limit < arrayLength.length - 1) {
                        arrayLength[limit] = input.readFloat();
                        input.readTag();
                        ++limit;
                    }

                    arrayLength[limit] = input.readFloat();
                    this.dEPRECATEDGyroBias = arrayLength;
                    continue;

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

                this.dEPRECATEDGyroBias = newArray;
                input.popLimit(limit);
            }
        }

        public static Phone.PhoneParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (Phone.PhoneParams) MessageNano.mergeFrom(new Phone.PhoneParams(), data);
        }

        public static Phone.PhoneParams parseFrom(CodedInputByteBufferNano input) throws IOException {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            return (new Phone.PhoneParams()).mergeFrom(input);
        }
    }
}
