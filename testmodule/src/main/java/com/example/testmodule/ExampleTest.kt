package com.example.testmodule

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val DEBUG_PACKAGE_NAME = "com.example.sentryexample"
private const val TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class ExampleTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device = UiDevice.getInstance(instrumentation)
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun loginWithWordpress() {
        device.pressHome()

        device.wait(Until.hasObject(By.pkg(device.launcherPackageName).depth(0)), TIMEOUT)

        val launchIntentForPackage = context.packageManager.getLaunchIntentForPackage(DEBUG_PACKAGE_NAME)
        val intent = launchIntentForPackage?.apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }
        context.startActivity(intent)

        device.wait(
            Until.hasObject(By.pkg(DEBUG_PACKAGE_NAME).depth(0)),
            TIMEOUT
        )
    }
}
