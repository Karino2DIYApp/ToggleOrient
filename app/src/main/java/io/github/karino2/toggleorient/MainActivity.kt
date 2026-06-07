package io.github.karino2.toggleorient

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Surface
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (!Settings.System.canWrite(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                data = "package:${packageName}".toUri()
            }
            startActivity(intent)
            return
        }


        if (Settings.System.getInt(contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_0) == Surface.ROTATION_0)
        {
            Settings.System.putInt(contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_90)
        }
        else
        {
            Settings.System.putInt(contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_0)
        }
        finish()
        // Settings.System.putInt(contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_0)
        // println(Surface.ROTATION_0)
        // println(Settings.System.getInt(contentResolver, Settings.System.USER_ROTATION, Surface.ROTATION_0))
    }
}