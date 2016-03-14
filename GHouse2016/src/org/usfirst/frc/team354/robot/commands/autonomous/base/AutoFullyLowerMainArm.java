package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.commands.FullyLowerMainArm;
import org.usfirst.frc.team354.robot.commands.RaiseUpperArmToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFullyLowerMainArm extends CommandGroup {
    
    public  AutoFullyLowerMainArm() {
    	addSequential(new RaiseUpperArmToAngle(10.0));
    	addSequential(new FullyLowerMainArm());
    	addSequential(new RaiseUpperArmToAngle(0.0));
        
    }
}
