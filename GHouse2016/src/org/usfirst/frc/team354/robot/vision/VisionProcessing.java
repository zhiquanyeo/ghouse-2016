package org.usfirst.frc.team354.robot.vision;

import org.usfirst.frc.team354.robot.vision.VisionSystem.VisionTarget;

public class VisionProcessing {
	
	public static double viewAngle = 25;
	public static int cameraRes = 640;
	
	// Static Methods for calculating distances
	public static double distanceToTarget(VisionTarget target) {
		return distanceToTarget(target, false);
	}
	
	// Calculating using the vertical is a little bit more accurate
	public static double distanceToTarget(VisionTarget target, boolean useHoriz) {
		double theta;
		if (useHoriz) {
			theta = VisionConstants.CameraConstants.LIFECAM_HORZ_VIEW_ANGLE / 2;
			return (VisionSystem.TARGET_WIDTH * VisionSystem.CAMERA_X_RES) / (2 * target.getWidth() * Math.tan(Math.toRadians(theta)));
		}
		else {
			theta = VisionConstants.CameraConstants.LIFECAM_VERT_VIEW_ANGLE / 2;
			return (VisionSystem.TARGET_HEIGHT * VisionSystem.CAMERA_Y_RES) / (2 * target.getHeight() * Math.tan(Math.toRadians(theta)));
		}
	}
	
	// TODO This calculation needs work
	// Based on angle of camera and distance away from center
	// SO MUCH MATH
	public static double flatlineDistance(VisionTarget target) {
		// vert = height of target above ground - height of camera
		// hyp = distanceToTarget(target)
		double hyp = distanceToTarget(target);
		double vert = VisionSystem.TARGET_HEIGHT_ABOVE_GROUND - VisionSystem.CAMERA_HEIGHT;
		return Math.sqrt((hyp * hyp) - (vert * vert));
	}
	
	//d = Tft*FOVpixel/(2*Tpixel*tanΘ)
	// 
	
	public static double effectiveTargetWidth(VisionTarget target) {
		// Calculate the height of the bounding box and use that to "guess" the distance. Then use the distance
		// to see how wide the target is. 
		// Note: Since GRIP doesn't broadcast oriented bounding boxes, this could have some inaccuracy if we are
		// heading towards a target at an angle. We can use convexHullAngleScore as a measure of confidence
		double dist = flatlineDistance(target);
		
		// T_in = 2 * T_px * tan(theta) * d / FOV_px
		return (2 * target.getWidth() * Math.tan(Math.toRadians(VisionConstants.CameraConstants.LIFECAM_HORZ_VIEW_ANGLE / 2)) * dist) / VisionSystem.CAMERA_X_RES;
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
