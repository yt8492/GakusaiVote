package com.yt8492.gakusaivote.common.model

import com.soywiz.klock.DateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    @Contextual
    val createdAt: DateTime,
)
