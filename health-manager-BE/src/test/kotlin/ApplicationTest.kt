package me.mikun

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.mikun.data.Tasks
import me.mikun.data.dao.DailyState
import me.mikun.data.dao.SleepState
import me.mikun.data.dao.SportState
import me.mikun.data.dao.WaterDrinkState
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ApplicationTest {
    val dailyState = DailyState(
        sportState = SportState(
            11,
            45,
            14,
        ),
        sleepState = SleepState(
            5,
            5,
            Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC).time,
            Clock.System.now().toLocalDateTime(timeZone = TimeZone.UTC).time,
        ),
        waterDrinkState = WaterDrinkState(
            1919810
        )
    )

    @Test
    fun testRoot() = testApplication {
        application {
            module()
            transaction {
                SchemaUtils.create(Tasks)

                val taskId = Tasks.insert {
                    it[title] = "Learn Exposed"
                    it[description] = "Description..."
                } get Tasks.id

                val secondTaskId = Tasks.insert {
                    it[title] = "Another Learn Exposed"
                    it[description] = "Another Description..."
                    it[isCompleted] = true
                } get Tasks.id

                Tasks.select(Tasks.id.count(), Tasks.isCompleted).groupBy(Tasks.isCompleted).forEach {
                    println("${it[Tasks.isCompleted]}: ${it[Tasks.id.count()]} ")
                }
            }
        }
        client.get("/").apply {
            assertEquals(
                HttpStatusCode.OK,
                status
            )
        }
    }

}
