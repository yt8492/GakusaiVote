package com.yt8492.gakusaivote.web.api

import com.yt8492.gakusaivote.common.model.CreateTeamInput
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.common.model.User
import com.yt8492.gakusaivote.common.model.serializer.DateTimeSerializer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

object Api {
    private const val BASE_URL = "http://localhost:8080"
    private val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json(
                Json {
                    serializersModule = SerializersModule {
                        contextual(DateTimeSerializer)
                        isLenient = true
                    }
                }
            )
        }
    }

    suspend fun createUser(): User {
        return client.post("$BASE_URL/user")
            .body()
    }

    suspend fun createTeam(
        leaderId: String,
        name: String,
        description: String,
    ): Team {
        return client.post("$BASE_URL/teams") {
            header("Authorization", "Bearer $leaderId")
            setBody(CreateTeamInput(name, description))
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getTeams(): List<Team> {
        return client.get("$BASE_URL/teams")
            .body()
    }
}
