package com.yt8492.gakusaivote.server

import com.google.cloud.datastore.DatastoreOptions
import com.yt8492.gakusaivote.common.model.CreateTeamInput
import com.yt8492.gakusaivote.common.model.serializer.DateTimeSerializer
import com.yt8492.gakusaivote.server.controller.TeamController
import com.yt8492.gakusaivote.server.controller.UserController
import com.yt8492.gakusaivote.server.controller.VoteController
import com.yt8492.gakusaivote.server.repository.TeamRepository
import com.yt8492.gakusaivote.server.repository.UserRepository
import com.yt8492.gakusaivote.server.repository.VoteRepository
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.header
import io.ktor.server.request.path
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        json(
            Json {
                serializersModule = SerializersModule {
                    contextual(DateTimeSerializer)
                }
            }
        )
    }
    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Post)
        allowHeader("Authorization")
        allowSameOrigin = false
        allowNonSimpleContentTypes = true
    }
    val datastore = DatastoreOptions.getDefaultInstance().service
    val userRepository = UserRepository(datastore)
    val teamRepository = TeamRepository(datastore)
    val voteRepository = VoteRepository(datastore)
    val userController = UserController(userRepository)
    val teamController = TeamController(userRepository, teamRepository)
    val voteController = VoteController(voteRepository)
    routing {
        post("/user") {
            val user = userController.createUser()
            call.respond(user)
        }
        post("/teams") {
            val createTeamInput = call.receive<CreateTeamInput>()
            val leaderId = call.request.header("Authorization")?.removePrefix("Bearer ")
            if (leaderId == null) {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }
            val team = teamController.createTeam(
                leaderId = leaderId,
                name = createTeamInput.name,
                description = createTeamInput.description,
            )
            if (team == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            call.respond(team)
        }
        get("/teams") {
            val teams = teamRepository.findAll()
            call.respond(teams)
        }
        post("/teams/{id}") {
            val userId = call.request.header("Authorization")?.removePrefix("Bearer ")
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }
            val teamId = call.parameters["id"]
            if (teamId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            voteController.vote(userId, teamId)
            call.respond(HttpStatusCode.OK)
        }
    }
}
