package com.yt8492.gakusaivote.web.pages

import androidx.compose.runtime.Composable
import com.yt8492.gakusaivote.web.hooks.getOrCreateUser
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.flexFlow
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text

@Composable
fun RootPage() {
    val userId = getOrCreateUser()
    println(userId)
    Div(
        attrs = {
            style {
                width(100.percent)
                height(100.percent)
                flexFlow(FlexDirection.Column, FlexWrap.Nowrap)
                backgroundColor(Color.aliceblue)
            }
        }
    ) {
        Nav {
            Div {
                Text("title")
            }
        }
    }
}