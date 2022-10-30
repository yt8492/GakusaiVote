package com.yt8492.gakusaivote.server.controller

import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.Vote
import com.yt8492.gakusaivote.server.repository.VoteRepository
import java.util.UUID

class VoteController(
    private val voteRepository: VoteRepository,
) {
    fun vote(userId: String, teamId: String) {
        val id = voteRepository.findByUserId(userId)?.id
            ?: UUID.randomUUID().toString()
        val vote = Vote(
            id = id,
            userId = userId,
            teamId = teamId,
            createdAt = DateTime.now(),
        )
        voteRepository.upsert(vote)
    }
}
