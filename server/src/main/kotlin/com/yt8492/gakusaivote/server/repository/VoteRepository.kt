package com.yt8492.gakusaivote.server.repository

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.datastore.Query
import com.google.cloud.datastore.StructuredQuery
import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.Vote

class VoteRepository(
    private val datastore: Datastore
) {

    fun findByUserId(userId: String): Vote? {
        val filter = StructuredQuery.PropertyFilter.eq(PROPERTY_USER_ID, userId)
        val query = Query.newEntityQueryBuilder()
            .setKind(KIND)
            .setFilter(filter)
            .build()
        return datastore.run(query)
            .asSequence()
            .map {
                entityToModel(it)
            }
            .firstOrNull()
    }

    fun upsert(vote: Vote) {
        val entity = modelToEntity(vote)
        datastore.put(entity)
    }

    private fun modelToEntity(vote: Vote): Entity {
        val key = newKey(vote.id)
        return Entity.newBuilder(key)
            .set(PROPERTY_TEAM_ID, vote.teamId)
            .set(PROPERTY_USER_ID, vote.userId)
            .set(PROPERTY_CREATED_AT, vote.createdAt.unixMillisLong)
            .build()
    }

    private fun entityToModel(entity: Entity): Vote {
        return Vote(
            id = entity.key.name,
            teamId = entity.getString(PROPERTY_TEAM_ID),
            userId = entity.getString(PROPERTY_USER_ID),
            createdAt = DateTime(entity.getLong(PROPERTY_CREATED_AT)),
        )
    }

    private fun newKey(id: String): Key {
        return datastore.newKeyFactory().setKind(KIND).newKey(id)
    }

    companion object {
        private const val KIND = "Vote"
        private const val PROPERTY_TEAM_ID = "teamId"
        private const val PROPERTY_USER_ID = "userId"
        private const val PROPERTY_CREATED_AT = "createdAt"
    }
}
