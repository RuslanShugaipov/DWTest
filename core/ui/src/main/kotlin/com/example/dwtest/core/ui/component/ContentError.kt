package com.example.dwtest.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dwtest.core.ui.R
import com.example.dwtest.core.ui.theme.DWTestTheme

@Composable
fun ContentError(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    title: String = stringResource(R.string.error),
    message: String,
    onRetryClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 4.dp,
                ),
                text = title,
            )

            Text(
                text = message,
                textAlign = TextAlign.Center,
            )

            Button(
                modifier = Modifier.padding(top = 12.dp),
                onClick = onRetryClicked,
            ) {
                Text(
                    text = stringResource(R.string.retry),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentErrorPreview() = DWTestTheme {
    ContentError(
        title = stringResource(R.string.error),
        message = stringResource(R.string.error_occurred),
    )
}
