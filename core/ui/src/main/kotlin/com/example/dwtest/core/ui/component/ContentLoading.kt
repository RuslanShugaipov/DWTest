package com.example.dwtest.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dwtest.core.ui.theme.DWTestTheme

@Composable
fun ContentLoading(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor,
        contentColor = backgroundColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContentLoader() = DWTestTheme {
    ContentLoading()
}
