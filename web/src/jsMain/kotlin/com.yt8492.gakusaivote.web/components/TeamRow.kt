package com.yt8492.gakusaivote.web.components

import androidx.compose.runtime.Composable
import com.yt8492.gakusaivote.common.model.Team
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
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun TeamRow(team: Team) {
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
    }
}
