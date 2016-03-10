package org.usfirst.frc.team354.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;

public class MaxBotixRangefinder {
	private static final int SAMPLE_SIZE = 5;
	
	private static final double V_PER_IN = 5.0 / 512.0;
	
	private AnalogInput d_aIn;
	private double d_offset;
	
	/**
	 * 
	 * @param channel
	 * @param offset How much further the sensor is measuring than actual
	 */
	public MaxBotixRangefinder(int channel, double offset) {
		d_aIn = new AnalogInput(channel);
		d_offset = offset;
	}
	
	public double getRange() {
		double v = d_aIn.getAverageVoltage();
		double inches = v / V_PER_IN;
		return inches - d_offset;
	}
}
