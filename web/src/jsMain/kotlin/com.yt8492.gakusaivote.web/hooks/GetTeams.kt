package com.yt8492.gakusaivote.web.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.web.api.Api
import kotlinx.coroutines.launch

@Composable
fun getTeams(): GetTeams {
    val (loading, setLoading) = remember {
        mutableStateOf(false)
    }
    val (teams, setTeams) = remember {
        mutableStateOf(listOf<Team>())
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        setLoading(true)
        val res = Api.getTeams()
        setTeams(res)
        setLoading(false)
    }
    val refetch = {
        coroutineScope.launch {
            setLoading(true)
            val res = Api.getTeams()
            setTeams(res)
            setLoading(false)
        }
        Unit
    }
    return GetTeams(
        teams = teams,
        loading = loading,
        refetch = refetch,
    )
}

data class GetTeams(
    val teams: List<Team>,
    val loading: Boolean,
    val refetch: () -> Unit
)
