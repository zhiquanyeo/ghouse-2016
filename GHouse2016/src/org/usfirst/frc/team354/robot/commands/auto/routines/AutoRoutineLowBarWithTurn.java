package org.usfirst.frc.team354.robot.commands.auto.routines;

import org.usfirst.frc.team354.robot.commands.FullyLowerMainArm;
import org.usfirst.frc.team354.robot.commands.autonomous.base.AutoSlowStartAndPrepareForDeposit;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutineLowBarWithTurn extends CommandGroup {
    
    public  AutoRoutineLowBarWithTurn() {
    	addParallel(new FullyLowerMainArm());
    	addParallel(new AutoSlowStartAndPrepareForDeposit());
    }
}
