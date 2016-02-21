package org.usfirst.frc.team354.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team354.robot.Robot;
import org.usfirst.frc.team354.robot.vision.VisionSystem;
import org.usfirst.frc.team354.robot.vision.VisionSystem.Coords;
import org.usfirst.frc.team354.robot.vision.VisionSystem.VisionTarget;
import org.usfirst.frc.team354.robot.vision.VisionProcessing;

/**
 *
 */
public class ShootBall extends CommandGroup {
    
    public  ShootBall() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	VisionTarget bestTarget = Robot.visionSystem.getBestTarget();
    	if (bestTarget != null) {
    		double distance = VisionProcessing.distanceToTargetCorrected(bestTarget);
    		//calculation to convert distance to rpms TODO: ZQ
    		double rpmLower = distance * 1.11; //random number
    		double rpmUpper = distance * 2.11; //random number
    		
    		addSequential(new SpinUpShooterRollers(rpmLower, rpmUpper));
    		addSequential(new ShootBallAtSpeed());
    	}
    	
    	
    	
    }
    
}
