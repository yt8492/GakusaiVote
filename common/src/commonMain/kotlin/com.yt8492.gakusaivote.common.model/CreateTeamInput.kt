package com.yt8492.gakusaivote.common.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateTeamInput(
    val name: String,
    val description: String,
)
