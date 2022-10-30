package com.yt8492.gakusaivote.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.web.hooks.voteTeam
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun TeamRow(team: Team) {
    val (vote, done, loading) = voteTeam()
    val (votedTeamId, setVotedTeamId) = remember {
        mutableStateOf(localStorage.getItem("votedTeamId"))
    }
    LaunchedEffect(Unit) {
        window.addEventListener("storage", {
            setVotedTeamId(localStorage.getItem("votedTeamId"))
        })
    }
    Div(
        attrs = {
            style {
                padding(16.px)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
                backgroundColor(Color.lightgray)
            }
        }
    ) {
        P {
            Text(team.name)
        }
        P {
            Text(team.description)
        }
        Button(
            attrs = {
                onClick {
                    vote(team.id)
                }
                if (loading || votedTeamId == team.id) {
                    disabled()
                }
            }
        ) {
            Text("投票する")
        }
    }
}
