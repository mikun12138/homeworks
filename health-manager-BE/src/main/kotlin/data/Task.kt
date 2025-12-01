package me.mikun.data

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.datetime.time

object Tasks : IntIdTable("tasks") {
    val title = varchar(
        "title",
        128
    )
    val description = varchar(
        "description",
        128
    )
    val isCompleted = bool("isCompleted").default(false)
    val createAt = time("create_at")
}

class Task(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Task>(Tasks)

    var title by Tasks.title
    var description by Tasks.description
    var isCompleted by Tasks.isCompleted
    var createAt by Tasks.createAt

    override fun toString() = "Task(id=$id, title=$title, description=$description, isCompleted=$isCompleted)"
}