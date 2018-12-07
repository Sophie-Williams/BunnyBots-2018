package org.sert2521.bunnybots

import edu.wpi.first.wpilibj.CameraServer
import org.sert2521.bunnybots.arm.Arm
import org.sert2521.bunnybots.autonomous.AutoChooser
import org.sert2521.bunnybots.autonomous.colorPin
import org.sert2521.bunnybots.autonomous.sortPin
import org.sert2521.bunnybots.autonomous.testAuto
import org.sert2521.bunnybots.drivetrain.Drivetrain
import org.sert2521.bunnybots.drivetrain.teleopDrive
import org.sert2521.bunnybots.dropper.Dropper
import org.sert2521.bunnybots.dropper.resetDroppers
import org.sert2521.bunnybots.util.UDPServer
import org.sert2521.bunnybots.util.initControls
import org.sert2521.bunnybots.util.initPreferences
import org.sert2521.bunnybots.util.logBuildInfo
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.disable
import org.team2471.frc.lib.framework.enable
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

object Robot : RobotProgram {
    init {
        AutoChooser

        Drivetrain
        Arm
        Dropper

        CameraServer.getInstance().startAutomaticCapture(0)
        CameraServer.getInstance().startAutomaticCapture(1)

        UDPServer.start()

        initControls()
        initPreferences()
        logBuildInfo()
    }

    private fun enableSubsystems() {
        Drivetrain.enable()
        Arm.enable()

        colorPin.set(false)
        sortPin.set(false)
    }

    private fun disableSubsystems() {
        Drivetrain.disable()
        Arm.disable()
    }

    override suspend fun enable() {
        enableSubsystems()
        resetDroppers()
    }

    override suspend fun disable() {
        disableSubsystems()
    }

    override suspend fun teleop() {
        println("Entering teleop...")

        teleopDrive()
    }

    override suspend fun autonomous() {
        println("Entering autonomous...")

        testAuto()
    }
}

fun main(args: Array<String>) {
    initializeWpilib()

    runRobotProgram(Robot)
}