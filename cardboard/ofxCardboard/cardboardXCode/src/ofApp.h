#pragma once

#include "ofMain.h"
#include "headTracking.h"
#include "headTransform.h"
#include "ofxGameCamera.h"
class ofApp : public ofBaseApp
{

public:
    void setup();
    void update();
    void draw();

    void keyPressed(int key);
    void keyReleased(int key);
    void mouseMoved(int x, int y);
    void mouseDragged(int x, int y, int button);
    void mousePressed(int x, int y, int button);
    void mouseReleased(int x, int y, int button);
    void windowResized(int w, int h);
    void dragEvent(ofDragInfo dragInfo);
    void gotMessage(ofMessage msg);

    headTracking tracking;
    headTransform transform;
    ofCamera easycam;
    //    ofCamera  cam;
    ofNode node;
    ofMatrix4x4 view;
    ofMatrix4x4 sum;
    ofQuaternion curRot;
    ofCamera cam;
    ofSpherePrimitive planet;
};
