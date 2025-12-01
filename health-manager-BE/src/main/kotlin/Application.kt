package me.mikun

import io.ktor.server.application.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.mikun.data.Tasks
import me.mikun.data.database.SportTargetTable
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)


}

@OptIn(ExperimentalTime::class)
fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureRouting()
    configureDatabase()
    transaction {
        SchemaUtils.create(Tasks)
        SchemaUtils.create(SportTargetTable)

        val taskId = Tasks.insert {
            it[title] = "Learn Exposed"
            it[description] = "Description..."
            it[createAt] = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        } get Tasks.id

        val secondTaskId = Tasks.insert {
            it[title] = "Another Learn Exposed"
            it[description] = "Another Description..."
            it[isCompleted] = true
            it[createAt] = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        } get Tasks.id

        Tasks.select(Tasks.id.count(), Tasks.isCompleted).groupBy(Tasks.isCompleted).forEach {
            println("${it[Tasks.isCompleted]}: ${it[Tasks.id.count()]} ")
        }

        SportTargetTable.insert {
            it[sportTypes] = listOf("Sports", "Sports", "Sports")
        }


    }
}
