package com.yt8492.gakusaivote.web.api

import com.yt8492.gakusaivote.common.model.User
import com.yt8492.gakusaivote.common.model.serializer.DateTimeSerializer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
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
}
