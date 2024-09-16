package com.example.yourapp

import android.content.Context
import android.widget.Toast
import com.example.logcatreader.MainActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class LogcatReader(private val context: Context) : Thread() {

    override fun run() {
        try {
            // Execute logcat command
            val process = Runtime.getRuntime().exec("logcat -d")
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                // Display log message as Toast
                showToast(line ?: "No log message")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showToast(message: String) {
        // Make sure to display Toast on the main thread
        (context as? MainActivity)?.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
