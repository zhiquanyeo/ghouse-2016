package org.usfirst.frc.team354.robot.dashboard;

import java.util.ArrayList;

import org.usfirst.frc.team354.robot.Robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DashboardConnection {
	private static boolean s_initialized = false;
	private static final String TABLE_BASE_NAME = "G-House/DashboardConnection";
	private static NetworkTable s_netTable;
	
	private static class DashboardConnectors {
		String key;
		IDashboardConnector connection;
		
		public DashboardConnectors(String k, IDashboardConnector c) {
			key = k;
			connection = c;
		}
	}
	
	private static final ArrayList<DashboardConnectors> s_pendingConnections = new ArrayList<>();
	private static final ArrayList<DashboardConnectors> s_connections = new ArrayList<>();
	
	private static void initialize() {
		if (!s_initialized) {
			for (int i = 0; i < s_pendingConnections.size(); i++) {
				s_connections.add(s_pendingConnections.get(i));
			}
			s_pendingConnections.clear();
			s_netTable = NetworkTable.getTable(TABLE_BASE_NAME);
			s_initialized = true;
		}
	}
	
	public static void registerDashboardConnection(String key, IDashboardConnector conn) {
		if (!s_initialized) {
			s_pendingConnections.add(new DashboardConnectors(key, conn));
		}
		else {
			s_connections.add(new DashboardConnectors(key, conn));
		}
	}
	
	public static void publish() {
		// Publish values
		s_netTable.putNumber("mainArmAngle", Robot.mainArm.getAngle());
		s_netTable.putNumber("upperArmAngle", Robot.upperArm.getAngle());
	}
}
