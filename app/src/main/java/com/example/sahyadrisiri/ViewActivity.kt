package com.example.sahyadrisiri

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val sharedPref = getSharedPreferences("WaterData", MODE_PRIVATE)
        val reports = sharedPref.getString("reports", "No data available")

        val reportList = findViewById<TextView>(R.id.reportList)
        val clearBtn = findViewById<Button>(R.id.clearBtn)

        val lines = reports?.split("\n") ?: listOf()

        val coloredText = StringBuilder()

        for (line in lines) {
            if (line.contains("Clean", true)) {
                coloredText.append("<font color='#2E7D32'>$line</font><br>")
            } else if (line.contains("Polluted", true)) {
                coloredText.append("<font color='#C62828'>$line</font><br>")
            } else {
                coloredText.append("$line<br>")
            }
        }

        reportList.text = android.text.Html.fromHtml(coloredText.toString())
        clearBtn.setOnClickListener {

            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            reportList.text = "No data available"
        }
    }
}