package org.usfirst.frc.team354.robot.dashboard;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DashboardConnection {
	private static final String TABLE_BASE_NAME = "G-House/DashboardConnection";
	private static NetworkTable s_netTable;
	
	private static boolean s_initialized = false;
	
	public static void intitialize() {
		s_netTable = NetworkTable.getTable(TABLE_BASE_NAME);
		s_initialized = true;
	}
	
	public static void publishNumber(String key, double value) {
		if (!s_initialized) return;
		s_netTable.putNumber(key, value);
	}
	
	public static void publishString(String key, String value) {
		if (!s_initialized) return;
		s_netTable.putString(key, value);
	}
	
	public static void publishBoolean(String key, boolean value) {
		if (!s_initialized) return;
		s_netTable.putBoolean(key, value);
	}
	
	public static void publishNumberArray(String key, double[] value) {
		if (!s_initialized) return;
		s_netTable.putNumberArray(key, value);
	}
}
