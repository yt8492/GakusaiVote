package com.yt8492.gakusaivote.web.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.yt8492.gakusaivote.common.model.Team
import com.yt8492.gakusaivote.web.hooks.createTeam
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexFlow
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun CreateTeamForm(
    onCreated: (Team) -> Unit,
) {
    val (name, setName) = remember {
        mutableStateOf("")
    }
    val (description, setDescription) = remember {
        mutableStateOf("")
    }
    val (exec, team, loading) = createTeam()
    LaunchedEffect(team) {
        if (team != null) {
            setName("")
            setDescription("")
            onCreated(team)
        }
    }
    Div(
        attrs = {
            style {
                padding(16.px)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
            }
        }
    ) {
        Text("チーム名")
        Input(
            type = InputType.Text,
            attrs = {
                onChange {
                    setName(it.value)
                }
            }
        )
        Text("説明")
        Input(
            type = InputType.Text,
            attrs = {
                onChange {
                    setDescription(it.value)
                }
            }
        )
        Button(
            attrs = {
                onClick {
                    exec(name, description)
                }
                if (loading) {
                    disabled()
                }
            }
        ) {
            Text("チームを登録")
        }
    }
}