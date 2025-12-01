package me.mikun.data.response

import kotlinx.serialization.SerialName
import me.mikun.data.dao.SleepState
import me.mikun.data.dao.SportState
import me.mikun.data.dao.WaterDrinkState

data class DailyStateResponse(
    @SerialName("daily_state")
    val sportState: SportState,
    @SerialName("sleep_state")
    val sleepState: SleepState,
    @SerialName("water_drink_state")
    val waterDrinkState: WaterDrinkState,
)
