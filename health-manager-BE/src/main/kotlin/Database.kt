package me.mikun

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database

fun Application.configureDatabase() {
//    Database.connect(
//        "jdbc:h2:mem:test",
//        driver = "org.h2.Driver"
//    )
    Database.connect(
        "jdbc:mariadb://localhost:3306/health_manager",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "koishi514"
    )
}