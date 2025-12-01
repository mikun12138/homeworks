package me.mikun.data.dao

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class DailyState(
    val sportState: SportState,
    val sleepState: SleepState,
    val waterDrinkState: WaterDrinkState,
)

@Serializable
class SportState(
    val bmr: Int,
    val tef: Int,
    val tdee: Int,
)

@Serializable
data class SleepState(
    val shallowSleep: Int,
    val deekSleep: Int,
    val wakeupTime: LocalTime,
    val goBadTime: LocalTime,
)

@Serializable
data class WaterDrinkState(
    val drinkVolume: Int,
)
