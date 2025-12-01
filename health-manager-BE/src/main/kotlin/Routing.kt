package me.mikun

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.mikun.data.dao.SportState

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello Health Manager!")
        }

        route("/api") {
            get("/daily") {
                call.respond(
                    SportState(
                        1,
                        2,
                        3
                    )
                )
            }

            get("/target") {

            }

            route("/upload") {
                patch("/daily") {

                }

                patch("/target") {

                }
            }
        }
    }
}
