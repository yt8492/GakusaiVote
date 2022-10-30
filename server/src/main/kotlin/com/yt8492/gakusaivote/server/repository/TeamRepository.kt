package com.yt8492.gakusaivote.server.repository

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.datastore.Query
import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.Team

class TeamRepository(
    private val datastore: Datastore,
) {
    fun create(team: Team) {
        val entity = modelToEntity(team)
        datastore.put(entity)
    }

    fun findAll(): List<Team> {
        val query = Query.newEntityQueryBuilder()
            .setKind(KIND)
            .build()
        return datastore.run(query)
            .asSequence()
            .map {
                entityToModel(it)
            }
            .toList()
    }

    private fun modelToEntity(team: Team): Entity {
        val key = newKey(team.id)
        return Entity.newBuilder(key)
            .set(PROPERTY_LEADER_ID, team.leaderId)
            .set(PROPERTY_NAME, team.name)
            .set(PROPERTY_DESCRIPTION, team.description)
            .set(PROPERTY_CREATED_AT, team.createdAt.unixMillisLong)
            .build()
    }

    private fun entityToModel(entity: Entity): Team {
        return Team(
            id = entity.key.name,
            leaderId = entity.getString(PROPERTY_LEADER_ID),
            name = entity.getString(PROPERTY_NAME),
            description = entity.getString(PROPERTY_DESCRIPTION),
            createdAt = DateTime(entity.getLong(PROPERTY_CREATED_AT)),
        )
    }

    private fun newKey(id: String): Key {
        return datastore.newKeyFactory().setKind(KIND).newKey(id)
    }

    companion object {
        private const val KIND = "Team"
        private const val PROPERTY_LEADER_ID = "leaderId"
        private const val PROPERTY_NAME = "name"
        private const val PROPERTY_DESCRIPTION = "description"
        private const val PROPERTY_CREATED_AT = "createdAt"
    }
}