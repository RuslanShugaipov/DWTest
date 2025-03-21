package com.example.dwtest.feature.detail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dwtest.core.ui.R
import com.example.dwtest.feature.detail.ui.AppInfo

@Composable
internal fun ContentSuccess(
    modifier: Modifier = Modifier,
    appInfo: AppInfo,
    onGoToAppClicked: (String) -> Unit,
) {
    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            appInfo.icon?.let { icon ->
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally),
                    bitmap = icon.asImageBitmap(),
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.name,
                    appInfo.name
                ),
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.app_version,
                    appInfo.version
                ),
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.package_name,
                    appInfo.packageName
                ),
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.checksum,
                    appInfo.checksum
                ),
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                onClick = {
                    appInfo.packageName.let { packageName ->
                        onGoToAppClicked(packageName)
                    }
                },
            ) {
                Text(
                    text = stringResource(R.string.go_to_app),
                )
            }
        }
    }
}
