package com.example.dwtest.core.ui.theme.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object DefaultAnimations {
    val exitLeft = slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween()
    ) + fadeOut(animationSpec = tween())

    val enterLeft = slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween()
    ) + fadeIn(animationSpec = tween())

    val exitRight = slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween()
    ) + fadeOut(animationSpec = tween())

    val enterRight = slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween()
    ) + fadeIn(animationSpec = tween())
}
