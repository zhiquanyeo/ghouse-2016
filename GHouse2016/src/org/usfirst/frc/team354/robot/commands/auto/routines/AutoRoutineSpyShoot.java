package org.usfirst.frc.team354.robot.commands.auto.routines;

import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoFireBall;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoShooterCleanUp;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoSpinUpShooterManualWhileWaiting;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoTankDriveForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutineSpyShoot extends CommandGroup {
    
    public  AutoRoutineSpyShoot() {
    	addSequential(new AutoTankDriveForTime(0.5, 0.5, 3000));
    	addSequential(new AutoSpinUpShooterManualWhileWaiting(4625, 4553, 2000));
    	addSequential(new AutoFireBall());
    	addSequential(new AutoShooterCleanUp());
    	
    }
}
