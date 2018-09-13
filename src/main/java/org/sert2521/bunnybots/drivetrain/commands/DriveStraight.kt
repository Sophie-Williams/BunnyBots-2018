package org.sert2521.bunnybots.drivetrain.commands

import org.sert2521.bunnybots.drivetrain.Drivetrain
import org.sertain.command.Command

class DriveStraight(private val speed: Double, timeoutMillis: Long) : Command(timeoutMillis) {
    override fun execute(): Boolean {
        Drivetrain.drive(speed, speed * 0.95)
        return false
    }
}
