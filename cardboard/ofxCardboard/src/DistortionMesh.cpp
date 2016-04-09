/*
 * DistortionMesh.cpp
 *
 *  Created on: Mar 5, 2015
 *      Author: dantheman
 */

#include "DistortionMesh.h"

DistortionMesh::DistortionMesh()
{
    // TODO Auto-generated constructor stub

}

DistortionMesh::~DistortionMesh()
{
    // TODO Auto-generated destructor stub
}

ofVbo DistortionMesh::getDistortionMesh(Distortion* distortionRed,
                                        Distortion* distortionGreen, Distortion* distortionBlue,
                                        float screenWidth, float screenHeight, float xEyeOffsetScreen,
                                        float yEyeOffsetScreen, float textureWidth, float textureHeight,
                                        float xEyeOffsetTexture, float yEyeOffsetTexture,
                                        float viewportXTexture, float viewportYTexture,
                                        float viewportWidthTexture, float viewportHeightTexture,
                                        bool vignetteEnabled)
{
    GLfloat vertexData[14400];

    int vertexOffset = 0;

    int rows = 40;
    int cols = 40;

    float vignetteSizeTanAngle = 0.05f;

    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            float uTextureBlue = col / 39.0f
                                 * (viewportWidthTexture / textureWidth)
                                 + viewportXTexture / textureWidth;
            float vTextureBlue = row / 39.0f
                                 * (viewportHeightTexture / textureHeight)
                                 + viewportYTexture / textureHeight;

            float xTexture = uTextureBlue * textureWidth - xEyeOffsetTexture;
            float yTexture = vTextureBlue * textureHeight - yEyeOffsetTexture;
            float rTexture = sqrtf(xTexture * xTexture + yTexture * yTexture);

            float textureToScreenBlue =
                (rTexture > 0.0f) ?
                distortionBlue->distortInverse(rTexture)
                / rTexture :
                1.0f;

            float xScreen = xTexture * textureToScreenBlue;
            float yScreen = yTexture * textureToScreenBlue;

            float uScreen = (xScreen + xEyeOffsetScreen) / screenWidth;
            float vScreen = (yScreen + yEyeOffsetScreen) / screenHeight;
            float rScreen = rTexture * textureToScreenBlue;

            float screenToTextureGreen =
                (rScreen > 0.0f) ?
                distortionGreen->distortionFactor(rScreen) : 1.0f;
            float uTextureGreen = (xScreen * screenToTextureGreen
                                   + xEyeOffsetTexture) / textureWidth;
            float vTextureGreen = (yScreen * screenToTextureGreen
                                   + yEyeOffsetTexture) / textureHeight;

            float screenToTextureRed =
                (rScreen > 0.0f) ?
                distortionRed->distortionFactor(rScreen) : 1.0f;
            float uTextureRed = (xScreen * screenToTextureRed
                                 + xEyeOffsetTexture) / textureWidth;
            float vTextureRed = (yScreen * screenToTextureRed
                                 + yEyeOffsetTexture) / textureHeight;

            float vignetteSizeTexture = vignetteSizeTanAngle
                                        / textureToScreenBlue;

            float dxTexture = xTexture + xEyeOffsetTexture
                              - ofClamp(xTexture + xEyeOffsetTexture,
                                        viewportXTexture + vignetteSizeTexture,
                                        viewportXTexture + viewportWidthTexture
                                        - vignetteSizeTexture);
            float dyTexture = yTexture + yEyeOffsetTexture
                              - ofClamp(yTexture + yEyeOffsetTexture,
                                        viewportYTexture + vignetteSizeTexture,
                                        viewportYTexture + viewportHeightTexture
                                        - vignetteSizeTexture);
            float drTexture = sqrtf(
                                  dxTexture * dxTexture + dyTexture * dyTexture);

            float vignette = 1.0f;

            if (vignetteEnabled) {
                vignette = 1.0f
                           - ofClamp(drTexture / vignetteSizeTexture, 0.0f, 1.0f);
            }

            vertexData[(vertexOffset + 0)] = 2.0f * uScreen - 1.0f;
            vertexData[(vertexOffset + 1)] = 2.0f * vScreen - 1.0f;
            vertexData[(vertexOffset + 2)] = vignette;
            vertexData[(vertexOffset + 3)] = uTextureRed;
            vertexData[(vertexOffset + 4)] = vTextureRed;
            vertexData[(vertexOffset + 5)] = uTextureGreen;
            vertexData[(vertexOffset + 6)] = vTextureGreen;
            vertexData[(vertexOffset + 7)] = uTextureBlue;
            vertexData[(vertexOffset + 8)] = vTextureBlue;

            vertexOffset += 9;
        }
    }

    _indices = 3158;
    GLshort indexData[_indices];

    int indexOffset = 0;
    vertexOffset = 0;

    for (int row = 0; row < rows - 1; row++) {
        if (row > 0) {
            indexData[indexOffset] = indexData[(indexOffset - 1)];
            indexOffset++;
        }

        for (int col = 0; col < cols; col++) {
            if (col > 0) {
                if (row % 2 == 0) {
                    vertexOffset++;
                } else {
                    vertexOffset--;
                }
            }

            indexData[(indexOffset++)] = vertexOffset;
            indexData[(indexOffset++)] = (vertexOffset + 40);
        }

        vertexOffset += 40;
    }

    ofMesh mesh;

    mesh.getVertices().resize(14400);
    mesh.getColors().resize(14400);
    mesh.getNormals().resize(14400);

    int glOffset = 0;

    for (unsigned vertNum = 0; vertNum < 14400; vertNum++) {

        mesh.getVertices()[vertNum].x = vertexData[(glOffset + 0)];
        mesh.getVertices()[vertNum].y = vertexData[(glOffset + 1)];
        mesh.getVertices()[vertNum].z = vertexData[(glOffset + 2)];

        mesh.getNormals()[vertNum].x = vertexData[(glOffset + 3)];
        mesh.getNormals()[vertNum].y = vertexData[(glOffset + 4)];
        mesh.getNormals()[vertNum].z = vertexData[(glOffset + 2)];

        mesh.getColors()[vertNum].r = vertexData[(glOffset + 5)];
        mesh.getColors()[vertNum].g = vertexData[(glOffset + 6)];
        mesh.getColors()[vertNum].b = vertexData[(glOffset + 7)];
        mesh.getColors()[vertNum].a = vertexData[(glOffset + 8)];
        glOffset += 9;
    }

    mesh.getIndices().resize(_indices);

    for (int i = 0; i < _indices; i++) {
        mesh.getIndices()[i] = indexData[i];
        i++;
    }

    ofVbo vbo;
    return vbo;
}

