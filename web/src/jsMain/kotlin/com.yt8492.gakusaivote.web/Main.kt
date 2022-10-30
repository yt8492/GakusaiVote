package com.yt8492.gakusaivote.web

import com.yt8492.gakusaivote.web.pages.RootPage
import kotlinx.browser.document
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.HTMLElement

fun main() {
    val rootElement = document.getElementById("root") as HTMLElement
    renderComposable(rootElement) {
        RootPage()
    }
}
