package org.usfirst.frc.team354.robot.commands.auto.routines;

import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoFireBall;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoShooterCleanUp;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoSpinUpShooterManualWhileWaiting;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutineSpyShootNoMove extends CommandGroup {
    
    public  AutoRoutineSpyShootNoMove() {
    	addSequential(new AutoSpinUpShooterManualWhileWaiting(5125, 5053, 2000));
    	addSequential(new AutoFireBall());
    	addSequential(new AutoShooterCleanUp());
    }
}
