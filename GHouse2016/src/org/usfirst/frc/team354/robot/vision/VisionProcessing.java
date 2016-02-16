package org.usfirst.frc.team354.robot.vision;

import org.usfirst.frc.team354.robot.vision.VisionSystem.VisionTarget;

public class VisionProcessing {
	
	public static double viewAngle = 25;
	public static int cameraRes = 640;
	
	// Static Methods for calculating distances
	public static double distanceToTarget(VisionTarget target) {
		double theta = VisionConstants.CameraConstants.LIFECAM_HORZ_VIEW_ANGLE / 2;
		return (VisionSystem.TARGET_WIDTH * VisionSystem.CAMERA_X_RES) / (2 * target.getWidth() * Math.tan(Math.toRadians(theta)));
	}
	
	public static double flatlineDistance(VisionTarget target) {
		// vert = height of target above ground - height of camera
		// hyp = distanceToTarget(target)
		double hyp = distanceToTarget(target);
		double vert = VisionSystem.TARGET_HEIGHT_ABOVE_GROUND - VisionSystem.CAMERA_HEIGHT;
		return Math.sqrt((hyp * hyp) - (vert * vert));
	}
	
	public static double widthInFeet(double knownPix, double knownFt, double widthPix) {
		return (knownFt * widthPix)/knownPix;
	}
	
	public double targetDistanceFromCenter(double centerX) {
		double centerOfGravity = cameraRes/2;
		return centerOfGravity - centerX;
	}
	
	
	public double distanceToCenter(double targetPix, double targetFt) {
		double w = widthInFeet(targetPix, targetFt, cameraRes)/2;
		double angleTangent = Math.tan(Math.toRadians(viewAngle/2));
		return w/angleTangent;
	}
	
	//widthPix is distance in pixels from center of FOV to center of target.
	public double horizontalAngle(double targetPix, double targetFt, double widthPix) {
		double w = widthInFeet(targetPix, targetFt, widthPix);
		double d = distanceToCenter(targetPix, targetFt);
		return Math.toDegrees(Math.atan(w/d));
	}
	
	public static void main(String[] args) {
		
		VisionProcessing vp = new VisionProcessing();
		
		double targetPix = 66;
		double centerX = 361;
		double targetFt = 20/12.0;
		
		double distance = vp.distanceToCenter(targetPix, targetFt);
	    System.out.println(distance);
	    double width = vp.widthInFeet(targetPix, targetFt, cameraRes);
	   // System.out.println(width);
	    double angle = vp.horizontalAngle(targetPix, targetFt, vp.targetDistanceFromCenter(centerX));
	 

	} 

}
