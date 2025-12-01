package me.mikun.data.dao

import kotlinx.datetime.LocalTime
import me.mikun.data.database.SleepTargetTable
import me.mikun.data.database.SportTargetTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity

class SleepTarget(
    id: EntityID<Int>
): IntEntity(id) {
    val sleepDuration: Int by SleepTargetTable.sleepDuration
    val wakeupTime: LocalTime by SleepTargetTable.wakeupTime
    val goBedTime: LocalTime by SleepTargetTable.goBedTime
}

class SportTarget(
    id: EntityID<Int>
): IntEntity(id) {
    val sportTypes: List<String> by SportTargetTable.sportTypes
}
