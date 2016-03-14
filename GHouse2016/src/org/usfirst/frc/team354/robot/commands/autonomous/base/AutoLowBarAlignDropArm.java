package org.usfirst.frc.team354.robot.commands.autonomous.base;

import org.usfirst.frc.team354.robot.commands.RaiseMainArmToAngle;
import org.usfirst.frc.team354.robot.subsystems.MainArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBarAlignDropArm extends CommandGroup {
    
    public  AutoLowBarAlignDropArm() {
    	addParallel(new AutoTankDriveForTime(-1.0, 0.0, 2000));
    	addParallel(new RaiseMainArmToAngle(MainArm.ARM_BALL_INTAKE));
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
    }
}
