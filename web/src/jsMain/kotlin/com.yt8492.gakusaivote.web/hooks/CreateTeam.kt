package com.yt8492.gakusaivote.web.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.web.api.Api
import kotlinx.browser.localStorage
import kotlinx.coroutines.launch

@Composable
fun createTeam(): CreateTeam {
    val (loading, setLoading) = remember {
        mutableStateOf(false)
    }
    val (team, setTeam) = remember {
        mutableStateOf<Team?>(null)
    }
    val coroutineScope = rememberCoroutineScope()
    val exec = { name: String, description: String ->
        val leaderId = localStorage.getItem("userId")!!
        setLoading(true)
        coroutineScope.launch {
            val res = Api.createTeam(leaderId, name, description)
            setTeam(res)
            setLoading(false)
        }
        Unit
    }
    return CreateTeam(
        exec = exec,
        team = team,
        loading = loading,
    )
}

data class CreateTeam(
    val exec: (name: String, description: String) -> Unit,
    val team: Team?,
    val loading: Boolean
)
