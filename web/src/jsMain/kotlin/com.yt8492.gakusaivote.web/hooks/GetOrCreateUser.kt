package com.yt8492.gakusaivote.web.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yt8492.gakusaivote.web.api.Api
import kotlinx.browser.localStorage

@Composable
fun getOrCreateUser(): String? {
    val (id, setId) = remember {
        mutableStateOf<String?>(null)
    }
    LaunchedEffect(Unit) {
        val userId = localStorage.getItem("userId")
        if (userId != null) {
            setId(userId)
            return@LaunchedEffect
        }
        val user = Api.createUser()
        localStorage.setItem("userId", user.id)
        setId(user.id)
    }
    return id
}
