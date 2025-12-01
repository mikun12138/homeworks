package me.mikun.data.database

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.time

object SleepTargetTable: Table("sleep_target") {
    val sleepDuration = integer("sleep_duration")
    val wakeupTime = time("wakeup_time")
    val goBedTime = time("go_bed_time")
}