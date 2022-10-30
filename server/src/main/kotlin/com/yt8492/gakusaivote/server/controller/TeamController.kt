package com.yt8492.gakusaivote.server.controller

import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.server.repository.TeamRepository
import com.yt8492.gakusaivote.server.repository.UserRepository
import java.util.UUID

class TeamController(
    private val userRepository: UserRepository,
    private val teamRepository: TeamRepository,
) {
    fun createTeam(
        leaderId: String,
        name: String,
        description: String,
    ): Team? {
        userRepository.findById(leaderId) ?: return null
        val team = Team(
            id = UUID.randomUUID().toString(),
            leaderId = leaderId,
            name = name,
            description = description,
            createdAt = DateTime.now(),
        )
        teamRepository.create(team)
        return team
    }

    fun findAllTeam(): List<Team> {
        return teamRepository.findAll()
    }
}