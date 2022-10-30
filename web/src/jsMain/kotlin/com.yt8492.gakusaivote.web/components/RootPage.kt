package com.yt8492.gakusaivote.web.components

import androidx.compose.runtime.Composable
import com.yt8492.gakusaivote.web.hooks.getOrCreateUser
import com.yt8492.gakusaivote.web.hooks.getTeams
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexFlow
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun RootPage() {
    val userId = getOrCreateUser()
    val (team, loading, refetch) = getTeams()
    println(userId)
    Div(
        attrs = {
            style {
                width(100.percent)
                height(100.vh)
                flexFlow(FlexDirection.Column, FlexWrap.Nowrap)
            }
        }
    ) {
        Nav {
            Div(
                attrs = {
                    style {
                        width(100.percent)
                        backgroundColor(Color.lightblue)
                        padding(24.px)
                    }
                }
            ) {
                H1 {
                    Text("学祭ハッカソン参加者投票フォーム")
                }
            }
        }
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                }
            }
        ) {
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        flexDirection(FlexDirection.Row)
                        justifyContent(JustifyContent.SpaceAround)
                    }
                }
            ) {
                P {
                    Text("チーム名")
                }
                P {
                    Text("説明")
                }
            }
            team.forEach {
                TeamRow(it)
            }
        }
        CreateTeamForm(
            onCreated = {
                refetch()
            }
        )
    }
}