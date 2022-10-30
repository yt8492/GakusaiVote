package com.yt8492.gakusaivote.common.model

import com.soywiz.klock.DateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Vote(
    val id: String,
    val userId: String,
    val teamId: String,
    @Contextual
    val createdAt: DateTime,
)
