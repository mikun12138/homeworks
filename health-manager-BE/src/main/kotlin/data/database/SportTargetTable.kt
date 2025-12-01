package me.mikun.data.database

import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.json.json

object SportTargetTable : Table("SportTarget") {
    val sportTypes = json<List<String>>(
        "sport_types",
        Json,
    )
}