package com.example.dwtest.core.data.repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.graphics.drawable.toBitmap
import com.example.dwtest.core.common.entity.Entity
import com.example.dwtest.core.data.model.AppInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import javax.inject.Inject

internal class InstalledAppsInfoRepositoryImpl @Inject constructor(
    private val packageManager: PackageManager,
    private val appPackageName: String,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : InstalledAppsInfoRepository {

    override suspend fun getInstalledAppsInfo(): Entity<List<AppInfo>> = withContext(ioDispatcher) {
        try {
            val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getInstalledApplications(
                    PackageManager.ApplicationInfoFlags.of(
                        PackageManager.GET_META_DATA.toLong()
                    )
                )
            } else {
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            }

            val filteredApps = apps
                .filter {
                    it.flags and ApplicationInfo.FLAG_SYSTEM == NOT_SYSTEM_APP_FLAG &&
                            it.packageName != appPackageName
                }
                .map {
                    val packageInfo = packageManager.getPackageInfo(it.packageName, 0)
                    val version = formatVersion(packageInfo.versionName.toString())


                    AppInfo(
                        icon = it.loadIcon(packageManager).toBitmap(),
                        name = it.loadLabel(packageManager).toString(),
                        version = version,
                        packageName = it.packageName,
                    )
                }

            Entity.Success(filteredApps)
        } catch (exception: Exception) {
            Entity.Error(exception)
        }
    }

    override suspend fun calculateApkChecksumByPackageName(packageName: String): Entity<String> =
        withContext(ioDispatcher) {
            try {
                val packageInfo = packageManager.getPackageInfo(packageName, 0)
                val apkPath = packageInfo.applicationInfo?.sourceDir
                val checksum = apkPath?.let { path ->
                    calculateApkChecksum(File(path))
                }.orEmpty()

                Entity.Success(checksum)
            } catch (exception: Exception) {
                Entity.Error(exception)
            }
        }

    private fun formatVersion(version: String): String {
        return if (version.contains(".")) {
            version
        } else {
            val major = version.take(1)
            val minor = version.drop(1).take(1)
            val patch = version.drop(2)
            "$major.$minor.$patch"
        }
    }

    private suspend fun calculateApkChecksum(apkFile: File): String = withContext(ioDispatcher) {
        val digest = MessageDigest.getInstance(CALCULATE_APK_CHECKSUM_ALGORITHM)

        FileInputStream(apkFile).use { fis ->
            val buffer = ByteArray(BUFFER_SIZE)
            var bytesRead: Int
            while (fis.read(buffer).also { bytesRead = it } != -1) {
                digest.update(buffer, 0, bytesRead)
            }
        }

        digest.digest().joinToString("") { FORMAT.format(it) }
    }

    companion object {
        private const val NOT_SYSTEM_APP_FLAG = 0
        private const val CALCULATE_APK_CHECKSUM_ALGORITHM = "SHA-1"
        private const val BUFFER_SIZE = 1024
        private const val FORMAT = "%02x"
    }
}
