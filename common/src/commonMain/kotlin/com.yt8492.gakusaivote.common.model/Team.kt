package com.yt8492.gakusaivote.common.model

import com.soywiz.klock.DateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: String,
    val leaderId: String,
    val name: String,
    val description: String,
    @Contextual
    val createdAt: DateTime,
)
