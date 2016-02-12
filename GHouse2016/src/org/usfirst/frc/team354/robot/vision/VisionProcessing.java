package org.usfirst.frc.team354.robot.vision;

public class VisionProcessing {
	
	public static double viewAngle = 25;
	public static int cameraRes = 640;
	
	public double widthInFeet(double knownPix, double knownFt, double widthPix) {
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
	
	public void flatlineDistance() {
		
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
	    double angle = vp.horizontalAngle(targetPix, targetFt, t.targetDistanceFromCenter(centerX));
	 

	} 

}
