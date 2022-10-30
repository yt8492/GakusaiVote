package com.yt8492.gakusaivote.server.repository

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.User

class UserRepository(
    private val datastore: Datastore,
) {
    fun create(user: User) {
        val entity = modelToEntity(user)
        datastore.put(entity)
    }

    fun findById(id: String): User? {
        val key = newKey(id)
        val entity = datastore.get(key) ?: return null
        return User(
            id,
            DateTime(entity.getLong(PROPERTY_CREATED_AT))
        )
    }

    private fun modelToEntity(user: User): Entity {
        val key = newKey(user.id)
        return Entity.newBuilder(key)
            .set(PROPERTY_CREATED_AT, user.createdAt.unixMillisLong)
            .build()
    }

    private fun newKey(id: String): Key {
        return datastore.newKeyFactory().setKind(KIND).newKey(id)
    }

    companion object {
        private const val KIND = "User"
        private const val PROPERTY_CREATED_AT = "createdAt"
    }
}