package com.yt8492.gakusaivote.web.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.yt8492.gakusaivote.web.api.Api
import kotlinx.browser.localStorage
import kotlinx.coroutines.launch

@Composable
fun voteTeam(): VoteTeam {
    val (loading, setLoading) = remember {
        mutableStateOf(false)
    }
    val (done, setDone) = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val exec = { teamId: String ->
        val userId = localStorage.getItem("userId")!!
        setLoading(true)
        coroutineScope.launch {
            Api.vote(userId, teamId)
            setLoading(false)
            setDone(true)
            localStorage.setItem("votedTeamId", teamId)
        }
        Unit
    }
    return VoteTeam(
        exec = exec,
        done = done,
        loading = loading,
    )
}

data class VoteTeam(
    val exec: (teamId: String) -> Unit,
    val done: Boolean,
    val loading: Boolean,
)
