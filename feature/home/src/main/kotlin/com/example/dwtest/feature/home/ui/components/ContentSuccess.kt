package com.example.dwtest.feature.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.dwtest.core.ui.R as UiKitR
import com.example.dwtest.feature.home.ui.AppInfo

@Composable
internal fun ContentSuccess(
    modifier: Modifier = Modifier,
    appsInfo: List<AppInfo>,
    onAppClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
        )
    ) {
        items(appsInfo) { appInfo ->
            AppInfoItem(
                appInfo = appInfo,
                onAppClick = {
                    onAppClick(appInfo.packageName)
                }
            )
        }
    }
}

@Composable
private fun AppInfoItem(
    modifier: Modifier = Modifier,
    appInfo: AppInfo,
    onAppClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onAppClick()
            }
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        appInfo.icon?.let { icon ->
            Image(
                modifier = Modifier.size(36.dp),
                bitmap = icon.asImageBitmap(),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer,
        ) {
            Column {
                Text(
                    text = stringResource(UiKitR.string.name, appInfo.name),
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(UiKitR.string.package_name, appInfo.packageName),
                )
            }
        }
    }
}
