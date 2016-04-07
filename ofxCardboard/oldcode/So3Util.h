/*
 * So3Util.h
 *
 *  Create on: Feb 24, 2015
 *      Author: Dantheman
 */

#pragma once
#include "ofMain.h"

class So3Util {
public:
    So3Util(){
        
    }
    virtual ~So3Util(){

    }
//	const float M_SQRT1_2 = 0.7071067811865476;
//	const float ONE_6TH = 0.16666667163372;
//	const float ONE_20TH = 0.16666667163372;
	ofVec3f temp31;
	ofVec3f sO3FromTwoVecN;
	ofVec3f sO3FromTwoVecA;
	ofVec3f sO3FromTwoVecB;
	ofVec3f sO3FromTwoVecRotationAxis;
	ofMatrix3x3 sO3FromTwoVec33R1;
	ofMatrix3x3 sO3FromTwoVec33R2;
	ofVec3f muFromSO3R2;
	ofVec3f rotationPiAboutAxisTemp;

	void ortho(ofVec3f v, ofVec3f & result) {
		int k = largestAbsComponent(v) - 1.0;
		if (k < 0) {
			k = 2;
		}
		result.set(0, 0, 0);
		if (k == 0)
			result.x = 1;
		if (k == 1)
			result.y = 1;
		if (k == 2)
			result.z = 1;

		result = result.getCrossed(v);
		result.normalize();
	}

	int largestAbsComponent(ofVec3f v){
		float xAbs = abs(v.x);
		float yAbs = abs(v.y);
		float zAbs = abs(v.z);

		if (xAbs > yAbs) {
			if (xAbs > zAbs) {
				return 0;
			}
			return 2;
		}

		if (yAbs > zAbs) {
			return 1;
		}
		return 2;
	}

	void sO3FromTwoVec(ofVec3f a, ofVec3f b, ofMatrix3x3 & result) {
		sO3FromTwoVecN = a.getCrossed(b);
//		ofLog()<<"sO3FromTwoVec "<<sO3FromTwoVecN<<endl;
		if (sO3FromTwoVecN.length() == 0.0) {
			float dot = a.dot(b);
			if (dot >= 0.0) {
				result.set(1, 0, 0, 0, 1, 0, 0, 0, 1);
			} else {
				ortho(a, sO3FromTwoVecRotationAxis);
				rotationPiAboutAxis(sO3FromTwoVecRotationAxis, result);
			}
			return;
		}

		sO3FromTwoVecA.set(a);
		sO3FromTwoVecB.set(b);

		sO3FromTwoVecN.normalize();
		sO3FromTwoVecA.normalize();
        sO3FromTwoVecB.normalize();

		ofMatrix3x3 r1 = sO3FromTwoVec33R1;
		r1.a = sO3FromTwoVecA.x;
		r1.b = sO3FromTwoVecA.y;
		r1.c = sO3FromTwoVecA.z;
		r1.d = sO3FromTwoVecN.x;
		r1.e = sO3FromTwoVecN.y;
		r1.f = sO3FromTwoVecN.z;
		temp31 = sO3FromTwoVecN.getCrossed(sO3FromTwoVecA);
		r1.g = temp31.x;
		r1.h = temp31.y;
		r1.i = temp31.z;

		ofMatrix3x3 r2 = sO3FromTwoVec33R2;
		r2.a =  sO3FromTwoVecB.x;
		r2.b =  sO3FromTwoVecB.y;
		r2.c =  sO3FromTwoVecB.z;
		r2.d = sO3FromTwoVecN.x;
		r2.e = sO3FromTwoVecN.y;
		r2.f = sO3FromTwoVecN.z;
		temp31 = sO3FromTwoVecB.getCrossed(sO3FromTwoVecN);
		r2.g = temp31.x;
		r2.h = temp31.y;
		r2.i = temp31.z;

		r1.transpose();
		result =r2*r1;
	}

	void rotationPiAboutAxis(ofVec3f  v, ofMatrix3x3 & result) {
		rotationPiAboutAxisTemp.set(v);
		rotationPiAboutAxisTemp*=(PI/rotationPiAboutAxisTemp.length());
		float invTheta = 0.3183098861837907;

		float kA = 0.0;

		float kB = 0.2026423672846756;
		rodriguesSo3Exp(rotationPiAboutAxisTemp, kA, kB, result);
	}

