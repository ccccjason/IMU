//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import java.util.Arrays;

public class Distortion
{
    private static final float[] DEFAULT_COEFFICIENTS = new float[] {0.441F, 0.156F};
    private float[] coefficients;

    public Distortion()
    {
        this.coefficients = (float[])DEFAULT_COEFFICIENTS.clone();
    }

    public Distortion(Distortion other)
    {
        this.setCoefficients(other.coefficients);
    }

    public static Distortion parseFromProtobuf(float[] coefficients)
    {
        Distortion distortion = new Distortion();
        distortion.setCoefficients(coefficients);
        return distortion;
    }

    public float[] toProtobuf()
    {
        return (float[])this.coefficients.clone();
    }

    public void setCoefficients(float[] coefficients)
    {
        this.coefficients = coefficients != null ? (float[])coefficients.clone() : new
                            float[0];
    }

    public float[] getCoefficients()
    {
        return this.coefficients;
    }

    public float distortionFactor(float radius)
    {
        float result = 1.0F;
        float rFactor = 1.0F;
        float rSquared = radius * radius;
        float[] arr$ = this.coefficients;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            float ki = arr$[i$];
            rFactor *= rSquared;
            result += ki * rFactor;
        }

        return result;
    }

    public float distort(float radius)
    {
        return radius * this.distortionFactor(radius);
    }

    public float distortInverse(float radius)
    {
        float r0 = radius / 0.9F;
        float r1 = radius * 0.9F;

        float dr1;

        for (float dr0 = radius - this.distort(r0); (double)Math.abs(r1 - r0) > 1.0E-4D;
             dr0 = dr1) {
            dr1 = radius - this.distort(r1);
            float r2 = r1 - dr1 * ((r1 - r0) / (dr1 - dr0));
            r0 = r1;
            r1 = r2;
        }

        return r1;
    }

    private static double[] solveLeastSquares(double[][] matA, double[] vecY)
    {
        int numSamples = matA.length;
        int numCoefficients = matA[0].length;
        double[][] matATA = new double[numCoefficients][numCoefficients];

        int vecX;

        for (int matInvATA = 0; matInvATA < numCoefficients; ++matInvATA) {
            for (int det = 0; det < numCoefficients; ++det) {
                double sum = 0.0D;

                for (vecX = 0; vecX < numSamples; ++vecX) {
                    sum += matA[vecX][det] * matA[vecX][matInvATA];
                }

                matATA[det][matInvATA] = sum;
            }
        }

        double[][] var14 = new double[numCoefficients][numCoefficients];

        if (numCoefficients != 2) {
            throw new RuntimeException("solveLeastSquares: only 2 coefficients currently supported, "
                                       + numCoefficients + " given.");
        } else {
            double var15 = matATA[0][0] * matATA[1][1] - matATA[0][1] * matATA[1][0];
            var14[0][0] = matATA[1][1] / var15;
            var14[1][1] = matATA[0][0] / var15;
            var14[0][1] = -matATA[1][0] / var15;
            var14[1][0] = -matATA[0][1] / var15;
            double[] vecATY = new double[numCoefficients];

            for (vecX = 0; vecX < numCoefficients; ++vecX) {
                double j = 0.0D;

                for (int i = 0; i < numSamples; ++i) {
                    j += matA[i][vecX] * vecY[i];
                }

                vecATY[vecX] = j;
            }

            double[] var16 = new double[numCoefficients];

            for (int var17 = 0; var17 < numCoefficients; ++var17) {
                double sum1 = 0.0D;

                for (int i1 = 0; i1 < numCoefficients; ++i1) {
                    sum1 += var14[i1][var17] * vecATY[i1];
                }

                var16[var17] = sum1;
            }

            return var16;
        }
    }

    public Distortion getApproximateInverseDistortion(float maxRadius)
    {
        boolean numSamples = true;
        boolean numCoefficients = true;
        double[][] matA = new double[10][2];
        double[] vecY = new double[10];

        for (int vecK = 0; vecK < 10; ++vecK) {
            float coefficients = maxRadius * (float)(vecK + 1) / 10.0F;
            double inverse = (double)this.distort(coefficients);
            double v = inverse;

            for (int j = 0; j < 2; ++j) {
                v *= inverse * inverse;
                matA[vecK][j] = v;
            }

            vecY[vecK] = (double)coefficients - inverse;
        }

        double[] var13 = solveLeastSquares(matA, vecY);
        float[] var14 = new float[var13.length];

        for (int var15 = 0; var15 < var13.length; ++var15) {
            var14[var15] = (float)var13[var15];
        }

        Distortion var16 = new Distortion();
        var16.setCoefficients(var14);
        return var16;
    }

    public boolean equals(Object other)
    {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof Distortion)) {
            return false;
        } else {
            Distortion o = (Distortion)other;
            return Arrays.equals(this.coefficients, o.coefficients);
        }
    }

    public String toString()
    {
        StringBuilder builder = (new
                                 StringBuilder()).append("{\n").append("  coefficients: [");

        for (int i = 0; i < this.coefficients.length; ++i) {
            builder.append(Float.toString(this.coefficients[i]));

            if (i < this.coefficients.length - 1) {
                builder.append(", ");
            }
        }

        builder.append("],\n}");
        return builder.toString();
    }
}