Distortion::~Distortion()
{

}

Distortion::Distortion()
{
    _coefficients[0] = 0.441f;
    _coefficients[1] = 0.156f;
}

Distortion::Distortion(Distortion* other)
{
    for (int i = 0; i < s_numberOfCoefficients; i++) {
        _coefficients[i] = other->_coefficients[i];
    }
}

void Distortion::setCoefficients(float* coefficients)
{
    for (int i = 0; i < s_numberOfCoefficients; i++) {
        _coefficients[i] = coefficients[i];
    }
}

float* Distortion::getCoefficients()
{
    return &_coefficients[0];
}

float Distortion::distortionFactor(float radius)
{
    float result = 1.0f;
    float rFactor = 1.0f;
    float squaredRadius = radius * radius;

    for (int i = 0; i < s_numberOfCoefficients; i++) {
        rFactor *= squaredRadius;
        result += _coefficients[i] * rFactor;
    }

    return result;
}

float Distortion::distort(float radius)
{
    return radius * distortionFactor(radius);
}

float Distortion::distortInverse(float radius)
{
    float r0 = radius / 0.9f;
    float r = radius * 0.9f;
    float dr0 = radius - distort(r0);

    while (fabsf(r - r0) > 0.0001f) {
        float dr = radius - distort(r);
        float r2 = r - dr * ((r - r0) / (dr - dr0));
        r0 = r;
        r = r2;
        dr0 = dr;
    }

    return r;
}

bool Distortion::equals(Distortion* other)
{
    if (other == nullptr) {
        return false;
    } else if (other == this) {
        return true;
    }

    for (int i = 0; i < s_numberOfCoefficients; i++) {
        if (_coefficients[i] != other->_coefficients[i]) {
            return false;
        }
    }

    return true;
}

string Distortion::toString()
{
    return ofToString(_coefficients[0]) + " " + ofToString(_coefficients[1]);
}