	void sO3FromMu(ofVec3f w, ofMatrix3x3 & result) {
		ofLog()<<"sO3FromMu w "<<w<<endl;
		ofLog()<<"sO3FromMu result "<<result<<endl;
		float thetaSq = w.dot(w);
		float theta = sqrt(thetaSq);
		float kA, kB;
		if (thetaSq < 1.0E-08) {
			kA = 1.0 - 0.16666667163372 * thetaSq;
			kB = 0.5;
		} else {
			if (thetaSq < 1.0E-06) {
				kB = 0.5 - 0.0416666679084301 * thetaSq;
				kA = 1.0
						- thetaSq * 0.16666667163372
								* (1.0 - 0.16666667163372 * thetaSq);
			} else {
				float invTheta = 1.0 / theta;
				kA = sin(theta) * invTheta;
				kB = (1.0 - cos(theta)) * (invTheta * invTheta);
			}
		}
		rodriguesSo3Exp(w, kA, kB, result);
	}

	void muFromSO3(ofMatrix3x3 so3, ofVec3f & result) {
		float cosAngle = (so3.a + so3.e + so3.i - 1.0)* 0.5;
        if((cosAngle)==0.0){
            
        }
            

		result.set((so3.h - so3.f) / 2.0,
				(so3.c - so3.g) / 2.0,
				(so3.d - so3.b) / 2.0);
        
		float sinAngleAbs = result.length();
        if((sinAngleAbs)==0.0){
            
        }
		if (cosAngle > 0.7071067811865476) {
			if (sinAngleAbs > 0.0){
				result.scale((asin(sinAngleAbs) / sinAngleAbs));
                if(result != result){
                    
                }
			}
		} else if (cosAngle > -0.7071067811865476 && sinAngleAbs != 0) {
			float angle = acos(cosAngle);
			result*=(angle / sinAngleAbs);
            if(result != result){
                
            }
		} else {
			float angle = PI - asin(sinAngleAbs);
			float a = so3.a - cosAngle;
			float b = so3.e - cosAngle;
			float c = so3.i - cosAngle;

			ofVec3f r2 = muFromSO3R2;
			if ((a * a > b * b) && (a * a > c * c)) {
				r2.set(a, (so3.d + so3.b) / 2.0,
						(so3.c + so3.g) / 2.0);
			} else if (b * b > c * c) {
				r2.set((so3.d + so3.b) / 2.0, b,
						(so3.h + so3.f) / 2.0);
			} else {
				r2.set((so3.c + so3.g) / 2.0,(so3.h + so3.f) / 2.0, c);
			}

			if (result.dot(r2) < 0.0) {
				r2*=-1.0;
			}
			r2.normalize();
            r2.scale(angle);
			result.set(r2);
            if(result != result){
                
            }
		}
//        cout<<"--"<<endl;
//        cout<<"muFromSO3"<<endl;
//        cout<<result<<endl;
        

	}

	void rodriguesSo3Exp(ofVec3f w, float kA, float kB, ofMatrix3x3& result) {
		float wx2 = w.x * w.x;
		float wy2 = w.y * w.y;
		float wz2 = w.z * w.z;

		result.a = 1.0 - kB * (wy2 + wz2);
		result.e = 1.0 - kB * (wx2 + wz2);
		result.i = 1.0 - kB * (wx2 + wy2);

		float a, b;
		a = kA * w.z;
		b = kB * (w.x * w.y);
		result.b =  b - a;
		result.d = b + a;

		a = kA * w.y;
		b = kB * (w.x * w.z);
		result.c = b + a;
		result.g = b - a;

		a = kA * w.x;
		b = kB * (w.y * w.z);
		result.f = b - a;
		result.h = b + a;
	}
    

	void generatorField(int i, ofMatrix3x3 pos, ofMatrix3x3& result) {
        if(i == 0){
            result.a = 0;
            result.d = -pos.g;
            result.g = pos.d;
        }else if(i == 1){
            result.d = 0;
            result.g = -pos.a;
            result.a = pos.g;
        }else{
            result.g = 0;
            result.a = -pos.d;
            result.d = pos.a;
        }
	}
};

