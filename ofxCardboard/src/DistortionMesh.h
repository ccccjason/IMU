/*
 * DistortionMesh.h
 *
 *  Created on: Mar 5, 2015
 *      Author: dantheman
 */

#pragma once
#include "ofMain.h"

class Distortion{
public:
	Distortion();
	Distortion(Distortion* other);
	~Distortion();

	void setCoefficients(float* coefficients);
	float* getCoefficients();

	float distortionFactor(float radius);
	float distort(float radius);
	float distortInverse(float radius);
	bool equals(Distortion *other);

	string toString();

private:
	const static int s_numberOfCoefficients = 2;
	float _coefficients[s_numberOfCoefficients];
};

class DistortionMesh{
public:

	virtual ~DistortionMesh();
	int _indices;
	int _arrayBufferID;
	int _elementBufferID;

	DistortionMesh();
	ofVbo getDistortionMesh(Distortion *distortionRed, Distortion *distortionGreen,
			Distortion *distortionBlue, float screenWidth, float screenHeight,
			float xEyeOffsetScreen, float yEyeOffsetScreen, float textureWidth,
			float textureHeight, float xEyeOffsetTexture,
			float yEyeOffsetTexture, float viewportXTexture,
			float viewportYTexture, float viewportWidthTexture,
			float viewportHeightTexture, bool vignetteEnabled);
};


