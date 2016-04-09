#include "ofApp.h"

//--------------------------------------------------------------
void ofApp::setup()
{
    ofSetVerticalSync(true);
    ofSetFrameRate(60);
    tracking.setup();

    //    SensorEvent gyro;
    //    gyro.reading.set(0, 0, 0);
    //    gyro.timestamp = ofGetElapsedTimeMillis();
    //    gyro.type = GYRO;
    //    tracking.processSensorEvent(gyro);
    //
    //
    //    SensorEvent accel;
    //    accel.reading.set(sin(ofGetElapsedTimef()*0.091231), sin(ofGetElapsedTimef()*0.091231), sin(ofGetElapsedTimef()*0.091231)*-9.0);
    //    accel.timestamp = ofGetElapsedTimeMillis();
    //    accel.type = ACCEL;
    //    tracking.processSensorEvent(accel);

    //    easycam.enableMouseInput();
    planet.set(1000, 100);
    planet.setPosition(0, 0, 0);
    //  ofLog() << "setup" << endl;

    //    easycam.setDistance(20);
    cam.setPosition(0, 0, 0);

    easycam.setPosition(0, 0, -100);

}

//--------------------------------------------------------------
void ofApp::update()
{
    SensorEvent gyro;
    float foo = ofSignedNoise(ofGetElapsedTimef(), 0, 0) * 9.8;
    float bar = (9.8 - foo) / 2.0;
    gyro.reading.set(foo / 9.8, bar / 9.8 , bar / 9.8);
    gyro.timestamp = ofGetElapsedTimeMicros();
    gyro.type = GYRO;
    tracking.processSensorEvent(gyro);


    SensorEvent accel;
    accel.reading.set(foo, bar , bar);
    accel.reading.normalize();
    accel.reading.scale(9.8);
    accel.timestamp = ofGetElapsedTimeMicros();
    accel.type = ACCEL;
    tracking.processSensorEvent(accel);



    //    SensorEvent gyro;
    //    gyro.reading.set(abs(ofSignedNoise(sin(ofGetElapsedTimef())))*0.01, abs(ofSignedNoise(sin(ofGetElapsedTimef())))*0.01, abs(ofSignedNoise(sin(ofGetElapsedTimef())))*0.01);
    //    gyro.timestamp = ofGetElapsedTimeMicros();
    //    gyro.type = GYRO;
    //    tracking.processSensorEvent(gyro);
    //
    //
    //    SensorEvent accel;
    //    accel.reading.set(9.8-abs(ofSignedNoise(cos(ofGetElapsedTimef())))*9.8, 9.8-abs(ofSignedNoise(sin(ofGetElapsedTimef())))*9.8, 0.01*abs(ofSignedNoise(sin(ofGetElapsedTimef()))));
    //    accel.timestamp = ofGetElapsedTimeMicros();
    //    accel.type = ACCEL;
    //    tracking.processSensorEvent(accel);
}

//--------------------------------------------------------------
void ofApp::draw()
{
    ofBackground(0, 0, 0);
    ofDrawBitmapStringHighlight("Rot :" + ofToString(view.getRotate()), 10, 500);
    ofMatrix4x4 headView;
    headView = tracking.getLastHeadView(transform.getHeadView());


    ofMatrix4x4 translate;
    translate.makeTranslationMatrix(ofVec3f(0.06, 0, 0));
    view.makeIdentityMatrix();
    view = headView * translate;

    transform.setMatrix(view);

    ofSetColor(255, 0, 255);
    ofDrawBitmapStringHighlight("HeadView: " + ofToString(transform.getHeadView(),
                                10), 10, 100);
    ofDrawBitmapStringHighlight("Gyro :" + ofToString(
                                    tracking.mTracker.getLastGyro()), 10, 200);
    ofDrawBitmapStringHighlight("Accel :" + ofToString(
                                    tracking.mTracker.getLastAccel()), 10, 300);



    node.setTransformMatrix(view);
    cam.setOrientation(node.getOrientationQuat());

    //    cam.setOrientation(rot*cam.getOrientationQuat());


    ofSetColor(255, 0, 255);
    ofDrawBitmapStringHighlight("HeadView: " + ofToString(transform.getHeadView(),
                                10), 10, 100);
    ofDrawBitmapStringHighlight("Gyro :" + ofToString(
                                    tracking.mTracker.getLastGyro()), 10, 200);
    ofDrawBitmapStringHighlight("Accel :" + ofToString(
                                    tracking.mTracker.getLastAccel()), 10, 300);
    ofDrawBitmapStringHighlight("Rot :" + ofToString(view.getRotate()), 10, 400);

    ofDrawBitmapStringHighlight("Cardboard Camera", ofGetWidth() - ofGetHeight(),
                                ofGetHeight() / 2 - 20);
    cam.begin(ofRectangle(ofGetWidth() - ofGetHeight(), ofGetHeight() / 2,
                          ofGetHeight() / 2, ofGetHeight() / 2));
    ofSetColor(255, 0, 255);
    ofPushMatrix();
    //    ofVec3f axis;
    //    float angle;
    //    rot.getRotate(angle, axis);
    //  ofRotate(angle, axis.x, axis.y, axis.z);
    planet.drawWireframe();
    ofSetColor(255, 255, 0);
    ofDrawBox(50, 0, 0, 10, 10, 10);
    ofDrawBox(0, 50, 0, 10, 10, 10);
    ofDrawBox(0, 0, 50, 10, 10, 10);
    ofDrawBox(0, 50, 50, 10, 10, 10);
    ofDrawBox(50, 50, 50, 10, 10, 10);

    ofDrawBox(-50, 0, 0, 10, 10, 10);
    ofDrawBox(0, -50, 0, 10, 10, 10);
    ofDrawBox(0, 0, -50, 10, 10, 10);
    ofDrawBox(0, -50, -50, 10, 10, 10);
    ofDrawBox(-50, -50, -50, 10, 10, 10);
    ofPopMatrix();
    cam.end();

    ofDrawBitmapStringHighlight("EasyCam View", ofGetWidth() - ofGetHeight() / 2,
                                ofGetHeight() / 2 - 20);
    easycam.lookAt(node);
    easycam.begin(ofRectangle(ofGetWidth() - ofGetHeight() / 2, ofGetHeight() / 2,
                              ofGetHeight() / 2, ofGetHeight() / 2));
    node.draw();
    easycam.end();


    easycam.lookAt(cam);
    easycam.begin(ofRectangle(ofGetWidth() - ofGetHeight() / 2, 0,
                              ofGetHeight() / 2, ofGetHeight() / 2));
    cam.draw();
    easycam.end();
}

//--------------------------------------------------------------
void ofApp::keyPressed(int key)
{


}

//--------------------------------------------------------------
void ofApp::keyReleased(int key)
{

}

//--------------------------------------------------------------
void ofApp::mouseMoved(int x, int y)
{

}

//--------------------------------------------------------------
void ofApp::mouseDragged(int x, int y, int button)
{

}

//--------------------------------------------------------------
void ofApp::mousePressed(int x, int y, int button)
{

}

//--------------------------------------------------------------
void ofApp::mouseReleased(int x, int y, int button)
{

}

//--------------------------------------------------------------
void ofApp::windowResized(int w, int h)
{

}

//--------------------------------------------------------------
void ofApp::gotMessage(ofMessage msg)
{

}

//--------------------------------------------------------------
void ofApp::dragEvent(ofDragInfo dragInfo)
{

}
