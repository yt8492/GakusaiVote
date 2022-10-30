package com.yt8492.gakusaivote.server.controller

import com.soywiz.klock.DateTime
import com.yt8492.gakusaivote.common.model.User
import com.yt8492.gakusaivote.server.repository.UserRepository
import java.util.UUID

class UserController(
    private val userRepository: UserRepository
) {
    fun createUser(): User {
        val id = UUID.randomUUID().toString()
        val now = DateTime.now()
        val user = User(id, now)
        userRepository.create(user)
        return user
    }
}
